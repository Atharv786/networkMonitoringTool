package service;

import bean.PollingBean;
import helper.GetPollingData;

/**
 * Created by atharv on 28/6/22.
 */
public class PollingService
{
    public static void showPollingData(PollingBean bean)
    {
        try
        {
            GetPollingData getPollingData = new GetPollingData();

            if (bean.getType().equals("Ping"))
            {
                bean.setPingStatistic(getPollingData.getPingStatistics(bean.getId()));
            }
            else
            {
                bean.setSshStatistic(getPollingData.getSshStatistics(bean.getId()));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
