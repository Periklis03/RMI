import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class MessagingServer extends UnicastRemoteObject implements MessagingServerInterface {
    private Map<String, Account> accounts = new HashMap<>();

    protected MessagingServer() throws RemoteException {
        super();
    }

    @Override
    public int createAccount(String username) {
        if (accounts.containsKey(username)) {
            System.out.println("Sorry, the user already exists");
            return -1;
        } else {
            if(!username.matches("^[a-zA-Z0-9_]+$"))
            {return 1;}
            Account acc = new Account(username);
            accounts.put(username, acc);
            return acc.getAuthToken();
        }

    }

    public Map<String, Account> showAccounts() {
        return accounts;
    }

    public int accountExists(int auth) {
        int flag = -1;
        for (String key : accounts.keySet()) {
            Account value = accounts.get(key);
            if (value.getAuthToken() == auth) {
                flag = 1;
            }
        }
        return flag;
    }

    public int accountExists2(String name) {
        int flag = -1;
        for (String key : accounts.keySet()) {
            if (key.equals(name)) {
                flag = 1;
                break;
            }
        }
        return flag;
    }

    public String fromAuthtoName(int auth) {

        for (String key : accounts.keySet()) {
            Account value = accounts.get(key);
            if (value.getAuthToken() == auth) {
                return key;
            }
        }
        return null;
    }

    public int sendMessage(int auth, String recipient, String message) {
        if (accountExists(auth) == -1) {
            return 0;
        } else {
            if (accountExists2(recipient) == -1) {
                return -1;
            } else {
                Message mes = new Message(fromAuthtoName(auth), recipient, message);
                for (String key : accounts.keySet()) {
                    Account value = accounts.get(key);
                    if (value.getUsername().equals(recipient)) {
                        value.addMessage(mes);
                    }
                }
                return 1;
            }
        }
    }

    public String showInbox(int auth) {
        String nam = fromAuthtoName(auth);
        String inbox = "";
        for (String key : accounts.keySet()) {
            Account value = accounts.get(key);
            if (key.equals(nam)) {
                if (value.getMessagebox().isEmpty()) {
                    return "Empty Inbox";
                }
                for (int i = 0; i < value.getMessagebox().size(); i++) {
                    String read = "*";
                    if (value.getMessagebox().get(i).getIsRead()) {
                        read = " ";
                    }
                    inbox = inbox + value.getMessagebox().get(i).getId() + ". from: " + value.getMessagebox().get(i).getSender() + read + "\n";
                }
                return inbox;
            }
        }
        return "";
    }

    public String ReadMessage(int auth, int id) {

        String nam = fromAuthtoName(auth);
        for (String key : accounts.keySet()) {
            Account value = accounts.get(key);
            if (key.equals(nam)) {
                for (int i = 0; i < value.getMessagebox().size(); i++) {
                    if (value.getMessagebox().get(i).getId() == (id)) {
                        String sen = value.getMessagebox().get(i).getSender();
                        String mess = value.getMessagebox().get(i).getBody();
                        value.getMessagebox().get(i).setRead(true);
                        return "(" + sen + ") " + mess;
                    }
                }
            }
        }
        return "Message" + " " + id + " does not exist";
    }

    public String DeleteMessage(int auth, int id) {
        String nam = fromAuthtoName(auth);
        for (String key : accounts.keySet()) {
            Account value = accounts.get(key);
            if (key.equals(nam)) {
                for (int i = 0; i < value.getMessagebox().size(); i++) {
                    if (value.getMessagebox().get(i).getId() == (id)) {
                        value.getMessagebox().remove(i);
                        return "OK";
                    }
                }
            }
        }
        return "Message does not exit";
    }

    public int isValid(int auth) {
        String nam = fromAuthtoName(auth);
        for (String key : accounts.keySet()) {
            Account value = accounts.get(key);
            if (key.equals(nam)) {
                return 1;
            }
        }
        return 0;


    }
}





