package action;

import bean.PollingBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import service.PollingService;

/**
 * Created by atharv on 21/4/22.
 */
public class PollingAction extends ActionSupport implements ModelDriven<PollingBean>
{
    PollingBean bean = new PollingBean();

    public String Polling()
    {
        try
        {
            PollingService.showPollingData(bean);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return "success";
    }

    @Override
    public PollingBean getModel() {
        return bean;
    }

}
