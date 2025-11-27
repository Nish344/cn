package pgm5;
import java.net.*;
import java.io.*;

public class slidsender {
    public static void main(String[] args) throws Exception {

        ServerSocket ser = new ServerSocket(4000);
        Socket s = ser.accept();

        DataInputStream in = new DataInputStream(System.in);
        DataInputStream in1 = new DataInputStream(s.getInputStream());
        PrintStream p = new PrintStream(s.getOutputStream());

        String sbuff[] = new String[8];
        int sptr = 0, sws = 8, nf, ano, i;
        String ch;

        do {
            System.out.print("Enter the number of frames: ");
            nf = Integer.parseInt(in.readLine());
            p.println(nf);

            if (nf <= sws - 1) {
                System.out.println("Enter " + nf + " messages to be sent:\n");
                for (i = 1; i <= nf; i++) {
                    sbuff[sptr] = in.readLine();
                    p.println(sbuff[sptr]);
                    sptr = (sptr + 1) % 8;
                }

                sws -= nf;
                System.out.print("Acknowledgment received: ");
                ano = Integer.parseInt(in1.readLine());
                System.out.println("for " + ano + " frames");
                sws += nf;
            } else {
                System.out.println("Number of frames exceeds window size!");
                break;
            }

            System.out.print("\nDo you want to send more frames? (yes/no): ");
            ch = in.readLine();
            p.println(ch);

        } while (ch.equals("yes"));

        s.close();
        ser.close();
    }
}
