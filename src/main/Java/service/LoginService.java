package service;

import dao.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by atharv on 14/4/22.
 */
public class LoginService
{

    public static boolean login(String username, String password)
    {
        DAO dao=new DAO();

        ArrayList<Object> login= new ArrayList<>();

        List<HashMap<String, String>> flag;

        login.add(username);

        login.add(password);

        flag = dao.select("select * from loginTable where username=? and password=?", login);

        if(!flag.isEmpty())
        {
            return  true;
        }

        return false;
    }

}
