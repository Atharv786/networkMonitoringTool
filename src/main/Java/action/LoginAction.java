package action;

import bean.LoginBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import service.LoginService;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by atharv on 14/4/22.
 */
public class LoginAction extends ActionSupport implements ModelDriven<LoginBean>,SessionAware {

    private SessionMap<String, Object> sessionMap;

    private LoginBean bean = new LoginBean();

    public String login() {
        HttpSession session = ServletActionContext.getRequest().getSession(true);

        if (LoginService.login(bean.getUsername(), bean.getPassword())) {
            bean.setStatus("success");
            sessionMap.put("username",bean.getUsername());
        } else
            {
            bean.setStatus("loginUnsuccessful");
        }
        return "login";
    }

    @Override
    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap<String, Object>) map;
    }

    public String logout()
    {
        sessionMap.invalidate();

        sessionMap.remove("username");

        return "logout";
    }

    @Override
    public LoginBean getModel() {
        return bean;
    }
}
