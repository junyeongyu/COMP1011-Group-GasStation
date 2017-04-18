package com.group.gasstation.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class which can connect database.
 */
public class DBManager
{
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    
    public static int SUCCESS = 1;
    public static int FAIL = -1;
    
    /**
     * Get single row data from database
     * @param query
     * @return 
     */
    public Map<String,Object> getObject(String query)
    {
        List list = getList(query);
        if (list == null || list.isEmpty())
        {
            return null;
        }
        
        return getList(query).get(0);
    }
    
    /**
     * Get multi row data from database
     * @param query
     * @return 
     */
    public List<Map<String,Object>> getList(String query)
    {
        List<Map<String,Object>> list = new ArrayList<>();
        try
        {
            preprocess(); // connect database
            
            // create statement object from connection
            stmt = conn.createStatement();
            
            // execute the statement and store the resultset.
            rs = stmt.executeQuery(query);

            ResultSetMetaData rsMetaData = rs.getMetaData();
            int numOfCols = rsMetaData.getColumnCount();
            
            // while loop to iterate the resultset
            while (rs.next())
            {
                Map map = new HashMap();
                for (int i = 1; i <= numOfCols; i++)
                {
                    String columnName = rsMetaData.getColumnName(i);
                    Object value = rs.getObject(i);
                    System.out.println(columnName + ": " + value);
                    map.put(columnName, value);
                }
                list.add(map);
            } // end while
        }
        catch (SQLException error)
        {
            error.printStackTrace();
        }
        catch (Exception error)
        {
            error.printStackTrace();
        }
        finally
        {
            try
            {
                if (rs != null) rs.close();
                stmt.close();
                conn.close();
            }
            catch (SQLException ex)
            {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return list;
    }
    
    /**
     * Execute query for update/delete/insert
     * @param query
     * @return SUCCESS when there is no error
     */
    public int execute(String query)
    {
        int result = FAIL; // error
        
        try
        {
            preprocess();
            stmt = conn.createStatement();
            stmt.executeUpdate(query); // UPDATE EMPLOYEE colum='aaa' where id = 'id'
            result = SUCCESS; // sucess
        }
        catch (SQLException error)
        {
            error.printStackTrace();
        }
        catch (Exception error)
        {
            error.printStackTrace();
        }
        finally
        {
            try
            {
                stmt.close();
                conn.close();
            }
            catch (SQLException ex)
            {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;  
    }

    /**
     * Getting connection to database
     */
    private void preprocess()
    {
        // Database=acsm_855816b26cc82d2;Data Source=us-cdbr-azure-southcentral-e.cloudapp.net;User Id=bdd2a9f50ea66c;Password=aa83c352
        final String DB_URL = "jdbc:mysql://sql.computerstudi.es:3306/gc200328206?user=gc200328206&password=?rVT4!2t";
        
        try
        {
            // try to create connection
            conn = DriverManager.getConnection(DB_URL);
        }
        catch (SQLException error)
        {
            error.printStackTrace();
            
            try
            {
                conn.close();
            }
            catch (SQLException ex)
            {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
}
