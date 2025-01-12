package com.example.digital_signature;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class DigitalSignatureExample {

    /**
     * Generuje parę kluczy RSA (publiczny i prywatny).
     *
     * @return KeyPair - para kluczy RSA
     * @throws NoSuchAlgorithmException jeśli algorytm RSA nie jest obsługiwany
     */
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); // Długość klucza (w bitach)
        return keyGen.generateKeyPair();
    }

    /**
     * Podpisuje wiadomość za pomocą klucza prywatnego.
     *
     * @param message   wiadomość do podpisania
     * @param privateKey klucz prywatny do podpisania wiadomości
     * @return podpis cyfrowy w postaci tablicy bajtów
     * @throws Exception w przypadku błędów podpisywania
     */
    public static byte[] signMessage(String message, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes(StandardCharsets.UTF_8));
        return signature.sign();
    }

    /**
     * Weryfikuje podpis cyfrowy dla wiadomości za pomocą klucza publicznego.
     *
     * @param message       wiadomość, której podpis ma być zweryfikowany
     * @param signatureBytes podpis cyfrowy do weryfikacji
     * @param publicKey     klucz publiczny do weryfikacji
     * @return true, jeśli podpis jest poprawny; false w przeciwnym wypadku
     * @throws Exception w przypadku błędów weryfikacji
     */
    public static boolean verifySignature(String message, byte[] signatureBytes, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(message.getBytes(StandardCharsets.UTF_8));
        return signature.verify(signatureBytes);
    }

    /**
     * Przykładowe użycie klasy DigitalSignatureExample.
     */
    public static void main(String[] args) {
        try {
            // 1. Generowanie pary kluczy
            KeyPair keyPair = generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // 2. Wiadomość do podpisania
            String message = "To jest przykładowa wiadomość do podpisania.";

            // 3. Podpisywanie wiadomości
            byte[] digitalSignature = signMessage(message, privateKey);
            String encodedSignature = Base64.getEncoder().encodeToString(digitalSignature);
            System.out.println("Podpis cyfrowy (Base64): " + encodedSignature);

            // 4. Weryfikacja podpisu
            boolean isSignatureValid = verifySignature(message, digitalSignature, publicKey);
            System.out.println("Czy podpis jest poprawny? " + isSignatureValid);

        } catch (Exception e) {
            System.err.println("Wystąpił błąd: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
