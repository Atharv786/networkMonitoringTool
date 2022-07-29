package bean;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by atharv on 5/7/22.
 */
public class MonitorBean
{

    private String status;

    private String ip;

    private String type;

    private String name;

    private String deviceStatus;

    private String id;

    public String getOperations() {
        return operations;
    }

    private String operations;

    private HashMap<String, Object> pingStatistic = new HashMap<>();

    private HashMap<String, Object> sshStatistic = new HashMap<>();

    public HashMap<String, Object> getPingStatistic() {
        return pingStatistic;
    }

    public void setPingStatistic(HashMap<String, Object> pingStatistic) {
        this.pingStatistic = pingStatistic;
    }

    public HashMap<String, Object> getSshStatistic() {
        return sshStatistic;
    }

    public void setSshStatistic(HashMap<String, Object> sshStatistic) {
        this.sshStatistic = sshStatistic;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private ArrayList<MonitorBean> monitorBeanList = new ArrayList<>();

    public ArrayList<MonitorBean> getMonitorBeanList() {
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

    public void setOperations(String operations) {
        this.operations = operations;
    }
}
