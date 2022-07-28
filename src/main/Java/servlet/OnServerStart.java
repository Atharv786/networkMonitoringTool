package servlet;

import Polling.QuartzScheduler;
import dao.DAO;
import helper.ConnectionPoolHandler;
import util.MultipleDiscovery;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;

public class OnServerStart extends HttpServlet
{
    public void init()
    {
        ConnectionPoolHandler.createConnection();

        QuartzScheduler scheduler=new QuartzScheduler();

        scheduler.create();

        scheduler.start();

        MultipleDiscovery.discovery();

        ArrayList<Object> values = new ArrayList<>();

        String status = "Unknown";

        values.add(status);

        DAO.update("update monitorTable set deviceStatus=?",values);
    }
}