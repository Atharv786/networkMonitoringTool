package util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by atharv on 16/6/22.
 */
public class MultipleDiscovery
{
    static BlockingQueue<String> queue=new LinkedBlockingQueue<>();

    public static void sendRequest(String Ip)
    {
        try {
            queue.put(Ip);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getRequest()
    {
        String ip=null;

        try {
            ip= queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ip;
    }
}
