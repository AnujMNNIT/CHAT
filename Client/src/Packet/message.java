
package Packet;

import java.io.Serializable;

/**
 *
 * @author anuj
 */
public class message implements Serializable{
    private final String message;
    private final String sender;
    private final String receiver;
    public message(String message,String sender ,String receiver)
    {
        this.message=message;
        this.sender=sender;
        this.receiver=receiver;
    }
    public String getMessage()
    {
        return this.message;
    }
    public String getSender()
    {
        return this.sender;
    }
    public String getReceiver()
    {
        return this.receiver;
    }
}
