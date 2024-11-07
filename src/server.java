import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class server{
    public static void main(String args[]){
        try{
            Registry rmiRegistry = LocateRegistry.createRegistry(Integer.parseInt(args[0]));
            MessagingServer messagingServer = new MessagingServer();
            rmiRegistry.rebind("MessagingServer",messagingServer);
            System.out.println("ServerReady with port "+args[0]);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
