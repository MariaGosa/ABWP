package com.example.digital_signature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.security.*;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class Application {

    private static final String ALGORITHM = "RSA";
    private static final int RATE_LIMIT = 5; // Max 5 requests per minute
    private static final AtomicInteger requestCount = new AtomicInteger(0);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostMapping("/sign")
    public ResponseEntity<String> signMessage(@RequestBody String message) {
        try {
            if (requestCount.incrementAndGet() > RATE_LIMIT) {
                return ResponseEntity.status(429).body("Rate limit exceeded. Try again later.");
            }

            // Generowanie pary kluczy RSA
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            keyPairGenerator.initialize(2048); // 2048-bitowy klucz
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            PrivateKey privateKey = keyPair.getPrivate();

            // Podpisywanie wiadomości
            String signedMessage = signMessageWithRSA(message, privateKey);

            return ResponseEntity.ok("Signed Message: " + signedMessage);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    // Funkcja do podpisywania wiadomości
    private String signMessageWithRSA(String message, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());

        byte[] signedMessage = signature.sign();
        return Base64.getEncoder().encodeToString(signedMessage); // Zwracamy jako Base64
    }

    // Resetowanie liczby żądań co minutę
    @Scheduled(fixedRate = 60000)
    public void resetRequestCount() {
        requestCount.set(0);
    }
}
