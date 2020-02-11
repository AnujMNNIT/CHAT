
package Server;

import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import Packet.Filestructure;
import Packet.message;

/**
 *
 * @author anuj
 */
public class ServerWorker extends Thread{
   
    private final Socket clientSocket;
    private ConnectedSockets sc;
    public ServerWorker(Socket clientSocket,ConnectedSockets sc)
    {
        this.clientSocket=clientSocket;
        this.sc=sc;
    }
    
    public void run()  //Thread to handle accepted connections
    {
        try 
        {
            handleClientSocket(); 
        }
        catch(IOException | InterruptedException e){
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public void send(message m,Socket newclientSocket)  //Method to send Message packet to assigned reciever's socket
    {
        try{
            OutputStream outputStream = newclientSocket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(m);
        }
         catch(IOException e)
        {
            e.printStackTrace();
        }
    }
       public void send(Filestructure file,Socket newclientSocket) //Method to send FileStructure packet to assigned reciever's socket
    {
        try{
            OutputStream outputStream = newclientSocket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(file);
        }
         catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public Object receive() throws ClassNotFoundException  //Method to recieve packets(Objects) from client 
    {
        Object m = null ;
        try{
        InputStream inputStream=clientSocket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        m=objectInputStream.readObject();
       
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return m;  
    }
    private void handleClientSocket() throws IOException,InterruptedException, ClassNotFoundException
    {
       
        while(true)
        {
                Object obj=receive(); //Here objects is recieved and stored in 
                if(obj==null)
                {
                    System.out.println("null hai");
                }
                System.out.println(obj.getClass());
                if(obj instanceof message) 
                {
                message m=(message)obj;//Object is typecasted into message packet(Object)
                send(m,sc.getSocket(m.getReceiver()));
                /*Here server cheks reciever name from packets and checks corresponding socket 
                and further send to reciever socket
                */
                
                }
                else
                {
                    Filestructure file=(Filestructure)obj;
                    send(file,sc.getSocket(file.getReciever()));
                  /*Here server cheks reciever name from packets and checks corresponding socket 
                and further send to reciever socket
                */
                    System.out.println("file rolled back from server");
                }
        }
    }
}
