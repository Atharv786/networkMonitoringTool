package helper;

import bean.DiscoveryBean;
import bean.MonitorBean;
import dao.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by atharv on 5/7/22.
 */
public class GetData
{
    public static ArrayList discovery()
    {
        ArrayList<DiscoveryBean> discoveryBeanList = new ArrayList<>();

        ArrayList<Object> values = new ArrayList<>();

        List<HashMap<String, String>> data = DAO.select("select * from addDevice", values);

        for (HashMap<String, String> row : data)
        {
            DiscoveryBean bean = new DiscoveryBean();

            bean.setId(row.get("id"));
            bean.setName(row.get("deviceName"));
            bean.setIp(row.get("deviceIP"));
            bean.setType(row.get("deviceType"));

            discoveryBeanList.add(bean);
        }

        return discoveryBeanList;
    }

    public static ArrayList monitor()
    {
        ArrayList<MonitorBean> monitorBeanList = new ArrayList<>();

        ArrayList<Object> values = new ArrayList<>();

        List<HashMap<String, String>> data = DAO.select("select * from monitorTable", values);

        for (HashMap<String, String> row : data)
        {
            MonitorBean bean = new MonitorBean();

            bean.setId(row.get("deviceId"));
            bean.setName(row.get("deviceName"));
            bean.setIp(row.get("deviceIP"));
            bean.setType(row.get("deviceType"));
            bean.setDeviceStatus(row.get("deviceStatus"));

            monitorBeanList.add(bean);
        }

        return monitorBeanList;
    }
}
