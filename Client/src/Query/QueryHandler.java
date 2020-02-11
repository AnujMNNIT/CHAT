
package Query;


import java.io.Serializable;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author anuj
 */
/* Special purpose class to Handle Request of Client 
After creation of Socket server will send object of this class which will used by the 
Client to invoke its method */

public class QueryHandler implements Serializable{
    private final database db;
    public QueryHandler()
    {
         db=new database();
    }
    public  String  LoginHandler(String user,String pass,String ip) throws SQLException, UnknownHostException
    {
        /*This method will check whether A particular user exists or not in database and if exists then whether it is online or offline*/
        
        ResultSet rs = db.searchQuery("SELECT * FROM user WHERE UName='"+user+"' AND Password='"+pass+"'") ;
        if(rs.isBeforeFirst())
        {
            while(rs.next()){
                if(rs.getString(3).equalsIgnoreCase(user)&&rs.getString(4).equalsIgnoreCase(pass))
                {
               
                    if(rs.getBoolean(5)==true)
                    {
                        return "ALREADY_LOGGED_IN AT IP "+rs.getString(6);   //When user is already logged in at some other system
                    }
                    else
                    {
                        db.updateQuery("UPDATE user SET online=true,IP='"+ip+"'WHERE UName='"+user+"' AND Password='"+pass+"'") ;
                        System.out.println("true from loginhandler");
                        return "LOGGED_IN";                                   //If user exists but not online
                    }   
                }
            }
        }
         System.out.println("true from loginhandler");
        return "WRONG_CREDENTIALS";  //When user doesn't exists
    }
    public boolean RegisterHandler(String name,String uname,String password ,String email) throws SQLException
    {
        /* This Method is used to register a particular user*/
         ResultSet rs = db.searchQuery("SELECT * FROM user WHERE Email='"+email+"'") ;
            if(rs.isBeforeFirst())
            {
                return false;

            }
            else
            {
                String query = " insert into user (Name, Email, UName, Password, online,IP)"
            + " values (?, ?, ?, ?, ?, ?)";

         
            PreparedStatement preparedStmt = db.getConnected().prepareStatement(query);
            preparedStmt.setString (1, name);
            preparedStmt.setString (2, email);
            preparedStmt.setString (3,uname);
            preparedStmt.setString(4, password);
            preparedStmt.setBoolean(5, false);
            preparedStmt.setString(6, "");
            preparedStmt.execute();
            return true;
           
            }
    }
    public  String OnlineHandler(String friend) throws SQLException
    {
        /* This method is used to check whether a particular user is online or not as requested by client*/
         ResultSet rs = db.searchQuery("SELECT * FROM user WHERE UName='"+friend+"'") ;
        if(rs.isBeforeFirst())
        {
            while(rs.next()){
                if(rs.getString(5).equalsIgnoreCase("1"))
                {
                    
                   return "CONNECTED";
                    
                }
                else
                {
                    return "OFFLINE";
                }
            }
        }
        return "NOT_EXISTS";
    }
    public void LogoutHandler(String user)
    {
         db.updateQuery("UPDATE user SET online=false WHERE UName='"+user+"'") ;
         
    }
       
}
