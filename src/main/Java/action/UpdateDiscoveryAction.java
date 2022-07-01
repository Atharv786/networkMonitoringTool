package action;

import bean.UpdateDiscoveryBean;
import service.UpdateDiscoveryService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateDiscoveryAction extends ActionSupport implements ModelDriven<UpdateDiscoveryBean> {

    UpdateDiscoveryBean bean=new UpdateDiscoveryBean();

    public String updateDiscovery()
    {
        Boolean nullValue;

        if(bean.getAddDeviceType2().equals("SSH"))
        {
            if(bean.getAddDeviceName2().equals("") || bean.getAddDeviceIP2().equals("") || bean.getAddDeviceType2().equals("") || bean.getAddDeviceUsername2().equals("") || bean.getAddDevicePassword2().equals(""))
            {
                nullValue = false;
            }
            else
            {
                nullValue = true;
            }
        }
        else
        {
            if(bean.getAddDeviceName2().equals("") || bean.getAddDeviceIP2().equals("") || bean.getAddDeviceType2().equals(""))
            {
                nullValue = false;
            }
            else
            {
                nullValue = true;
            }
        }

        if(nullValue)
        {
            if (UpdateDiscoveryService.update(bean))
            {
                bean.setStatus("success");
            }
            else
            {
                bean.setStatus("UpdateUnsuccessfull");
            }
        }
        else
        {
            bean.setStatus("fieldsRequired");
        }


        return "updateSuccess";
    }

    @Override
    public UpdateDiscoveryBean getModel()
    {
        return bean;
    }
}