package pgm7;
import java.net.*;
import java.io.*;

public class TCPC {
    public static void main(String[] args) throws Exception {
        
        Socket sock = new Socket("127.0.0.1", 4000);

        System.out.println("Enter the filename:");
        BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
        String fname = keyRead.readLine();

        PrintWriter pwrite = new PrintWriter(sock.getOutputStream(), true);
        pwrite.println(fname);

        BufferedReader socketRead = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        String str;

        System.out.println("\n--- File Content ---\n");
        while ((str = socketRead.readLine()) != null) {
            System.out.println(str);
        }

        pwrite.close();
        socketRead.close();
        keyRead.close();
        sock.close();
    }
}
