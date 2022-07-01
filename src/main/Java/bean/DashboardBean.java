package bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by atharv on 15/6/22.
 */
public class DashboardBean
{
    private List<Integer> availability = new ArrayList<>(4);

    private List<HashMap<String,String>> topRtt = new ArrayList<>();

    private List<HashMap<String,String>> topCpu = new ArrayList<>();

    private List<HashMap<String,String>> topMemory = new ArrayList<>();

    private List<HashMap<String,String>> topDisk = new ArrayList<>();

    private List<HashMap<String,String>> monitorGroup = new ArrayList<>();

    public List<Integer> getAvailability() {
        return availability;
    }

    public void setAvailability(List<Integer> availability) {
        this.availability = availability;
    }

    public List<HashMap<String, String>> getTopRtt()
    {
        return topRtt;
    }

    public void setTopRtt(List<HashMap<String, String>> topRtt)
    {
        this.topRtt = topRtt;
    }

    public List<HashMap<String, String>> getTopCpu()
    {
        return topCpu;
    }

    public void setTopCpu(List<HashMap<String, String>> topCpu)
    {
        this.topCpu = topCpu;
    }

    public List<HashMap<String, String>> getTopMemory()
    {
        return topMemory;
    }

    public void setTopMemory(List<HashMap<String, String>> topMemory)
    {
        this.topMemory = topMemory;
    }

    public List<HashMap<String, String>> getTopDisk()
    {
        return topDisk;
    }

    public void setTopDisk(List<HashMap<String, String>> topDisk)
    {
        this.topDisk = topDisk;
    }

    public List<HashMap<String, String>> getMonitorGroup()
    {
        return monitorGroup;
    }

    public void setMonitorGroup(List<HashMap<String, String>> monitorGroup)
    {
        this.monitorGroup = monitorGroup;
    }
}
