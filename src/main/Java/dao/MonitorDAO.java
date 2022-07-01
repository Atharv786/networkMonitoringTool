package dao;
import bean.MonitorBean;
import helper.ConnectionPoolHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by atharv on 21/4/22.
 */
public class MonitorDAO {

    public static ArrayList monitorSuccess()
    {
        ArrayList<MonitorBean> monitorBeanList=new ArrayList<>();

        try {
            Connection connection = ConnectionPoolHandler.getConnection();

            PreparedStatement stmt1=connection.prepareStatement("select * from monitorTable");
            ResultSet rs=stmt1.executeQuery();

            while (rs.next())
            {
                MonitorBean bean=new MonitorBean();

                bean.setId(rs.getInt(1));
                bean.setName(rs.getString(2));
                bean.setIp(rs.getString(3));
                bean.setType(rs.getString(4));
                bean.setDeviceStatus(rs.getString(5));


                monitorBeanList.add(bean);
            }

            ConnectionPoolHandler.releaseConnection(connection);
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

        return monitorBeanList;
    }
}
