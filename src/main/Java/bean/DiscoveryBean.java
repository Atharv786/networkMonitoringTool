package bean;

import java.util.ArrayList;

/**
 * Created by atharv on 5/7/22.
 */
public class DiscoveryBean
{
    private String newIp;

    public String getNewIp() {
        return newIp;
    }

    public void setNewIp(String newIp) {
        this.newIp = newIp;
    }

    private String status;

    private String name;

    private String ip;

    private String username;

    private String password;

    private String type;

    private String id;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    private String operation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<DiscoveryBean> getDiscoveryBeanList() {
        return discoveryBeanList;
    }

    public void setDiscoveryBeanList(ArrayList<DiscoveryBean> discoveryBeanList) {
        this.discoveryBeanList = discoveryBeanList;
    }

    private ArrayList<DiscoveryBean> discoveryBeanList = new ArrayList<>();

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
