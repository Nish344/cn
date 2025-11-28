package TCPIP;
import java.net.*;
import java.io.*;

public class serverSide {
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(4000)) {
            System.out.println("Server ready for connection...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            BufferedReader fileRead = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            String fname = fileRead.readLine();
            PrintWriter pwrite = new PrintWriter(socket.getOutputStream(), true);

            String filePath = "C:\\Users\\Nishanth Antony\\Downloads\\computerNetworks\\src\\TCPIP\\";
            File file = new File(filePath+fname);

            // Check file exists
            if (!file.exists()) {
                pwrite.println("ERROR: File Not Found");
            } else {
                try (BufferedReader contentRead = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = contentRead.readLine()) != null) {
                        pwrite.println(line);
                    }
                }
            }

            // Close everything
            fileRead.close();
            pwrite.close();
            socket.close();
            System.out.println("Connection closed.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
