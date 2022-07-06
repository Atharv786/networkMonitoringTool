package helper;

import dao.DAO;

import java.sql.Timestamp;

import java.util.*;

/**
 * Created by atharv on 28/6/22.
 */
public class GetPollingData
{

    public HashMap<String,Object> getPingStatistics(String id)
    {
        HashMap<String, Object> pingPollingData = null;

        DAO dao = new DAO();

        pingPollingData = new HashMap<>();

        ArrayList<Object> availability = getAvailability(id);

        pingPollingData.put("pieChart", availability);


        String query = "select * from PingPOlling where ID=? ORDER BY Time_Stamp DESC limit 10";

        ArrayList<Object> values = new ArrayList<>(Arrays.asList(id));

        List<HashMap<String, String>> Data = dao.select(query, values);


        if (!Data.isEmpty())
        {
            ArrayList<Object> Values = new ArrayList<>();

            ArrayList<Object> TimeStamp = new ArrayList<>();

            for (HashMap<String, String> row : Data)
            {
                Values.add(row.get("RecievedPacket"));

                TimeStamp.add(row.get("Time_Stamp"));
            }

            pingPollingData.put("Values", Values);

            pingPollingData.put("TimeStamp", TimeStamp);

            pingPollingData.put("LiveData", new ArrayList<>(Arrays.asList("4", Data.get(0).get("RecievedPacket"), Data.get(0).get("PacketLoss"), Data.get(0).get("RTT_Time"))));
        }

        return pingPollingData;
    }

    public HashMap<String,Object> getSshStatistics(String id)
    {
        HashMap<String, Object> sshStatistic = null;

        DAO dao = new DAO();

        try
        {
            sshStatistic = new HashMap<>();

            ArrayList<Object> availability = getAvailability(id);

            sshStatistic.put("pieChart", availability);

            String query = "select * from SshDump where ID=? ORDER BY TimeStamp DESC limit 10";

            ArrayList<Object> values = new ArrayList<>(Arrays.asList(id));

            List<HashMap<String, String>> barData = dao.select(query, values);

            if (!barData.isEmpty())
            {
                ArrayList<Object> value = new ArrayList<>();

                ArrayList<Object> TimeStamp = new ArrayList<>();

                for (HashMap<String, String> row : barData)
                {
                    String str = row.get("CPU");

                    value.add(str);

                    TimeStamp.add(row.get("TimeStamp"));
                }

                sshStatistic.put("Values", value);

                sshStatistic.put("TimeStamp", TimeStamp);

                sshStatistic.put("LiveData", new ArrayList<>(Arrays.asList(barData.get(0).get("CPU"), barData.get(0).get("Memory"), barData.get(0).get("Disk"), barData.get(0).get("UpTime"), barData.get(0).get("TotalDisk"), barData.get(0).get("TotalMemory"))));

                barData = dao.select("select * from PingPOlling where ID=? ORDER BY Time_Stamp DESC limit 1", values);

                sshStatistic.put("PingData", new ArrayList<>(Arrays.asList("4", barData.get(0).get("RecievedPacket"), barData.get(0).get("PacketLoss"), barData.get(0).get("RTT_Time"))));

            }

        } catch (Exception e)
        {
            e.printStackTrace();

        }

        return sshStatistic;
    }

    public ArrayList<Object> getAvailability(String id)
    {

        DAO dao = new DAO();

        ArrayList<Object> availability = null;

        try
        {
            Calendar calendar = Calendar.getInstance();

            Date date = new Date();

            calendar.setTime(date);

            calendar.add(Calendar.DATE, -1);

            Date lastDay = calendar.getTime();

            Timestamp lastDayTimestamp = new Timestamp(lastDay.getTime());

            Timestamp currentTimeStamp = new Timestamp(date.getTime());

            String query = "select count(*),PacketLoss from PingPOlling where Time_Stamp BETWEEN '" + lastDayTimestamp + "' AND '" + currentTimeStamp + "' AND ID=? group by PacketLoss";

            ArrayList<Object> values = new ArrayList<>(Arrays.asList(id));

            List<HashMap<String, String>> data = dao.select(query, values);

            if (!data.isEmpty())
            {
                int pieUp = 0, pieDown = 0;

                for (HashMap<String, String> row : data)
                {
                    int packetloss = Integer.parseInt(row.get("PacketLoss"));

                    if (packetloss < 50)
                    {
                        pieUp += Integer.parseInt(row.get("count(*)"));

                    }
                    else
                    {
                        pieDown += Integer.parseInt(row.get("count(*)"));
                    }
                }

                pieUp = (int) (((float) pieUp / (float) (pieDown + pieUp) * 1.0) * 100);

                pieDown = 100 - pieUp;

                availability = new ArrayList<>();

                availability.add(pieUp);

                availability.add(pieDown);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }

        return availability;
    }
}


