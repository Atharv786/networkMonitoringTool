package helper;

import com.jcraft.jsch.*;

import java.io.*;
import java.util.Properties;

public class SshConnection
{
    private Session session;

    private ChannelShell channel;

    private ChannelExec execChannel;

    private final String username;

    private final String password;

    private final String hostname;

    public SshConnection(String Hostname, String Username, String Password)
    {
        hostname = Hostname;

        username = Username;

        password = Password;
    }

    private Session getSession()
    {
        if (session == null)
        {
            session = connect(hostname, username, password);
        }
        return session;
    }

    private Channel getChannel(String ChannelType)
    {

        System.out.println(ChannelType);

        try
        {
            session = getSession();

            if (session != null)
            {
                if(ChannelType.equals("exec"))
                {
                    if (execChannel == null)
                    {
                        execChannel = (ChannelExec) session.openChannel("exec");

                        execChannel.connect(10000);
                    }
                }
                else
                {
                    if (channel == null)
                    {
                        channel = (ChannelShell) session.openChannel("shell");

                        channel.connect(10000);
                    }
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        if(ChannelType.equals("exec"))
        {
            return execChannel;
        }
        else
        {
            return channel;
        }
    }


    private Session connect(String hostname, String username, String password)
    {
        JSch jSch = new JSch();

        try
        {
            Properties config = new Properties();

            config.put("StrictHostKeyChecking", "no");

            session = jSch.getSession(username, hostname, 22);

            session.setConfig(config);

            session.setPassword(password);

            session.connect(10000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return session;
    }


    private void sendCommands(Channel channel, String commands)
    {
        BufferedWriter bufferedWriter = null;

        try
        {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(channel.getOutputStream()));

            bufferedWriter.write(commands);

            bufferedWriter.write("exit\n");

            bufferedWriter.flush();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (bufferedWriter != null)
                {
                    bufferedWriter.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


    private String readChannelOutput(Channel channel) throws IOException {

        BufferedReader bufferedReader = null;

        String answer = "";

        if(channel.isClosed())
        {
            System.out.println("Channel is closed");
        }
        else
        {
            System.out.println("Channel is open");
        }

        try
        {
            bufferedReader = new BufferedReader(new InputStreamReader(channel.getInputStream()));

            String temp;

            while ((temp = bufferedReader.readLine()) != null && !temp.contains("logout"))
            {
                answer += temp;
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (bufferedReader != null)
            {
                bufferedReader.close();
            }
        }
        return answer;
    }


    public String executeCommands(String commands, String ChannelType)
    {
        try
        {
            Channel channel = getChannel(ChannelType);

            if (channel != null)
            {
                sendCommands(channel, commands);

                String output = readChannelOutput(channel);

                if (!channel.isClosed())
                {
                    Thread.sleep(10000);
                }

                return output;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }


    public void close()
    {
        if (channel != null && channel.isConnected())
        {
            channel.disconnect();
        }

        if (session != null && session.isConnected())
        {
            session.disconnect();
        }
    }

}