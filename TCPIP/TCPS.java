package pgm7;
import java.net.*;
import java.io.*;

public class TCPS {
    public static void main(String[] args) throws Exception {

        ServerSocket sersock = new ServerSocket(4000);
        System.out.println("Server ready for connection...");

        Socket sock = sersock.accept();
        System.out.println("Client connected!");

        BufferedReader fileRead = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        String fname = fileRead.readLine();

        BufferedReader contentRead = new BufferedReader(new FileReader(fname));
        PrintWriter pwrite = new PrintWriter(sock.getOutputStream(), true);

        String str;
        while ((str = contentRead.readLine()) != null) {
            pwrite.println(str);
        }

        contentRead.close();
        fileRead.close();
        pwrite.close();
        sock.close();
        sersock.close();
    }
}
