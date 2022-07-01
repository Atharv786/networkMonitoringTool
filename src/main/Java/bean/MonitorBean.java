package bean;

import java.util.ArrayList;

/**
 * Created by atharv on 21/4/22.
 */
public class MonitorBean
{
    private String status;

    private String deviceStatus;

    private String name;

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    private String ip;

    private String type;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private ArrayList<MonitorBean> monitorBeanList=new ArrayList<>();

    public ArrayList<MonitorBean> getDiscoveryBeanList() {
        return monitorBeanList;
    }

    public void setMonitorBeanList(ArrayList<MonitorBean> monitorBeanList) {
        this.monitorBeanList = monitorBeanList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
