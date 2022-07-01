package action;

import service.MonitorService;
import bean.MonitorBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * Created by atharv on 21/4/22.
 */
public class MonitorAction extends ActionSupport implements ModelDriven<MonitorBean>
{
    MonitorBean bean=new MonitorBean();


    public String monitor()
    {
        MonitorService.addMonitor(bean);

        bean.setStatus("monitorAddSuccessfully");

        return "monitor";
    }

    @Override
    public MonitorBean getModel() {
        return bean;
    }

}
