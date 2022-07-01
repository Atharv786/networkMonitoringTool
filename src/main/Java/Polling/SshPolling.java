package Polling;

import dao.DAO;
import helper.SshConnection;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Created by atharv on 16/6/22.
 */
public class SshPolling extends RecursiveTask<Boolean>
{

    String id;

    String Ip;

    String Username;

    String Passowrd;


    public SshPolling(String id, String Ip)
    {
        this.id=id;

        this.Ip=Ip;
    }


    @Override
    protected Boolean compute()
    {
        DAO dao = new DAO();

        ArrayList<Object> sshPolling = new ArrayList<>();

        sshPolling.add(Ip);

        List<HashMap<String, String>> credentials= dao.select("select * from credentials where deviceIp=?", sshPolling);

        HashMap<Integer, String> data = new HashMap<>();

        ArrayList<Object> values= new ArrayList<>();

        data.put(0,"free -m | grep Mem | awk '{print $3}'");

        data.put(1,"free -m | grep Mem | awk '{print $2}'");

        data.put(2,"df -hT /home | grep dev | awk '{print $6}'");

        data.put(3,"df -hT /home | grep dev | awk '{print $3}'");

        data.put(4,"top -bn  2 | grep Cpu%");

        data.put(5,"uptime -p");

        for (HashMap<String, String> row: credentials)
        {
            Ip = row.get("deviceIP");

            Username = row.get("deviceUsername");

            Passowrd = row.get("devicePassword");
        }

        SshConnection sshConnection=new SshConnection(Ip, Username, Passowrd);

        String  result = sshConnection.executeCommands("free -m | grep Mem | awk '{print $3}'\n free -m | grep Mem | awk '{print $2}'\n df -hT /home | grep dev | awk '{print $6}' \n df -hT /home | grep dev | awk '{print $3}' \n top -bn  2 | grep Cpu\n uptime -p\n");

        values.add(id);


        int length = data.get(0).length();

        int index = result.lastIndexOf(data.get(0)) + length;

        result = result.substring(index);

        String str = result.substring(0, result.indexOf(Username));

        str = str.replaceAll("\\s", "");

        values.add(str);



        length = data.get(1).length();

        index = result.lastIndexOf(data.get(1)) + length;

        result = result.substring(index);

        str = result.substring(0, result.indexOf(Username));

        str = str.replaceAll("\\s", "");

        values.add(str);



        length = data.get(2).length();

        index = result.lastIndexOf(data.get(2)) + length;

        result = result.substring(index);

        str = result.substring(0, result.indexOf(Username)-1);

        str = str.replaceAll("\\s", "");

        values.add(str);



        length = data.get(3).length();

        index = result.lastIndexOf(data.get(3)) + length;

        result = result.substring(index);

        str = result.substring(0, result.indexOf(Username)-1);

        str = str.replaceAll("\\s", "");

        values.add(str);




        length = data.get(4).length();

        index = result.lastIndexOf(data.get(4)) + length;

        result = result.substring(index);

        str = result.substring(0, result.indexOf(Username));

        index = str.lastIndexOf(":") + 1;

        str = str.substring(index);

        str = str.substring(0, str.indexOf("us")-1);

        result = result.substring(result.indexOf(Username));

        str = str.replaceAll("\\s", "");

        values.add(str);




        length = data.get(5).length();

        index = result.lastIndexOf(data.get(5)) + length;

        result = result.substring(index);

        str = result.substring(0, result.indexOf(Username));

        values.add(str);



        Date date = new Date();

        values.add(new Timestamp(date.getTime()));


        values.add(Ip);


        System.out.println(values);

        dao.update("insert into SshDump(ID,Memory,TotalMemory,Disk,TotalDisk,CPU,UpTime,TimeStamp,IP)"+"values(?,?,?,?,?,?,?,?,?)",values);

        sshConnection.close();

        return true;
    }
}
