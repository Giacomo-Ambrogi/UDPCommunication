import java.io.IOException;
import java.net.*;
import java.util.function.DoubleToIntFunction;

public class MainClient {

    public static void main(String[] args) {
        System.out.println("Inizio CLIENT!!!");

        try {
            InetAddress serverAddress = InetAddress.getLocalHost();
            String message = "Ciao";
            int port = 3000;

            //scelta primitiva socket e operazione di 'bind' implicita
            DatagramSocket dSocket = new DatagramSocket();
            System.out.println("Primitiva socket lato CLIENT realizzata!!!");

            //costruzione del pacchetto dati
            DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, port);

            //invio dei dati
            dSocket.send(outPacket);
            System.out.println("Pacchetto dati inviato al SERVER!!!");

            byte[] bufferIn = new byte[256];
            DatagramPacket inPacket = new DatagramPacket(bufferIn, bufferIn.length);

            dSocket.receive(inPacket); //METODO BLOCCANTE
            System.out.println("Ricezione messaggio effettuata" + inPacket);

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}