import java.io.IOException;
import java.net.*;
import java.util.function.DoubleToIntFunction;

public class MainClient {

    //colore messaggi SERVER
    public static final String BLU = "\u001B[34m";
    //colore messaggi CLIENT
    public static final String ROSSO = "\033[1;31m";
    //colore del prompt del gruppo
    public static final String VERDE_SOTTOLINEATO = "\033[4;32m";
    //colore RESET
    public static final String RESET = "\033[0m";

    public static void main(String[] args) {
        System.out.println("Inizio CLIENT!!!");

        try {
            InetAddress serverAddress = InetAddress.getLocalHost();
            String message = "Ciao, ti mando questo messaggio!!!";
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
            System.out.println("Ricezione messaggio effettuata: " + inPacket);

        } catch (UnknownHostException e) {
            System.err.println("SERVER non trovato!!!");
        } catch (SocketException e) {
            System.err.println("Errore Socket!!!");
        } catch (IOException e) {
            System.err.println("Errore I/O!!!");
        }

    }
}