package action;

import service.DiscoveryService;
import bean.DiscoveryBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * Created by atharv on 21/4/22.
 */
public class DiscoveryAction extends ActionSupport implements ModelDriven<DiscoveryBean>
{
    DiscoveryBean bean=new DiscoveryBean();


    public String discovery()
    {
        DiscoveryService.addDiscovery(bean);
        bean.setStatus("discoverAddSuccessfully");

        return "discovery";
    }

    @Override
    public DiscoveryBean getModel() {
        return bean;
    }

}
