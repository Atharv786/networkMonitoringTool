package util;

import dao.DAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class PingDevice {


    public static boolean pingDevice(String ip, String id) {

        boolean flag=false;

        ip = ip.replaceAll("\\s", "");

        String  s="";

        String rtt="";

        ArrayList<String> arrayList= new ArrayList<>();

        arrayList.add("ping");

        arrayList.add("-c");

        arrayList.add("5");

        arrayList.add(ip);

        ProcessBuilder processBuilder = new ProcessBuilder();

        processBuilder.command(arrayList);

        try {

            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null)
            {
                if(line.contains("errors")==true)
                {
                    s = line.substring(line.indexOf("errors") + 8, line.indexOf("%"));

                }
                else if(line.contains("received")==true)
                {
                    s = line.substring(line.indexOf("received") + 10, line.indexOf("%"));
                }

                if(line.contains("rtt")==true)
                {
                    String sub=line.substring(line.indexOf("=")+2);

                    int index=sub.indexOf("/");

                    rtt = line.substring(line.indexOf("=")+2, line.indexOf("=")+2+index);
                }
            }

            int packetLoss = Integer.parseInt(s);

            Date date = new Date();

            Timestamp timestamp = new Timestamp(date.getTime());

            if(packetLoss<50)
            {
                flag= true;
            }
            else
            {
                flag= false;
            }

            int recievedPacket = 100-packetLoss;

            recievedPacket/=25;

            if(packetLoss==100)
            {
                rtt = "0";
            }

            ArrayList<Object> data = new ArrayList<>();

            ArrayList<Object> data1 = new ArrayList<>();

            data.add(id);

            data.add(ip);

            data.add(packetLoss);

            data.add(recievedPacket);

            data.add(rtt);

            data.add(timestamp);

            String status = null;

            if(packetLoss<=3)
            {
                status="UP";
            }
            else
            {
                status="Down";
            }

            data1.add(status);

            data1.add(id);

            DAO dao= new DAO();

            dao.update("insert into PingPOlling(ID,IP,PacketLoss,RecievedPacket,RTT_Time,Time_Stamp)"+"values(?,?,?,?,?,?)", data);

            dao.update("update monitorTable set deviceStatus=? WHERE deviceId=?",data1);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return flag;
    }

}
