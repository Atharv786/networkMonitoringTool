package service;

import bean.DeleteMonitorBean;
import dao.DAO;

import java.util.ArrayList;

/**
 * Created by atharv on 22/4/22.
 */
public class DeleteMonitorService
{
    public static Boolean deleteMonitorService(DeleteMonitorBean bean)
    {
        DAO dao = new DAO();

        ArrayList<Object> deletion = new ArrayList<>();

        deletion.add(bean.getId());

        Boolean result = false;

        int affectedRow;

        affectedRow = dao.update("delete from monitorTable where deviceId=?", deletion);

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
}

