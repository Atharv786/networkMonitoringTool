package action;

import bean.DashboardBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import service.DashboardService;

public class DashBoardAction extends ActionSupport implements ModelDriven<DashboardBean>
{
    DashboardBean bean=new DashboardBean();


    public String loadDashboard()
    {
        try
        {
            DashboardService.loadDashboard(bean);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return "success";
    }

    @Override
    public DashboardBean getModel() {
        return bean;
    }
}
