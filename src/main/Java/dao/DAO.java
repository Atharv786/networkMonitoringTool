package dao;

import helper.ConnectionPoolHandler;

import java.sql.*;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

public class DAO
{
    public static List<HashMap<String, String>> select(String query, ArrayList<Object> values)
    {
        Connection connection = ConnectionPoolHandler.getConnection();

        PreparedStatement preparedStatement = null;

        List<HashMap<String, String>> data = null;

        try
        {
            if (connection != null && !connection.isClosed())
            {
                preparedStatement = connection.prepareStatement(query);

              for (int i=0; i<values.size(); i++)
              {
                  if (values.get(i).getClass() == Integer.class)
                  {
                      preparedStatement.setInt(i+1, (Integer) values.get(i));
                  }
                  else if (values.get(i).getClass() == String.class)
                  {
                      preparedStatement.setString(i+1, (String) values.get(i));

                  } else if (values.get(i).getClass() == Timestamp.class)
                  {
                      preparedStatement.setTimestamp(i + 1, (Timestamp) values.get(i));
                  }
              }

              ResultSet resultSet = preparedStatement.executeQuery();

              ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

              int columnCount = resultSetMetaData.getColumnCount();

              data = new ArrayList<>();

              while (resultSet.next())
              {
                  HashMap<String, String> row = new HashMap<>();

                  for (int j = 1; j <= columnCount; j++)
                  {
                      row.put(resultSetMetaData.getColumnName(j), resultSet.getString(j));
                  }

                  data.add(row);
              }


            }
            else
            {
                throw new Exception("Cannot get connection OR Connection is closed");
            }

        }
        catch (SQLException e)
        {
/*
            bean.setStatus = "unsuccess";
*/

            e.printStackTrace();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(connection != null)
                {
                    ConnectionPoolHandler.releaseConnection(connection);
                }

                if (preparedStatement != null)
                {

                        preparedStatement.close();

                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        return data;
    }

    public static int update(String query, ArrayList<Object> values)
    {

        Connection connection = ConnectionPoolHandler.getConnection();

        PreparedStatement preparedStatement = null;

        int affectedRow = 0;

        try
        {
            if (connection != null && !connection.isClosed())
            {
                preparedStatement = connection.prepareStatement(query);

                for (int i=0; i<values.size(); i++)
                {
                    if (values.get(i).getClass() == Integer.class)
                    {
                        preparedStatement.setInt(i+1, (Integer) values.get(i));

                    }
                    else if (values.get(i).getClass() == String.class)
                    {
                        preparedStatement.setString(i+1, (String) values.get(i));

                    }
                    else if (values.get(i).getClass() == Timestamp.class)
                    {
                        preparedStatement.setTimestamp(i+1, (Timestamp) values.get(i));
                    }
                }

                affectedRow = preparedStatement.executeUpdate();
            }
            else
            {
                throw new Exception("Cannot get connection OR Connection is closed");
            }

        }
        catch (SQLIntegrityConstraintViolationException e)
        {
            return -1;

        } catch (SQLException e)
        {
            e.printStackTrace();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionPoolHandler.releaseConnection(connection);

            try
            {
                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        return affectedRow;
    }

}