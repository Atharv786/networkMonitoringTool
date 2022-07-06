package helper;

import bean.DiscoveryBean;

/**
 * Created by atharv on 5/7/22.
 */
public class Validator
{
    public static Boolean validation(DiscoveryBean bean)
    {
        Boolean result;

        if(bean.getType().equals("SSH"))
        {
            if(bean.getName().equals("") || bean.getIp().equals("") || bean.getUsername().equals("") || bean.getPassword().equals(""))
            {
                result = false;
            }
            else
            {
                result = true;
            }
        }
        else
        {
            if(bean.getName().equals("") || bean.getIp().equals(""))
            {
                result = false;
            }
            else
            {
                result = true;
            }
        }

        return result;
    }
}
