package com.bfhl.bfhlapi.service;

import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

public class GeminiUtil {

    static String API_KEY = "AIzaSyAYAxaA9k16KHK_sryIv41VL48Hk1IPf5c";

    public static String askGemini(String question) {

        try {

            RestTemplate restTemplate = new RestTemplate();

            String url = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-flash:generateContent?key=" + API_KEY;

            Map<String, Object> textMap = new HashMap<>();
            textMap.put("text", question);

            Map<String, Object> partsMap = new HashMap<>();
            partsMap.put("parts", new Object[]{textMap});

            Map<String, Object> body = new HashMap<>();
            body.put("contents", new Object[]{partsMap});

            Object response = restTemplate.postForObject(url, body, Object.class);

            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "AI_failed";
        }
    }
}
