package bean;

import java.util.HashMap;

/**
 * Created by atharv on 24/6/22.
 */
public class PingPollingBean
{
    private HashMap<String, Object> PingPollingBeanList;

    private String id;

    private String ip;

    private String status;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashMap<String, Object> getPingPollingBeanList() {
        return PingPollingBeanList;
    }

    public void setPingPollingBeanList(HashMap<String, Object> pingPollingBeanList) {
        PingPollingBeanList = pingPollingBeanList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
