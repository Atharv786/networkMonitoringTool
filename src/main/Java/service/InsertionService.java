package service;

import bean.InsertionBean;
import dao.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by atharv on 17/4/22.
 */
public class InsertionService
{
    public static boolean insertion(InsertionBean bean)
    {
        DAO dao = new DAO();

        Boolean result = false;

        bean.setAddDeviceIP(bean.getAddDeviceIP().replaceAll("\\s",""));

        bean.setAddDeviceType(bean.getAddDeviceType().replaceAll("\\s",""));

        ArrayList<Object> insertion = new ArrayList<>();

        insertion.add(bean.getAddDeviceIP());

        insertion.add(bean.getAddDeviceType());

        List<HashMap<String, String>> data = dao.select("select * from addDevice where deviceIP=? and deviceType=?", insertion);

        int affectedRow;

        if(data.isEmpty())
        {
            insertion.add(bean.getAddDeviceName());

            affectedRow = dao.update("insert into addDevice(deviceIP,deviceType,deviceName)"+"values(?,?,?)", insertion);

            if(bean.getAddDeviceType().equals("SSH") && affectedRow!=0)
            {
                ArrayList<Object> credentials = new ArrayList<>();

                bean.setAddDeviceUsername(bean.getAddDeviceUsername().replaceAll("\\s",""));

                bean.setAddDevicePassword(bean.getAddDevicePassword().replaceAll("\\s",""));

                bean.setAddDeviceIP(bean.getAddDeviceIP().replaceAll("\\s",""));

                credentials.add(bean.getAddDeviceUsername());

                credentials.add(bean.getAddDevicePassword());

                credentials.add(bean.getAddDeviceIP());

                affectedRow = dao.update("insert into credentials(deviceUsername,devicePassword,deviceIP)" + "values(?,?,?)",credentials);

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
}
