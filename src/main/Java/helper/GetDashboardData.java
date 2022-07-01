package helper;

import dao.DAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by atharv on 28/6/22.
 */
public class GetDashboardData
{
    public ArrayList<Object> getDashboardData()
    {
        ArrayList<Object> dashboardData = new ArrayList<>();

        try
        {
            DAO dao = new DAO();

            String query = "select count(*), deviceStatus from monitorTable group by deviceStatus;";

            List<HashMap<String, String>> data = dao.select(query, new ArrayList<>());

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

            data = dao.select(query, new ArrayList<>());


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

            List<HashMap<String, String>> dataTopRtt = dao.select(query, new ArrayList<>());

            dashboardData.add(dataTopRtt);

            query = "select deviceIp, max(CPU) as cpu from SshDump inner join monitorTable on SshDump.ID=monitorTable.deviceId where TimeStamp in (select max(TimeStamp) from SshDump inner join monitorTable on SshDump.ID=monitorTable.deviceId group by monitorTable.deviceIp) and CPU!=-1 group by deviceIp order by CPU desc limit 5";

            List<HashMap<String, String>> dataTopCpu = dao.select(query, new ArrayList<>());

            dashboardData.add(dataTopCpu);

            query = "select deviceIp, max(Memory) as Memory from SshDump inner join monitorTable on SshDump.ID=monitorTable.deviceId where TimeStamp in (select max(TimeStamp) from SshDump inner join monitorTable on SshDump.ID=monitorTable.deviceId group by monitorTable.deviceIp) and Memory!=-1 group by deviceIp order by Memory desc limit 5";

            List<HashMap<String, String>> dataTopMemory = dao.select(query, new ArrayList<>());

            dashboardData.add(dataTopMemory);

            query = "select deviceIp, max(Disk) as Disk from SshDump inner join monitorTable on SshDump.ID=monitorTable.deviceId where TimeStamp in (select max(TimeStamp) from SshDump inner join monitorTable on SshDump.ID=monitorTable.deviceId group by monitorTable.deviceIp) and Disk!=-1 group by deviceIp order by Disk desc limit 5";

            List<HashMap<String, String>> dataTopDisk = dao.select(query, new ArrayList<>());

            dashboardData.add(dataTopDisk);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return dashboardData;
    }
}
