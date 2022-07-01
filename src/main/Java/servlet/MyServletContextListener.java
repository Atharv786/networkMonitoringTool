package servlet;

import Polling.QuartzScheduler;

import helper.ConnectionPoolHandler;

import javax.servlet.http.HttpServlet;

public class MyServletContextListener extends HttpServlet
{
    public void init()
    {
        ConnectionPoolHandler connectionPoolHandler=new ConnectionPoolHandler();

        connectionPoolHandler.createConnection();

        QuartzScheduler scheduler=new QuartzScheduler();

        scheduler.create();

        scheduler.start();
    }
}