package action;

import bean.DeleteMonitorBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import service.DeleteMonitorService;

public class DeleteMonitor extends ActionSupport implements ModelDriven<DeleteMonitorBean>
{
    DeleteMonitorBean bean=new DeleteMonitorBean();


    public String deleteMonitorAction()
    {

        if(DeleteMonitorService.deleteMonitorService(bean))
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
    public DeleteMonitorBean getModel() {
        return bean;
    }
}
