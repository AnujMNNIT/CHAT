
package Server;


/**
 *
 * @author anuj
 */


import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.Socket;
import javax.swing.JOptionPane;

public class ServerMain {
     /* Main class of server
    */
    public static void main(String args[]) {
       
        String response = JOptionPane.showInputDialog(null,
        "Enter port of server?",
        "Enter value",                                             //TO CREATE INPUT DIALOG BOX TO GET PORT NUMBER FROM USER TO CREATE PASSIVE SOCKET
        JOptionPane.QUESTION_MESSAGE);
         
        int port =Integer.parseInt(response);
        try{
        ServerSocket serverSocket = new ServerSocket(port);         //Creation of passive socket from server side
        ConnectedSockets sc=new ConnectedSockets();                 //Object Of connectedSockets class to store username and corresponding socket
        while(true)                                                //infinite loop to accept sockets from client
        {
            Socket clientSocket = serverSocket.accept();            
            System.out.println("Client connected");
            
            ServerThread st=new ServerThread(clientSocket,sc);     // Object to handle connected sockets
            st.start();//Thread call
        
        }
                
        } catch (IOException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
