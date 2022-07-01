package service;

import bean.MonitorBean;
import dao.MonitorDAO;

/**
 * Created by atharv on 21/4/22.
 */
public class MonitorService {

    public static void addMonitor(MonitorBean bean)
    {
        bean.setMonitorBeanList(MonitorDAO.monitorSuccess());
    }
}
