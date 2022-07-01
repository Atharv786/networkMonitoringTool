package bean;

import java.util.ArrayList;

/**
 * Created by atharv on 21/4/22.
 */
public class DiscoveryBean
{
    private String status;

    private String name;

    private String ip;

    private String type;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private ArrayList<DiscoveryBean> discoveryBeanList=new ArrayList<>();

    public ArrayList<DiscoveryBean> getDiscoveryBeanList() {
        return discoveryBeanList;
    }

    public void setDiscoveryBeanList(ArrayList<DiscoveryBean> discoveryBeanList) {
        this.discoveryBeanList = discoveryBeanList;
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
