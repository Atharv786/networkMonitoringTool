package servlet;

import Polling.QuartzScheduler;

import helper.ConnectionPoolHandler;

import javax.servlet.http.HttpServlet;

public class OnServerStart extends HttpServlet
{
    public void init()
    {
        ConnectionPoolHandler.createConnection();

        QuartzScheduler scheduler=new QuartzScheduler();

        scheduler.create();

        scheduler.start();
    }
}