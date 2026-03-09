import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class MainServer {

    public static void main(String[] args) {
        System.out.println("Inizio SERVER!!!");

        try {
            //scelta primitiva socket e operazione di 'bind' esplicita perché ho scelto la porta
            DatagramSocket dSocket = new DatagramSocket(3000);

            byte[] bufferIn = new byte[256];
            DatagramPacket inPacket = new DatagramPacket(bufferIn, bufferIn.length);

            dSocket.receive(inPacket); //METODO BLOCCANTE
            System.out.println("Ricezione messaggio effettuata");

            //ECHO
            //Deserializzazione (trasformo i byte in una stringa)
            String message = new String(inPacket.getData(), 0, inPacket.getLength());

            InetAddress clientAddress = inPacket.getAddress();
            int port = inPacket.getPort();

            DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(), clientAddress, port);
            dSocket.send(outPacket);

            //NO chiusura SERVER perché deve poter sempre ricevere 24/24

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}