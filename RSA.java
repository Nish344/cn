package computerNetworking;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RSA {
    private BigInteger p, q, N, phi, e, d;
    private int bitlength = 32; // smaller for demo
    private Random r = new Random();

    public RSA() {
        // Generate two random primes
        p = BigInteger.probablePrime(bitlength, r);
        q = BigInteger.probablePrime(bitlength, r);

        N = p.multiply(q);
        phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // Choose e (public key exponent)
        e = BigInteger.probablePrime(bitlength / 2, r);
        while (phi.gcd(e).intValue() > 1) {
            e = e.add(BigInteger.ONE);
        }

        // Compute d (private key exponent)
        d = e.modInverse(phi);

        System.out.println("Public key: (" + e + ", " + N + ")");
        System.out.println("Private key: (" + d + ", " + N + ")");
    }

    // Encrypt with public key
    public byte[] encrypt(byte[] message) {
        return new BigInteger(message).modPow(e, N).toByteArray();
    }

    // Decrypt with private key
    public byte[] decrypt(byte[] encrypted) {
        return new BigInteger(encrypted).modPow(d, N).toByteArray();
    }

    public static void main(String[] args) {
        RSA rsa = new RSA();
        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter a message: ");
        String msg = sc.nextLine();

        // Encrypt
        byte[] encrypted = rsa.encrypt(msg.getBytes());
        System.out.println("Encrypted: " + new BigInteger(encrypted));

        // Decrypt
        byte[] decrypted = rsa.decrypt(encrypted);
        System.out.println("Decrypted: " + new String(decrypted));
    }
}
