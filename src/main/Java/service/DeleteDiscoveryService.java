package service;

import bean.DeleteDiscoveryBean;
import dao.DAO;

import java.util.ArrayList;

/**
 * Created by atharv on 22/4/22.
 */
public class DeleteDiscoveryService
{
    public static Boolean deleteDiscoveryService(DeleteDiscoveryBean bean)
    {
        DAO dao = new DAO();

        Boolean result = false;

        ArrayList<Object> deletion = new ArrayList<>();

        deletion.add(bean.getId());

        int flag = dao.update("delete from addDevice where id=?", deletion);

        if(flag!=0)
        {
            result = true;
        }

        return result;
    }
}
