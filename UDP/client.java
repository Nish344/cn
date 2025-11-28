package UDP;
import java.net.*;

public class ClientSide {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket(4000);
            byte[] buffer = new byte[1024];

            System.out.println("Client waiting for messages...\n");

            while (true) {
                DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
                ds.receive(dp);

                String msg = new String(dp.getData(), 0, dp.getLength());

                if (msg.equalsIgnoreCase("exit")) {
                    System.out.println("Server closed the chat.");
                    break;
                }

                System.out.println("Server: " + msg);
            }

            ds.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
