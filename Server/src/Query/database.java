
package Query;

import java.io.Serializable;
import java.sql.*;


/**
 *
 * @author anuj
 */
public class database implements Serializable{
    
    private Connection conn;
    private Statement stmt;
    private ResultSet rst;
    
    
    public Connection getConnection()
    {
        //Use your own database credentials here 
        try{
            
            /*String url="jdbc:mysql://localhost/java";
            String user="root";
            String pass="";*/
            
            String url="jdbc:mysql://172.31.100.41/db20174030";
            String user="20174030";
            String pass="20174030";
            
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url,user,pass);
            
        }
        catch(SQLException e)
        {
            System.out.println("Error1: "+ e.getMessage());
            
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Error2: "+ e.getMessage());
        }
        finally
        {
            return conn;
        }
    }
    //Utility method to perform search query and return resultset
    public ResultSet searchQuery(String sql)
    {
        try{
            getConnection();
            stmt=conn.createStatement();
            rst=stmt.executeQuery(sql);
        }
        catch(SQLException e)
        {
            System.out.println("Error: "+ e.getMessage());
        }
        return rst;        
    }
    //Utility method to perform insert query
    public void InsertQuery(String sql)
    {
        try{
            getConnection();
            stmt=conn.createStatement();
            stmt.executeQuery(sql);
        }
        catch(SQLException e)
        {
            System.out.println("Error: "+ e.getMessage());
        }
        
    
    }
    //Utility method to perform update query
     public void updateQuery(String sql)
    {
         try{
            getConnection();
            stmt=conn.createStatement();
            stmt.executeUpdate(sql);
        }
        catch(SQLException e)
        {
            System.out.println("Error: "+ e.getMessage());
        }
        
    }
    public Connection getConnected()
    {
        return conn;
    }
     
}
