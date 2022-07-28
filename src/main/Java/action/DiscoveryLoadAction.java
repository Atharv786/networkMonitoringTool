package action;

import bean.DiscoveryBean;
import bean.DiscoveryLoadBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import service.DiscoveryService;

/**
 * Created by atharv on 28/7/22.
 */
public class DiscoveryLoadAction extends ActionSupport implements ModelDriven<DiscoveryBean>
{

    DiscoveryBean bean = new DiscoveryBean();

    public String load()
    {
        DiscoveryService.load(bean);

        return "success";
    }

    @Override
    public DiscoveryBean getModel() {
        return bean;
    }
}
