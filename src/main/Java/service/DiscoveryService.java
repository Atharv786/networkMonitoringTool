package service;

import bean.DiscoveryBean;
import dao.DiscoverDAO;

/**
 * Created by atharv on 21/4/22.
 */
public class DiscoveryService
{
    public static void addDiscovery(DiscoveryBean bean)
    {
        bean.setDiscoveryBeanList(DiscoverDAO.discoverySuccess());
    }
}
