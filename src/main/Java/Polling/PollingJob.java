package Polling;

import dao.DAO;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by atharv on 16/6/22.
 */
public class PollingJob implements Job {

    private static final ForkJoinPool forkJoinPool = new ForkJoinPool();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {

        ArrayList<Object> job = new ArrayList<>();

        List<HashMap<String, String>> data= DAO.select("select * from monitorTable", job);

        if(!data.isEmpty())
        {
            for (HashMap<String, String> row : data)
            {
                String ip=row.get("deviceIP");
                String type=row.get("deviceType");
                String id=row.get("deviceId");

                forkJoinPool.execute(new MainPolling(ip, type, id));
            }
        }

    }
}
