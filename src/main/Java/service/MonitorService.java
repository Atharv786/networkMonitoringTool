package service;

import bean.MonitorBean;
import dao.DAO;
import helper.GetData;

import java.util.ArrayList;

/**
 * Created by atharv on 5/7/22.
 */
public class MonitorService
{
    public static void load(MonitorBean bean)
    {
        bean.setMonitorBeanList(GetData.monitor());
    }

    public static Boolean delete(MonitorBean bean)
    {
        ArrayList<Object> values = new ArrayList<>();

        values.add(bean.getId());

        Boolean result = false;

        int affectedRow = DAO.update("delete from monitorTable where deviceId=?", values);

        if (affectedRow != 0)
        {
            result = true;
        }
        else
        {
            result = false;
        }

        return result;
    }

    public static void polling(MonitorBean bean)
    {
        try
        {
            GetData getData = new GetData();

            if (bean.getType().equals("Ping"))
            {
                bean.setPingStatistic(getData.getPingStatistics(bean.getId()));
            }
            else
            {
                bean.setSshStatistic(getData.getSshStatistics(bean.getId()));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
