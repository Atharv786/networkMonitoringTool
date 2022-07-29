package helper;

import bean.DiscoveryBean;
import bean.MonitorBean;
import dao.DAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by atharv on 5/7/22.
 */
public class GetData
{
    public ArrayList<Object> getDashboardData()
    {
        ArrayList<Object> dashboardData = new ArrayList<>();

        try
        {
            String query = "select count(*), deviceStatus from monitorTable group by deviceStatus;";

            List<HashMap<String, String>> data = DAO.select(query, new ArrayList<>());

            int down = 0;

            List<Integer> availability = null;

            if (!data.isEmpty())
            {
                int up = 0, unreachable = 0, total;

                for (HashMap<String, String> row : data)
                {
                    String status = row.get("deviceStatus");

                    if (status.equals("UP"))
                    {
                        up = Integer.parseInt(row.get("count(*)"));
                    }
                    if (status.equals("Down"))
                    {
                        down = Integer.parseInt(row.get("count(*)"));
                    }
                    if (status.equals("Unknown"))
                    {
                        unreachable = Integer.parseInt(row.get("count(*)"));
                    }
                }

                total = up + down + unreachable;

                availability = new ArrayList<>(Arrays.asList(up, down, unreachable, total));
            }

            dashboardData.add(availability);


            query = "select count(*),deviceStatus,deviceType from monitorTable group by deviceType, deviceStatus";

            data = DAO.select(query, new ArrayList<>());


            List<HashMap<String, String>> monitorGroup = new ArrayList<>();

            if (!data.isEmpty())
            {
                int pingUp = 0, pingDown = 0, pingTotal, sshUp = 0, sshDown = 0, sshTotal;

                for (HashMap<String, String> row : data)
                {
                    String type = row.get("deviceType");

                    if (type.equals("Ping"))
                    {
                        if (row.get("deviceStatus").equals("UP"))
                        {
                            pingUp += Integer.parseInt(row.get("count(*)"));

                        } else if (row.get("deviceStatus").equals("Down"))
                        {
                            pingDown += Integer.parseInt(row.get("count(*)"));
                        }
                    } else
                    {
                        if (row.get("deviceStatus").equals("UP"))
                        {
                            sshUp += Integer.parseInt(row.get("count(*)"));

                        } else if (row.get("deviceStatus").equals("Down"))
                        {
                            sshDown += Integer.parseInt(row.get("count(*)"));
                        }
                    }
                }

                pingTotal = pingDown + pingUp;

                sshTotal = sshDown + sshUp;

                HashMap<String, String> ping = new HashMap<>();

                ping.put("deviceType", "Ping");

                ping.put("UP", String.valueOf(pingUp));

                ping.put("Down", String.valueOf(pingDown));

                ping.put("Total", String.valueOf(pingTotal));

                HashMap<String, String> ssh = new HashMap<>();

                ssh.put("deviceType", "SSH");

                ssh.put("UP", String.valueOf(sshUp));

                ssh.put("Down", String.valueOf(sshDown));

                ssh.put("Total", String.valueOf(sshTotal));

                monitorGroup.add(ping);

                monitorGroup.add(ssh);

                dashboardData.add(monitorGroup);
            }

            query = "select deviceIp, max(RTT_Time) as rtt from PingPOlling inner join monitorTable on PingPOlling.ID=monitorTable.deviceId where Time_Stamp in (select max(Time_Stamp) from PingPOlling inner join monitorTable on PingPOlling.ID=monitorTable.deviceId group by monitorTable.deviceIp) and RTT_Time!=-1 group by deviceIp order by max(RTT_Time) desc limit 5";

            List<HashMap<String, String>> dataTopRtt = DAO.select(query, new ArrayList<>());

            dashboardData.add(dataTopRtt);

            query = "select deviceIp, max(CPU) as cpu from SshDump inner join monitorTable on SshDump.ID=monitorTable.deviceId where TimeStamp in (select max(TimeStamp) from SshDump inner join monitorTable on SshDump.ID=monitorTable.deviceId group by monitorTable.deviceIp) and CPU!=-1 group by deviceIp order by CPU desc limit 5";

            List<HashMap<String, String>> dataTopCpu = DAO.select(query, new ArrayList<>());

            dashboardData.add(dataTopCpu);

            query = "select deviceIp, max(Memory) as Memory from SshDump inner join monitorTable on SshDump.ID=monitorTable.deviceId where TimeStamp in (select max(TimeStamp) from SshDump inner join monitorTable on SshDump.ID=monitorTable.deviceId group by monitorTable.deviceIp) and Memory!=-1 group by deviceIp order by Memory desc limit 5";

            List<HashMap<String, String>> dataTopMemory = DAO.select(query, new ArrayList<>());

            dashboardData.add(dataTopMemory);

            query = "select deviceIp, max(Disk) as Disk from SshDump inner join monitorTable on SshDump.ID=monitorTable.deviceId where TimeStamp in (select max(TimeStamp) from SshDump inner join monitorTable on SshDump.ID=monitorTable.deviceId group by monitorTable.deviceIp) and Disk!=-1 group by deviceIp order by Disk desc limit 5";

            List<HashMap<String, String>> dataTopDisk = DAO.select(query, new ArrayList<>());

            dashboardData.add(dataTopDisk);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return dashboardData;
    }

    public static ArrayList discovery()
    {
        ArrayList<DiscoveryBean> discoveryBeanList = new ArrayList<>();

        ArrayList<Object> values = new ArrayList<>();

        List<HashMap<String, String>> data = DAO.select("select * from addDevice", values);

        for (HashMap<String, String> row : data)
        {
            DiscoveryBean bean = new DiscoveryBean();

            bean.setId(row.get("id"));
            bean.setName(row.get("deviceName"));
            bean.setIp(row.get("deviceIP"));
            bean.setType(row.get("deviceType"));
            bean.setOperation("<button onclick='discovery.provisionRequest(this) 'data-id='" + row.get("id") + "' data-type='" + row.get("deviceType") + "' data-ip='" + row.get("deviceIP") + " 'data-name='" + row.get("deviceName") + "' class='provisionButton' style='width: 32%;'>Provision</button><button onclick='discovery.updateForm(this)' data-id='" +row.get("id")+ "' data-type='" + row.get("deviceType") + "' data-ip='" + row.get("deviceIP") + " 'data-name='" + row.get("deviceName") + "' class='updateButton' style='width: 32%'>Update</button><button onclick='discovery.deleteRequest(this)' data-id='"+row.get("id")+"' class='deleteButton' style='width: 32%'>Delete</button>");

            discoveryBeanList.add(bean);
        }

        return discoveryBeanList;
    }

    public static ArrayList monitor()
    {
        ArrayList<MonitorBean> monitorBeanList = new ArrayList<>();

        ArrayList<Object> values = new ArrayList<>();

        List<HashMap<String, String>> data = DAO.select("select * from monitorTable", values);

        for (HashMap<String, String> row : data)
        {
            MonitorBean bean = new MonitorBean();

            bean.setId(row.get("deviceId"));
            bean.setName(row.get("deviceName"));
            bean.setIp(row.get("deviceIP"));
            bean.setType(row.get("deviceType"));
            bean.setDeviceStatus(row.get("deviceStatus"));
            bean.setOperations("<button onclick='monitor.pollingRequest(this) 'data-id='" + row.get("deviceId") + "' data-ip='" + row.get("deviceIP") + "' data-type='" + row.get("deviceType") + "' class='monitorActionButton' style='width: 32%;'>Action</button><button onclick='monitor.deleteRequest(this)' data-id='" + row.get("deviceId") + "' data-type='" + row.get("deviceType") + "' class='deleteButton' style='width: 32%'>Delete</button>");

            monitorBeanList.add(bean);
        }

        return monitorBeanList;
    }

    public HashMap<String,Object> getPingStatistics(String id)
    {
        HashMap<String, Object> pingPollingData = null;

        pingPollingData = new HashMap<>();

        ArrayList<Object> availability = getAvailability(id);

        pingPollingData.put("pieChart", availability);


        String query = "select * from PingPOlling where ID=? ORDER BY Time_Stamp DESC limit 10";

        ArrayList<Object> values = new ArrayList<>(Arrays.asList(id));

        List<HashMap<String, String>> Data = DAO.select(query, values);


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

        try
        {
            sshStatistic = new HashMap<>();

            ArrayList<Object> availability = getAvailability(id);

            sshStatistic.put("pieChart", availability);

            String query = "select * from SshDump where ID=? ORDER BY TimeStamp DESC limit 10";

            ArrayList<Object> values = new ArrayList<>(Arrays.asList(id));

            List<HashMap<String, String>> barData = DAO.select(query, values);

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

                barData = DAO.select("select * from PingPOlling where ID=? ORDER BY Time_Stamp DESC limit 1", values);

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

            List<HashMap<String, String>> data = DAO.select(query, values);

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

    public static Boolean pingData(String ip)
    {
        Boolean pingStatus = false;

        ip = ip.replaceAll("\\s", "");

        String  result ="";

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

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null)
            {
                if (line.contains("errors") == true)
                {
                    result = line.substring(line.indexOf("errors") + 8, line.indexOf("%"));

                }
                else if (line.contains("received") == true)
                {
                    result = line.substring(line.indexOf("received") + 10, line.indexOf("%"));
                }

                if (line.contains("rtt") == true)
                {
                    String sub = line.substring(line.indexOf("=") + 2);

                    int index = sub.indexOf("/");

                    rtt = line.substring(line.indexOf("=") + 2, line.indexOf("=") + 2 + index);
                }
            }

            int packetLoss = Integer.parseInt(result);

            Date date = new Date();

            Timestamp timestamp = new Timestamp(date.getTime());

            if (packetLoss < 50)
            {
                pingStatus = true;
            }
            else
            {
                pingStatus = false;
            }

            int recievedPacket = 100 - packetLoss;

            recievedPacket /= 25;

            if (packetLoss == 100)
            {
                rtt = "0";
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return pingStatus;
    }
}
