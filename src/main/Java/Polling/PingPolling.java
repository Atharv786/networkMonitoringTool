package Polling;
import util.PingDevice;

import java.util.concurrent.RecursiveTask;

/**
 * Created by atharv on 16/6/22.
 */
public class PingPolling extends RecursiveTask<Boolean>
{
    String ip;

    String id;

    public PingPolling(String ip, String id)
    {
        this.ip = ip;

        this.id = id;
    }

    @Override
    protected Boolean compute()
    {
        if(PingDevice.pingDevice(ip, id))
        {
            return true;
        }

        return false;
    }
}
