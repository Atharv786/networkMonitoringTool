package service;

import bean.UpdateDiscoveryBean;
import dao.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by atharv on 24/4/22.
 */
public class UpdateDiscoveryService
{

    public static boolean update(UpdateDiscoveryBean bean)
    {
        ArrayList<Object> update = new ArrayList<>();

        Boolean result = false;

        DAO dao = new DAO();

        bean.setAddDeviceIP2(bean.getAddDeviceIP2().replaceAll("\\s",""));

        bean.setAddDeviceType2(bean.getAddDeviceType2().replaceAll("\\s",""));

        update.add(bean.getAddDeviceIP2());

        update.add(bean.getAddDeviceType2());

        update.add(bean.getId());

        List<HashMap<String, String>> flag = dao.select("select * from addDevice where deviceIP=? and deviceType=? and id<>?", update);

        if(flag.isEmpty())
        {
            update.clear();

            update.add(bean.getAddDeviceIP2());

            update.add(bean.getAddDeviceType2());

            update.add(bean.getAddDeviceName2());

            update.add(bean.getId());

            int affectedRow = dao.update("update addDevice set deviceIP=?, deviceType=?, deviceName=? where id=?", update);

            if(affectedRow!=0 && bean.getAddDeviceType2().equals("SSH"))
            {
                update.clear();

                update.add(bean.getIp());

                affectedRow = dao.update("select * from credentials where deviceIP=?", update);

                bean.setAddDeviceUsername2(bean.getAddDeviceUsername2().replaceAll("\\s",""));

                bean.setAddDevicePassword2(bean.getAddDevicePassword2().replaceAll("\\s",""));

                bean.setAddDeviceIP2(bean.getAddDeviceIP2().replaceAll("\\s",""));

                update.clear();

                update.add(bean.getAddDeviceUsername2());

                update.add(bean.getAddDevicePassword2());

                if(affectedRow!=0)
                {
                    update.add(bean.getIp());

                    affectedRow = dao.update("update credentials set deviceUsername=?, devicePassword=? where deviceIP=?", update);

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
                    update.add(bean.getAddDeviceIP2());

                    affectedRow = dao.update("insert into credentials (deviceUsername,devicePassword,deviceIP)"+"values(?,?,?)", update);

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
}
