
package Server;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author anuj
 */

/* A utility Class to store sockets for connected users. Here Map has been used to map users with corresponding
Sockets like a Telephone Directory*/

public class ConnectedSockets {
    private final Map<String,Socket> mp ;
    public ConnectedSockets(){
        mp=new HashMap<>();
    }
    public void addSocket(String uname ,Socket socket)
    {
        mp.put(uname,socket);
        //System.out.println(uname+" "+mp.get(uname).getRemoteSocketAddress().toString());
    }
    
    public Socket getSocket(String uname)
    {
       // System.out.println(uname+" "+mp.get(uname).getRemoteSocketAddress().toString());
        return mp.get(uname);
    }    
}
