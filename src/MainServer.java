import java.io.IOException;
import java.net.*;

public class MainServer {

    public static void main(String[] args) {
        System.out.println("Inizio SERVER!!!");

        try {
            //scelta primitiva socket e operazione di 'bind' esplicita perché ho scelto la porta
            DatagramSocket dSocket = new DatagramSocket(3000);

            //decido in quale parte della RAM mettere le mie istruzioni
            byte[] bufferIn = new byte[256];
            //leggo tutto il bufferIn con il 'length'
            DatagramPacket inPacket = new DatagramPacket(bufferIn, bufferIn.length);

            dSocket.receive(inPacket); //METODO BLOCCANTE
            System.out.println("Ricezione messaggio effettuata: " + inPacket);

            //ECHO
            //Deserializzazione (trasformo i byte in una stringa)
            String message = new String(inPacket.getData(), 0, inPacket.getLength());

            InetAddress clientAddress = inPacket.getAddress();
            int port = inPacket.getPort();

            DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(), clientAddress, port);
            dSocket.send(outPacket);

            //NO chiusura SERVER perché deve poter sempre ricevere 24/24

        } catch (BindException e) {
            System.err.println("Porta già in uso!!!");
        } catch (SocketException e) {
            System.err.println("Errore Socket!!!");
        } catch (IOException e) {
            System.err.println("Errore I/O!!!");
        }

    }
}