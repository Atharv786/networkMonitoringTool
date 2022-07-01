package service;

import bean.ProvisionBean;
import dao.DAO;
import helper.SshConnection;
import util.MultipleDiscovery;
import util.PingDevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by atharv on 27/5/22.
 */
public class ProvisionService
{

    public static boolean provisionService(ProvisionBean bean)
    {
        String Ip = null;

        String Username = null;

        String Password = null;

        String status = "Unknown";

        Boolean result = false;

        DAO dao = new DAO();

        ArrayList<Object> provision = new ArrayList<>();

        Ip = bean.getIp();

        provision.add(Ip);

        provision.add(bean.getType());

        List<HashMap<String, String>> flag = dao.select("select * from monitorTable where deviceIP=? and deviceType=?",provision) ;

        MultipleDiscovery request=new MultipleDiscovery();

        if(flag.isEmpty())
        {
            provision.add(bean.getName());

            provision.add(status);

            if (PingDevice.pingDevice(request.getRequest(), String.valueOf(bean.getId())))
            {
                if(bean.getType().equals("SSH"))
                {
                    provision.clear();

                    provision.add(Ip);

                    flag = dao.select("select * from credentials where deviceIp=?", provision);

                    for (HashMap<String, String> row: flag)
                    {
                        Username = row.get("deviceUsername");

                        Password = row.get("devicePassword");
                    }

                    SshConnection sshConnection = new SshConnection(Ip, Username, Password);

                    String deviceName = sshConnection.executeCommands("uname\n");


                    if(deviceName.contains("Linux"))
                    {
                        provision.add(bean.getType());

                        provision.add(bean.getName());

                        provision.add(status);

                        int success = dao.update("insert into monitorTable(deviceIP,deviceType,deviceName,deviceStatus)"+"values(?,?,?,?)", provision);

                        if(success!=0)
                        {
                            result = true;
                        }
                        else
                        {
                            result = false;
                        }
                    }
                }
                else
                {
                    int affectedRow = dao.update("insert into monitorTable(deviceIP,deviceType,deviceName,deviceStatus)"+"values(?,?,?,?)", provision);

                    if(affectedRow!=0)
                    {
                        result = true;
                    }
                    else
                    {
                        result = false;
                    }

                }

            }

        }

        return result;
    }
}
