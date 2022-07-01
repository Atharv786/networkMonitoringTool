package bean;

/**
 * Created by atharv on 17/4/22.
 */
public class InsertionBean
{
    private String addDeviceName;

    private String addDeviceIP;

    private String addDeviceType;

    public String getAddDeviceType() {
        return addDeviceType;
    }

    public void setAddDeviceType(String addDeviceType) {
        this.addDeviceType = addDeviceType;
    }

    public String getAddDeviceIP() {

        return addDeviceIP;
    }

    public void setAddDeviceIP(String addDeviceIP) {
        this.addDeviceIP = addDeviceIP;
    }

    public String getAddDeviceName() {
        return addDeviceName;
    }

    public void setAddDeviceName(String addDeviceName) {
        this.addDeviceName = addDeviceName;
    }

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String addDeviceUsername;

    private String addDevicePassword;

    public String getAddDeviceUsername() {
        return addDeviceUsername;
    }

    public void setAddDeviceUsername(String addDeviceUsername) {
        this.addDeviceUsername = addDeviceUsername;
    }

    public String getAddDevicePassword() {
        return addDevicePassword;
    }

    public void setAddDevicePassword(String addDevicePassword) {
        this.addDevicePassword = addDevicePassword;
    }
}
