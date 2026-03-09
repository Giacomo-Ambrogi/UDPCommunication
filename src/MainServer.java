import java.net.DatagramSocket;
import java.net.SocketException;

public class MainServer {

    public static void main(String[] args) {
        System.out.println("Inizio SERVER!!!");

        try {
            //scelta primitiva socket e operazione di 'bind' esplicita perché ho scelto la porta
            DatagramSocket dSocket = new DatagramSocket(3000);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

    }
}