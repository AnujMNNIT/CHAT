
package Server;

import Query.QueryHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anuj
 */
public class ServerThread extends Thread{
    
    private Socket clientSocket ;
    private String user;
    private ConnectedSockets sc;
    public ServerThread(Socket clientSocket,ConnectedSockets sc)
    {
        this.clientSocket=clientSocket;
        this.sc=sc;
    }
    @Override
     public void run() 
    {
            QueryHandler queryhandler=new QueryHandler();       
            /*Object creation of queryHandler class ,this object will
              be sent to client using object output stream */
            
            
            OutputStream outputStream = null;
        try {
            outputStream = clientSocket.getOutputStream();  //output Stream for object
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
            ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(outputStream); //Object output stream to send query handler object
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            objectOutputStream.writeObject(queryhandler);          //Here server is sending query handler object to client
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
            
            /* Here input stream is created to accept user name of connected socket which will be
              sent by client via Outputstream
        */
            InputStream inputStream = null;
        try {
            inputStream = clientSocket.getInputStream();  //input stream to accept user name sent by client
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));  //Object of BufferedReader class to read inputStream
        try {
            user=reader.readLine();  //Here username is getting stored in user variable
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
            if("*****".equals(user))
            {
                
            }
            else
            {
            sc.addSocket(user, clientSocket);  //Here user is added in connectedSocket directory/map  USER-->SOCKET
            ServerWorker worker=new ServerWorker(clientSocket,sc);  //A new object is created of ServerWorker class to handle connected use 
            worker.start();                            //Thread calling to handle 
            }
    }
    
}
