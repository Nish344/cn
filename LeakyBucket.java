package cnlab;
import java.util.*;

public class Leakybucket {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int bucketCap = 4;   // bucket capacity
        int outputRate = 3;  // transmission rate
        int bucketContent = 0;

        int[] packets = new int[20];

        System.out.println("Enter number of packets: ");
        int n = in.nextInt();

        System.out.println("Enter packet sizes: ");
        for (int i = 0; i < n; i++) {
            packets[i] = in.nextInt();
        }

        System.out.println("Clock\tPacket\tAccept\tSent\tRemaining");

        for (int i = 0; i < n; i++) {

            int packet = packets[i];
            int accept, sent;

            // Check if packet fits
            if (packet + bucketContent > bucketCap) {
                accept = -1; // dropped
            } else {
                accept = packet;
                bucketContent += packet;
            }

            // Transmission
            if (bucketContent == 0) {
                sent = 0;
            } else if (bucketContent <= outputRate) {
                sent = bucketContent;
                bucketContent = 0;
            } else {
                sent = outputRate;
                bucketContent -= outputRate;
            }

            // Printing
            if (accept == -1)
                System.out.println((i + 1) + "\t" + packet + "\tdropped\t" + sent + "\t" + bucketContent);
            else
                System.out.println((i + 1) + "\t" + packet + "\t" + accept + "\t" + sent + "\t" + bucketContent);
        }

        in.close();
    }
}
