package action;

import bean.InsertionBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import service.InsertionService;
/**
 * Created by atharv on 16/4/22.
 */
public class InsertionAction extends ActionSupport implements ModelDriven<InsertionBean>
{
    private InsertionBean bean = new InsertionBean();

    public String insertionAction()
    {
        Boolean nullValue;

        if(bean.getAddDeviceType().equals("SSH"))
        {
            if(bean.getAddDeviceName().equals("") || bean.getAddDeviceIP().equals("") || bean.getAddDeviceType().equals("") || bean.getAddDeviceUsername().equals("") || bean.getAddDevicePassword().equals(""))
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
            if(bean.getAddDeviceName().equals("") || bean.getAddDeviceIP().equals("") || bean.getAddDeviceType().equals(""))
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
            if (InsertionService.insertion(bean))
            {
                bean.setStatus("success");
            }
            else
            {
                bean.setStatus("InsertionUnsuccessfull");
            }
        }
        else
        {
            bean.setStatus("fieldsRequired");
        }

        return "insertion";
    }


    @Override
    public InsertionBean getModel() {
        return bean;
    }
}
