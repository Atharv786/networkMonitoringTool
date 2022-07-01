package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;


public class ConnectionPoolHandler
{

    static String url;

    static String user;

    static String password;

    static BlockingQueue<Connection> connectionPool=new LinkedBlockingDeque<>();

    static int INITIAL_POOL_SIZE = 10;

    static int MAX_POOL_SIZE = 20;

    static int count=0;


    public static void createConnection()
    {

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver" );
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }


        for (int i = 0; i <INITIAL_POOL_SIZE ; i++) {

            Connection connection = null;

            try
            {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/networkMonitoringTool", "root", "Mind@123" );

                connectionPool.put(connection);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }

    }


    public static Connection getConnection()
    {

        Connection connection=null;

        try
        {
            if(count>=INITIAL_POOL_SIZE && count<= MAX_POOL_SIZE)
            {
                INITIAL_POOL_SIZE++;

                Connection connection1 = null;
                try
                {

                    connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/networkMonitoringTool", "root", "Mind@123" );

                    connectionPool.put(connection1);
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

            }


            connection=connectionPool.take();

            if(connection.isClosed())
            {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/networkMonitoringTool", "root", "Mind@123" );

                connectionPool.put(connection);

                count++;
            }
            else
            {
                count++;
            }

        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return connection;
    }

    public static void releaseConnection(Connection connection)
    {
        try
        {

            if(count>10)
            {
                INITIAL_POOL_SIZE--;
            }

            if(!connection.isClosed())
            {
                connectionPool.put(connection);
            }
            else
            {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/networkMonitoringTool", "root", "Mind@123" );

                connectionPool.put(connection);
            }

        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}