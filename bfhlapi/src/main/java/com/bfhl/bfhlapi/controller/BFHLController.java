package com.bfhl.bfhlapi.controller;

import com.bfhl.bfhlapi.service.BFHLService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class BFHLController {

    private final BFHLService service;
    private final String EMAIL = "anish0027.be23@chitkara.edu.in";

    public BFHLController(BFHLService service) {
        this.service = service;
    }

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok(Map.of(
                "is_success", true,
                "official_email", EMAIL
        ));
    }

    @PostMapping("/bfhl")
    public ResponseEntity<?> process(@RequestBody Map<String, Object> body) {
        try {

            if (body.containsKey("fibonacci"))
                return ResponseEntity.ok(service.fibonacci(body, EMAIL));

            if (body.containsKey("prime"))
                return ResponseEntity.ok(service.prime(body, EMAIL));

            if (body.containsKey("lcm"))
                return ResponseEntity.ok(service.lcm(body, EMAIL));

            if (body.containsKey("hcf"))
                return ResponseEntity.ok(service.hcf(body, EMAIL));

            if (body.containsKey("AI"))
                return ResponseEntity.ok(service.ai(body, EMAIL));

            return ResponseEntity.badRequest().body(Map.of(
                    "is_success", false,
                    "error", "Invalid request"
            ));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "is_success", false,
                    "error", "Server error"
            ));
        }
    }
}
