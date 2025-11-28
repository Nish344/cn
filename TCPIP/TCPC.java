package TCPIP;
import java.net.*;
import java.io.*;

public class clientSide {
    public static void main(String[] args) {

        try (Socket socket = new Socket("127.0.0.1", 4000)) {

            System.out.println("Enter the filename:");
            BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
            String fname = keyRead.readLine();

            PrintWriter pwrite = new PrintWriter(socket.getOutputStream(), true);
            pwrite.println(fname);

            BufferedReader socketRead = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            System.out.println("\n--- File Content ---\n");
            String str;

            while ((str = socketRead.readLine()) != null) {
                System.out.println(str);
            }

            keyRead.close();
            socketRead.close();
            pwrite.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
