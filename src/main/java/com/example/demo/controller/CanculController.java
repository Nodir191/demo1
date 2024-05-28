package com.example.demo.controller;

import com.example.demo.service.CanculService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // CORS konfiguratsiyasi
public class CanculController {

    private final CanculService canculService;

    @PostMapping("/calculate")
    public HttpEntity<?> add(@RequestBody String numbers) {
        System.out.println("-"+numbers+"-");
        try {
            double result = canculService.calculate(numbers);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Calculation error: " + e.getMessage());
        }
    }
}
