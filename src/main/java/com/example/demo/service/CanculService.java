package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CanculService implements CanculServiceImp {

    @Override
    public double calculate(String numbers) {
        if (numbers.startsWith("\"")) {
            numbers = numbers.substring(1);
        }
        if (numbers.endsWith("\"")) {
            numbers = numbers.substring(0, numbers.length() - 1);
        }
        StringBuilder num = new StringBuilder();
        List<Double> calc = new ArrayList<>();
        char prevSign = '+';
        for (int i = 0; i < numbers.length(); i++) {
            char ch = numbers.charAt(i);
            boolean isDigitOrDot = (ch >= '0' && ch <= '9') || ch == '.';
            if (isDigitOrDot) {
                num.append(ch);
            }
            if (!isDigitOrDot || i == numbers.length() - 1) {
                try {
                    double number = Double.parseDouble(num.toString());
                    if (!Double.isFinite(number)) {
                        throw new IllegalArgumentException("Invalid number format");
                    }
                    if (prevSign == '+') {
                        calc.add(number);
                    } else if (prevSign == '-') {
                        calc.add(-number);
                    } else if (prevSign == '*') {
                        calc.set(calc.size() - 1, calc.get(calc.size() - 1) * number);
                    } else if (prevSign == '/') {
                        calc.set(calc.size() - 1, calc.get(calc.size() - 1) / number);
                    }
                    prevSign = ch;
                    num = new StringBuilder();
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid number format");
                }
            }
        }
        if (!num.isEmpty()) {
            calc.add(Double.parseDouble(num.toString()));
        }
        double result = 0;
        for (double value : calc) {
            result += value;
        }
        System.out.println(result);
        return result;
    }
}
