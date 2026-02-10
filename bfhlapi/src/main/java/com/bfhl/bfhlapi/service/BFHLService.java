package com.bfhl.bfhlapi.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BFHLService {

    public Map<String, Object> fibonacci(Map<String, Object> body, String email) {
        int n = (int) body.get("fibonacci");

        List<Integer> list = new ArrayList<>();
        int a = 0, b = 1;

        for (int i = 0; i < n; i++) {
            list.add(a);
            int c = a + b;
            a = b;
            b = c;
        }

        return success(email, list);
    }

    public Map<String, Object> prime(Map<String, Object> body, String email) {
        List<Integer> arr = (List<Integer>) body.get("prime");
        List<Integer> primes = new ArrayList<>();

        for (int num : arr)
            if (isPrime(num)) primes.add(num);

        return success(email, primes);
    }

    public Map<String, Object> lcm(Map<String, Object> body, String email) {
        List<Integer> arr = (List<Integer>) body.get("lcm");
        int result = arr.get(0);

        for (int i = 1; i < arr.size(); i++)
            result = lcm(result, arr.get(i));

        return success(email, result);
    }

    public Map<String, Object> hcf(Map<String, Object> body, String email) {
        List<Integer> arr = (List<Integer>) body.get("hcf");
        int result = arr.get(0);

        for (int i = 1; i < arr.size(); i++)
            result = gcd(result, arr.get(i));

        return success(email, result);
    }

    public Map<String, Object> ai(Map<String, Object> body, String email) {
        String question = body.get("AI").toString();
        String response = GeminiUtil.askGemini(question);
        return success(email, response);
    }

    private boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++)
            if (num % i == 0) return false;
        return true;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    private int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    private Map<String, Object> success(String email, Object data) {
        return Map.of(
                "is_success", true,
                "official_email", email,
                "data", data
        );
    }
}
