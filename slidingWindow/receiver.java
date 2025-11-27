package pgm5;
import java.net.*;
import java.io.*;

public class slidreceiver {
    public static void main(String[] args) throws Exception {

        Socket s = new Socket(InetAddress.getLocalHost(), 4000);

        DataInputStream in = new DataInputStream(s.getInputStream());
        PrintStream p = new PrintStream(s.getOutputStream());

        int i, rptr = -1, nf, rws = 8;
        String rbuf[] = new String[8];
        String ch;

        do {
            nf = Integer.parseInt(in.readLine());

            if (nf <= rws - 1) {
                for (i = 1; i <= nf; i++) {
                    rptr = (rptr + 1) % 8;
                    rbuf[rptr] = in.readLine();
                    System.out.println("Received Frame " + rptr + ": " + rbuf[rptr]);
                }

                rws -= nf;
                System.out.println("\nAcknowledgment sent.\n");
                p.println(rptr + 1);
                rws += nf;

            } else {
                break;
            }

            ch = in.readLine();
        } while (ch.equals("yes"));
        
        s.close();
    }
}
