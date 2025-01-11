package com.example.digital_signature;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.client.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testSignMessage() {
        String message = "Test message";
        ResponseEntity<String> response = restTemplate.postForEntity("/api/sign", message, String.class);

        assertTrue(response.getBody().contains("Signed Message"));
    }
}
