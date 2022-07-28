package service;

import bean.DiscoveryBean;
import dao.DAO;
import helper.GetData;
import helper.SshConnection;
import util.PingDevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by atharv on 5/7/22.
 */
public class DiscoveryService
{
    public static void load(DiscoveryBean bean)
    {
        bean.setDiscoveryBeanList(GetData.discovery());
    }

    public static void insertion(DiscoveryBean bean)
    {
        bean.setIp(bean.getIp().replaceAll("\\s",""));

        ArrayList<Object> values = new ArrayList<>();

        values.add(bean.getIp());

        values.add(bean.getType());

        List<HashMap<String, String>> data = DAO.select("select * from addDevice where deviceIP=? and deviceType=?", values);

        int affectedRow;

        if(data.isEmpty())
        {
            values.add(bean.getName());

            affectedRow = DAO.update("insert into addDevice(deviceIP,deviceType,deviceName)"+"values(?,?,?)", values);

            if(bean.getType().equals("SSH") && affectedRow!=0)
            {
                ArrayList<Object> credentials = new ArrayList<>();

                bean.setUsername(bean.getUsername().replaceAll("\\s",""));

                bean.setPassword(bean.getPassword().replaceAll("\\s",""));

                bean.setIp(bean.getIp().replaceAll("\\s",""));

                credentials.add(bean.getUsername());

/*
                credentials.add(Cipher.encode(bean.getPassword()));
*/

                credentials.add(bean.getPassword());

                credentials.add(bean.getIp());

                affectedRow = DAO.update("insert into credentials(deviceUsername,devicePassword,deviceIP)" + "values(?,?,?)",credentials);

                if(affectedRow!=0)
                {
                    bean.setStatus("success");
                }
                else
                {
                    bean.setStatus("unsuccess");
                }
            }
            else if(affectedRow!=0)
            {
                bean.setStatus("success");
            }
        }
        else
        {
            bean.setStatus("DeviceAlreadyPresent");
        }
    }

    public static void update(DiscoveryBean bean)
    {
        ArrayList<Object> values = new ArrayList<>();

        values.add(bean.getNewIp());

        values.add(bean.getType());

        values.add(bean.getId());

        List<HashMap<String, String>> data = DAO.select("select * from addDevice where deviceIP=? and deviceType=? and id<>?", values);

        if(data.isEmpty())
        {
            System.out.println("True");

            values.clear();

            values.add(bean.getNewIp());

            values.add(bean.getName());

            values.add(bean.getId());

            int affectedRow = DAO.update("update addDevice set deviceIP=?, deviceName=? where id=?", values);

            if(affectedRow!=0)
            {
                bean.setStatus("success");
            }
            else
            {
                bean.setStatus("unsuccess");
            }
        }
        else
        {
            bean.setStatus("deviceAlreadyPresent");
        }
    }

    public static boolean delete(DiscoveryBean bean)
    {
        Boolean result = false;

        ArrayList<Object> deletion = new ArrayList<>();

        deletion.add(bean.getId());

        int affetcedRow = DAO.update("delete from addDevice where id=?", deletion);

        if(affetcedRow!=0)
        {
            result = true;
        }

        return result;
    }

    public static String provision(DiscoveryBean bean)
    {
        String Ip = null;

        String type = null;

        String Username = null;

        String Password = null;

        String status = "Unknown";

        String name = null;

        String result = null;

        ArrayList<Object> values = new ArrayList<>();

        values.add(bean.getId());

        List<HashMap<String, String>> data = DAO.select("select * from addDevice where id=?",values);

        for (HashMap<String, String> datas : data)
        {
            System.out.println(datas);

            Ip = datas.get("deviceIP");

            type = datas.get("deviceType");

            name = datas.get("deviceName");
        }

        System.out.println(Ip+type+name);

        values.clear();

        values.add(Ip);

        values.add(type);

        data  = DAO.select("select * from monitorTable where deviceIP=? and deviceType=?", values) ;

        if(data.isEmpty())
        {
            if (PingDevice.pingDevice(Ip , String.valueOf(bean.getId())))
            {
                if(type.equals("SSH"))
                {
                    values.clear();

                    values.add(Ip);

                    data = DAO.select("select * from credentials where deviceIp=?", values);

                    for (HashMap<String, String> row: data)
                    {
                        Username = row.get("deviceUsername");

                        Password = row.get("devicePassword");
                    }

                    SshConnection sshConnection = new SshConnection(Ip, Username, Password);

                    String deviceName = sshConnection.executeCommands("uname\n", "shell");

                    System.out.println("data : " +deviceName);

                    if(deviceName.contains("Linux"))
                    {
                        values.add(type);

                        values.add(name);

                        values.add(status);

                        int success = DAO.update("insert into monitorTable(deviceIP,deviceType,deviceName,deviceStatus)"+"values(?,?,?,?)", values);

                        if(success!=0)
                        {
                            result = "DeviceAddedSuccesfully";
                        }
                        else
                        {
                            result = "DeviceTypeIsNotLinux";
                        }
                    }
                }
                else
                {
                    values.add(name);

                    values.add(status);

                    int affectedRow = DAO.update("insert into monitorTable(deviceIP,deviceType,deviceName,deviceStatus)"+"values(?,?,?,?)", values);

                    if(affectedRow!=0)
                    {
                        result = "DeviceAddedSuccesfully";
                    }
                    else
                    {
                        result = "DeviceCannotAdded";
                    }

                }

            }
            else
            {
                result = "Unreachable";
            }

        }
        else
        {
            result = "deviceAlreadyPresent";
        }

        return result;
    }
}
