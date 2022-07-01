package service;

import bean.DashboardBean;
import helper.GetDashboardData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by atharv on 15/6/22.
 */
public class DashboardService
{
    public static void loadDashboard(DashboardBean bean)
    {
        try
        {
            GetDashboardData getData = new GetDashboardData();

            ArrayList<Object> data = getData.getDashboardData();

            bean.setAvailability((ArrayList<Integer>) data.get(0));

            bean.setMonitorGroup((List<HashMap<String, String>>) data.get(1));

            bean.setTopRtt((List<HashMap<String, String>>) data.get(2));

            bean.setTopCpu((List<HashMap<String, String>>) data.get(3));

            bean.setTopMemory((List<HashMap<String, String>>) data.get(4));

            bean.setTopDisk((List<HashMap<String, String>>) data.get(5));


        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
