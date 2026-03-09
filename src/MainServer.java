import java.net.DatagramSocket;

public class MainServer {
    public static void main(String[] args) {
        //scelta primitiva socket e operazione di 'bind' esplicita perché ho scelto la porta
        dSocket = new DatagramSocket(3000);
    }
}