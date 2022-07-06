package Polling;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by atharv on 16/6/22.
 */
public class QuartzScheduler
{

    SchedulerFactory schedulerFactory;

    Scheduler scheduler;

    public void create()
    {

        try {

            schedulerFactory = new StdSchedulerFactory();

            scheduler = schedulerFactory.getScheduler();

            JobDetail job = JobBuilder.newJob(PollingJob.class)
                    .withIdentity("pollingJob", "group1")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("pollingTrigger", "group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(60)
                            .repeatForever())
                    .build();

            scheduler.scheduleJob(job, trigger);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void start()
    {
        try
        {
            scheduler.start();
        }
        catch (SchedulerException e)
        {
            e.printStackTrace();
        }
    }

}


