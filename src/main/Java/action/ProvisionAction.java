package action;


import bean.ProvisionBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import service.ProvisionService;
import util.MultipleDiscovery;

public class ProvisionAction extends ActionSupport implements ModelDriven<ProvisionBean>
{

    private ProvisionBean bean = new ProvisionBean();

    public String provisionAction()
    {
        MultipleDiscovery request=new MultipleDiscovery();

        request.sendRequest(bean.getIp());

        if(ProvisionService.provisionService(bean))
        {
            bean.setStatus("provisionSuccessfull");
        }
        else
        {
            bean.setStatus("provisionUnsuccessfull");
        }

        return "provision";
    }

    @Override
    public ProvisionBean getModel() {
        return bean;
    }
}
