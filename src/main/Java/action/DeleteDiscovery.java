package action;

import bean.DeleteDiscoveryBean;
import service.DeleteDiscoveryService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DeleteDiscovery extends ActionSupport implements ModelDriven<DeleteDiscoveryBean>
{
    DeleteDiscoveryBean bean=new DeleteDiscoveryBean();


    public String deleteDiscoveryAction()
    {
        if(DeleteDiscoveryService.deleteDiscoveryService(bean))
        {
            bean.setStatus("deletionSuccessfull");
        }
        else
        {
            bean.setStatus("deletionUnsuccessfull");
        }


        return "deletionSuccessfull";
    }

    @Override
    public DeleteDiscoveryBean getModel() {
        return bean;
    }
}
