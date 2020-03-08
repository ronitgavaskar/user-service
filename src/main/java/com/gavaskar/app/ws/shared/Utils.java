package com.gavaskar.app.ws.shared;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class Utils {

    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = createPossibleCombinations();

    public String generateUserId(int length) {
        return generateRandomString(length);
    }

    public String generateRandomString(int length) {
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            randomString.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return randomString.toString();
    }

    public String createPossibleCombinations() {
        StringBuilder combo = new StringBuilder();
        for (char i = '0'; i <= 'z'; i++) {
            if ((i >= '0' && i <= '9') || (i >= 'a' && i <= 'z') ||
                    (i >= 'A' && i <= 'Z')) {
                combo.append(i);
            }
        }
        return combo.toString();
    }
}
