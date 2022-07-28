package util;

import bean.DiscoveryBean;
import service.DiscoveryService;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by atharv on 16/6/22.
 */
public class MultipleDiscovery
{
    static BlockingQueue<DiscoveryBean> queue = new LinkedBlockingQueue<>();

    public static void discovery()
    {
        WebSocket webSocket = new WebSocket();

        new Thread(() -> {

            while (true)
            {
                try
                {
                    webSocket.onMessage(DiscoveryService.provision(queue.take()));
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

        }).start();
    }

    public static void put(DiscoveryBean bean)
    {
        try
        {
            queue.put(bean);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}
