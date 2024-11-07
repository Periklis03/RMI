import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
    private String username;
    private static int generator=1000;
    private int authToken;
    ArrayList<Message> messagebox=new ArrayList<>();

    public Account(String username) {
        this.username = username;
        this.authToken=generator;
        generator=generator+2;
    }
    public void addMessage(Message k)
    {
        messagebox.add(k);
    }
    public int getAuthToken() {
        return authToken;
    }

    public String getUsername() {
        return username;
    }

    public List<Message> getMessagebox() {
        return messagebox;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthToken(int authToken) {
        this.authToken = authToken;
    }

    public void setMessagebox(ArrayList<Message> messagebox) {
        this.messagebox = messagebox;
    }
}
