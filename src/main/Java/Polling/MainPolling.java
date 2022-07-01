package Polling;

import java.util.concurrent.RecursiveAction;

/**
 * Created by atharv on 16/6/22.
 */
public class MainPolling extends RecursiveAction
{
    String ip;

    String type;

    String id;

    public MainPolling(String ip, String type, String id)
    {
        this.ip=ip;

        this.type=type;

        this.id=id;
    }

    @Override
    protected void compute()
    {
        PingPolling task=new PingPolling(ip, id);

        task.fork();

        Boolean result=task.join();

        if (result && type.equals("SSH"))
        {
            SshPolling task2= new SshPolling(id, ip);

            task2.fork();
        }

    }
}
