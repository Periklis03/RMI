import java.io.Serializable;

public class Message implements Serializable {
    private boolean isRead=false;
    private String sender;
    private String Receiver;
    private String body;
    private static int temp=0;
    private int id;

    public Message(String sender, String receiver, String body) {
        this.sender = sender;
        this.Receiver = receiver;
        this.body = body;
        this.id=temp;
        temp=temp+1;
    }


    public int getId() {return id;}

    public boolean getIsRead() {
        return this.isRead;
    }

    public String getReceiver() {
        return this.Receiver;
    }

    public String getBody() {
        return this.body;
    }

    public String getSender() {
        return this.sender;
    }

    public void setRead(boolean read) {
        this.isRead = read;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.Receiver = receiver;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
