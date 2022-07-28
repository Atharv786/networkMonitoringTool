package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;


public class ConnectionPoolHandler
{

    static String url;

    static String user;

    static String password;

    static BlockingQueue<Connection> connectionPool=new LinkedBlockingDeque<>();

    static int INITIAL_POOL_SIZE = 10;

    static int MAX_POOL_SIZE = 20;

    static AtomicInteger count= new AtomicInteger(0);


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


        for (int i = 0; i <INITIAL_POOL_SIZE ; i++)
        {

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
        Connection connection = null;

        try
        {
            if(count.get()>=INITIAL_POOL_SIZE && count.get()< MAX_POOL_SIZE)
            {
                try
                {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/networkMonitoringTool", "root", "Mind@123" );

                    connectionPool.put(connection);

                    connection = connectionPool.take();
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
            else
            {
                connection = connectionPool.take();
            }


            if(connection.isClosed())
            {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/networkMonitoringTool", "root", "Mind@123" );

                connectionPool.put(connection);
            }

            count.set(count.get()+1);


        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
            
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (Exception exception)
        {
            exception.getMessage();
        }

        return connection;
    }

    public static void releaseConnection(Connection connection)
    {
        try
        {
            if(count.get()>10)
            {
                INITIAL_POOL_SIZE--;

                connectionPool.remove(connection);

                connection.close();
            }
            else
            {
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

            count.set(count.get()-1);
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