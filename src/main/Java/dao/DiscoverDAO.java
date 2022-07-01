package dao;
import bean.DiscoveryBean;
import helper.ConnectionPoolHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by atharv on 21/4/22.
 */
public class DiscoverDAO {

    public static ArrayList discoverySuccess()
    {
        ArrayList<DiscoveryBean> discoveryBeanList=new ArrayList<>();

        try {
            Connection connection = ConnectionPoolHandler.getConnection();

            PreparedStatement stmt1=connection.prepareStatement("select * from addDevice");
            ResultSet rs=stmt1.executeQuery();

            while (rs.next())
            {
                DiscoveryBean bean=new DiscoveryBean();

                bean.setId(rs.getInt(1));
                bean.setName(rs.getString(2));
                bean.setIp(rs.getString(3));
                bean.setType(rs.getString(4));

                discoveryBeanList.add(bean);

            }

            ConnectionPoolHandler.releaseConnection(connection);
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

        return discoveryBeanList;
    }
}
