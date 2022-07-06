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

    public static boolean insertion(DiscoveryBean bean)
    {
        Boolean result = false;

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

                credentials.add(bean.getPassword());

                credentials.add(bean.getIp());

                affectedRow = DAO.update("insert into credentials(deviceUsername,devicePassword,deviceIP)" + "values(?,?,?)",credentials);

                if(affectedRow!=0)
                {
                    result = true;
                }
                else
                {
                    result =  false;
                }
            }
            else if(affectedRow!=0)
            {
                result = true;
            }
        }
        else
        {
            bean.setStatus("DeviceAlreadyPresent");
        }

        return result;
    }

    public static boolean update(DiscoveryBean bean)
    {
        ArrayList<Object> values = new ArrayList<>();

        Boolean result = false;

        bean.setIp(bean.getIp().replaceAll("\\s",""));

        bean.setType(bean.getType().replaceAll("\\s",""));

        values.add(bean.getIp());

        values.add(bean.getType());

        values.add(bean.getId());

        List<HashMap<String, String>> data = DAO.select("select * from addDevice where deviceIP=? and deviceType=? and id<>?", values);

        if(data.isEmpty())
        {
            values.clear();

            values.add(bean.getIp());

            values.add(bean.getType());

            values.add(bean.getName());

            values.add(bean.getId());

            int affectedRow = DAO.update("update addDevice set deviceIP=?, deviceType=?, deviceName=? where id=?", values);

            if(affectedRow!=0 && bean.getIp().equals("SSH"))
            {
                values.clear();

                values.add(bean.getIp());

                data = DAO.select("select * from credentials where deviceIP=?", values);

                bean.setUsername(bean.getUsername().replaceAll("\\s",""));

                bean.setPassword(bean.getPassword().replaceAll("\\s",""));

                bean.setIp(bean.getIp().replaceAll("\\s",""));

                values.clear();

                values.add(bean.getUsername());

                values.add(bean.getPassword());

                if(!data.isEmpty())
                {
                    values.add(bean.getIp());

                    affectedRow = DAO.update("update credentials set deviceUsername=?, devicePassword=? where deviceIP=?", values);

                    if(affectedRow!=0)
                    {
                        result = true;
                    }
                    else
                    {
                        result = false;
                    }
                }
                else
                {
                    values.add(bean.getIp());

                    affectedRow = DAO.update("insert into credentials (deviceUsername,devicePassword,deviceIP)"+"values(?,?,?)", values);

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
            else if(affectedRow!=0)
            {
                result = true;
            }
            else
            {
                result = false;
            }
        }

        return result;
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

    public static boolean provision(DiscoveryBean bean)
    {
        String Ip = null;

        String Username = null;

        String Password = null;

        String status = "Unknown";

        Boolean result = false;

        ArrayList<Object> values = new ArrayList<>();

        Ip = bean.getIp();

        values.add(Ip);

        values.add(bean.getType());

        List<HashMap<String, String>> data  = DAO.select("select * from monitorTable where deviceIP=? and deviceType=?", values) ;

        if(data.isEmpty())
        {
            values.add(bean.getName());

            values.add(status);

            if (PingDevice.pingDevice(bean.getIp() , String.valueOf(bean.getId())))
            {
                if(bean.getType().equals("SSH"))
                {
                    values.clear();

                    Ip = Ip.replaceAll("\\s","");

                    values.add(Ip);

                    data = DAO.select("select * from credentials where deviceIp=?", values);

                    for (HashMap<String, String> row: data)
                    {
                        Username = row.get("deviceUsername");

                        Password = row.get("devicePassword");
                    }

                    SshConnection sshConnection = new SshConnection(Ip, Username, Password);

                    String deviceName = sshConnection.executeCommands("uname\n");


                    if(deviceName.contains("Linux"))
                    {
                        values.add(bean.getType());

                        values.add(bean.getName());

                        values.add(status);

                        int success = DAO.update("insert into monitorTable(deviceIP,deviceType,deviceName,deviceStatus)"+"values(?,?,?,?)", values);

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
                    int affectedRow = DAO.update("insert into monitorTable(deviceIP,deviceType,deviceName,deviceStatus)"+"values(?,?,?,?)", values);

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
