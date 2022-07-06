package action;

import bean.DiscoveryBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import service.DiscoveryService;
import helper.Validator;
import util.MultipleDiscovery;

public class DiscoveryAction extends ActionSupport implements ModelDriven<DiscoveryBean>
{

    DiscoveryBean bean = new DiscoveryBean();

    public String load()
    {
        DiscoveryService.load(bean);

        bean.setStatus("success");

        return "success";
    }

    public String insertion()
    {

        if(Validator.validation(bean))
        {
            if (DiscoveryService.insertion(bean))
            {
                bean.setStatus("success");
            }
            else
            {
                bean.setStatus("unsuccess");
            }
        }
        else
        {
            bean.setStatus("invalid");
        }

        return "success";
    }

    public String update()
    {
        if(Validator.validation(bean))
        {
            if (DiscoveryService.update(bean))
            {
                bean.setStatus("success");
            }
            else
            {
                bean.setStatus("unsuccess");
            }
        }
        else
        {
            bean.setStatus("invalid");
        }


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
