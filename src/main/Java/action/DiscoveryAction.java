package action;

import bean.DiscoveryBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import service.DiscoveryService;
import util.MultipleDiscovery;

public class DiscoveryAction extends ActionSupport implements ModelDriven<DiscoveryBean>
{

    DiscoveryBean bean = new DiscoveryBean();

    public String insertion()
    {
        DiscoveryService.insertion(bean);

        return "success";
    }

    public String update()
    {
        System.out.println(bean.getIp() + bean.getNewIp());

        DiscoveryService.update(bean);

        return "success";
    }

    public String delete()
    {
        if(DiscoveryService.delete(bean))
        {
            bean.setStatus("success");
        }
        else
        {
            bean.setStatus("unsuccess");
        }

        return "success";
    }

    public String provision()
    {
        MultipleDiscovery.put(bean);

        return "success";
    }


    @Override
    public DiscoveryBean getModel() {
        return bean;
    }
}
