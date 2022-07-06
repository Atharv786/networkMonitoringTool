package action;

import bean.MonitorBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import service.MonitorService;

/**
 * Created by atharv on 5/7/22.
 */
public class MonitorAction extends ActionSupport implements ModelDriven<MonitorBean>
{

    MonitorBean bean = new MonitorBean();

    public String load()
    {
        MonitorService.load(bean);

        bean.setStatus("monitorAddSuccessfully");

        return "success";
    }

    public String delete()
    {
        if(MonitorService.delete(bean))
        {
            bean.setStatus("success");
        }
        else
        {
            bean.setStatus("unsuccess");
        }

        return "success";

    }

    public String polling()
    {
        MonitorService.polling(bean);

        bean.setStatus("success");

        return "success";
    }



    @Override
    public MonitorBean getModel() {
        return bean;
    }

}
