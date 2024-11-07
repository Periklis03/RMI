import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface MessagingServerInterface extends Remote {
    int createAccount(String username) throws RemoteException;
     Map<String,Account> showAccounts() throws RemoteException;
    int accountExists(int auth) throws RemoteException;
    int sendMessage(int auth,String recipient,String sentmesssage)  throws RemoteException;
    int accountExists2(String name)  throws RemoteException;
    String fromAuthtoName(int auth)  throws RemoteException;
     String showInbox(int auth) throws  RemoteException;
     String ReadMessage(int auth,int id) throws RemoteException;
    String DeleteMessage(int auth, int id) throws RemoteException;
    int isValid(int auth) throws  RemoteException;
}