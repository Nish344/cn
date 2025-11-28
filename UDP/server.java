package UDP;
import java.net.*;
import java.io.*;

public class ServerSide {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Server ready. Type messages to send to client.");

            InetAddress ip = InetAddress.getByName("127.0.0.1");
            int port = 4000;

            while (true) {
                System.out.print("Server: ");
                String msg = br.readLine();

                if (msg.equalsIgnoreCase("exit")) {
                    System.out.println("Server closed.");
                    break;
                }

                byte[] b = msg.getBytes();

                DatagramPacket dp = new DatagramPacket(b, b.length, ip, port);
                ds.send(dp);
            }

            ds.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
