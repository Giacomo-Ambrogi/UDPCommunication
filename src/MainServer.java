import java.io.IOException;
import java.net.*;

public class MainServer {

    //colore messaggi SERVER
    public static final String BLU = "\u001B[34m";
    //colore messaggi CLIENT
    public static final String ROSSO = "\033[1;31m";
    //colore RESET
    public static final String RESET = "\033[0m";

    public static void main(String[] args) {
        System.out.println(BLU + "Inizio SERVER!!!" + RESET);

        try {
            //scelta primitiva socket e operazione di 'bind' esplicita perché ho scelto la porta
            DatagramSocket dSocket = new DatagramSocket(3000);
            System.out.println(BLU + "Apertura della porta per ricevere!!!" + RESET);

            while (true) {
                //decido in quale parte della RAM mettere le mie istruzioni
                byte[] bufferIn = new byte[256];
                //leggo tutto il bufferIn con il 'length'
                DatagramPacket inPacket = new DatagramPacket(bufferIn, bufferIn.length);

                dSocket.receive(inPacket); //METODO BLOCCANTE
                System.out.println("Ricezione messaggio effettuata: " + inPacket);

                InetAddress clientAddress = inPacket.getAddress();
                int port = inPacket.getPort();

                //ECHO
                //Deserializzazione (trasformo i byte in una stringa)
                String messageIn = new String(inPacket.getData(), 0, inPacket.getLength());
                System.out.println(ROSSO + "Messaggio ricevuto dal CLIENT" + clientAddress + ": " + port + "\n\t" + messageIn + RESET);

                String messageOut = "Richiesta ricevuta!!!";
                DatagramPacket outPacket = new DatagramPacket(messageOut.getBytes(), messageOut.length(), clientAddress, port);

                dSocket.send(outPacket);
                System.out.println(BLU + "Messaggio spedito al CLIENT: " + messageOut + RESET);

                //recuperiamo l'indirizzo del gruppo
                InetAddress groupAddress = InetAddress.getByName("239.255.255.250");
                int groupPort = 2500;

                messageOut= "Benvenuti a tutti!";

                outPacket = new DatagramPacket(messageOut.getBytes(), messageOut.length(), groupAddress, groupPort);

                dSocket.send(outPacket);
                System.out.println(BLU + "Messaggio spedito al gruppo: " + messageOut + RESET);
                //NO chiusura SERVER perché deve poter sempre ricevere 24/24
            }

        } catch (BindException e) {
            System.err.println("Porta già in uso!!!");
        } catch (SocketException e) {
            System.err.println("Errore Socket!!!");
        } catch (IOException e) {
            System.err.println("Errore I/O!!!");
        }

    }
}