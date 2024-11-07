import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;

public class client {
    public static void main(String[] args) throws NotBoundException, RemoteException {

        Registry rmiRegistry = LocateRegistry.getRegistry(args[0],Integer.parseInt(args[1]));
        MessagingServerInterface messagingServer = (MessagingServerInterface) rmiRegistry.lookup("MessagingServer");
        if (Integer.parseInt(args[2]) == 1) {
            int flag = messagingServer.createAccount(args[3]);
            if (flag == -1) {
                System.out.println("Sorry, the user already exists");
            } else {
                if(flag==1)
                {
                    System.out.println("Invalid Username");

                }else System.out.println(flag);
            }
        }
        if (Integer.parseInt(args[2]) == 2) {
            if (messagingServer.accountExists(Integer.parseInt(args[3])) == 1) {
                Map<String, Account> acc = messagingServer.showAccounts();
                int count = 1;
                for (String key : acc.keySet()) {
                    System.out.println(count + ". " + key);
                    count++;
                }
            } else {
                System.out.println("Invalid Auth Token");
            }
        }
             if (Integer.parseInt(args[2]) == 3)
             {
                int result = messagingServer.sendMessage(Integer.parseInt(args[3]), args[4], args[5]);
                if (result == 1)
                {
                    System.out.println("OK");
                }
                else
                {
                    if(result!=0) {
                        System.out.println("User does not exist");
                    }
                    else
                    {
                    System.out.println("Invalid Auth Token");
                    }
                }
            }
            if (Integer.parseInt(args[2]) == 4)
            {

                if(messagingServer.isValid(Integer.parseInt(args[3]))==1) {
                    String value = messagingServer.showInbox(Integer.parseInt(args[3]));
                    System.out.println(value);
                }
                else  {
                    System.out.println("Invalid Auth Token");
                }

            }

            if (Integer.parseInt(args[2]) == 5)
            {
                if(messagingServer.isValid(Integer.parseInt(args[3]))==1) {
                    String re=messagingServer.ReadMessage(Integer.parseInt(args[3]),Integer.parseInt(args[4]));
                    System.out.println(re);
                }
                else  {
                    System.out.println("Invalid Auth Token");
                }
            }
            if (Integer.parseInt(args[2]) == 6)
            {
                if(messagingServer.isValid(Integer.parseInt(args[3]))==1) {
                    String re = messagingServer.DeleteMessage(Integer.parseInt(args[3]), Integer.parseInt(args[4]));
                    System.out.println(re);
                }
                else  {
                    System.out.println("Invalid Auth Token");
                }

            }
    }
}
