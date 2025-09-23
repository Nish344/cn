package computerNetworking;

import java.util.Scanner;

public class cyclicRC {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // Sender side
        System.out.print("Enter message bits: ");
        String message = sc.nextLine().replaceAll("\\s", "");

        System.out.print("Enter generator: ");
        String generator = sc.nextLine().replaceAll("\\s", "");

        int[] data = new int[message.length() + generator.length() - 1];
        int[] divisor = new int[generator.length()];

        // Copy message bits into data
        for (int i = 0; i < message.length(); i++)
            data[i] = message.charAt(i) - '0';

        for (int i = 0; i < generator.length(); i++)
            divisor[i] = generator.charAt(i) - '0';

        // Division process
        for (int i = 0; i < message.length(); i++) {
            if (data[i] == 1) {
                for (int j = 0; j < divisor.length; j++) {
                    data[i + j] ^= divisor[j];
                }
            }
        }

        System.out.print("The checksum code is: ");
        // Print original message + remainder
        System.out.print(message);
        for (int i = message.length(); i < data.length; i++) {
            System.out.print(data[i]);
        }
        System.out.println();

        // Receiver side
        System.out.print("Enter checksum code: ");
        String received = sc.nextLine().replaceAll("\\s", "");

        data = new int[received.length()];
        for (int i = 0; i < received.length(); i++)
            data[i] = received.charAt(i) - '0';

        // Reuse same divisor (generator is known to both sides)
        for (int i = 0; i < received.length() - divisor.length + 1; i++) {
            if (data[i] == 1) {
                for (int j = 0; j < divisor.length; j++) {
                    data[i + j] ^= divisor[j];
                }
            }
        }

        boolean valid = true;
        for (int bit : data) {
            if (bit == 1) {
                valid = false;
                break;
            }
        }

        if (valid)
            System.out.println("Data stream is valid ✅");
        else
            System.out.println("Data stream is invalid ❌ (CRC error).");

        sc.close();
    }
}
