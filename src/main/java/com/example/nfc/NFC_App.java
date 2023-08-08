package com.example.nfc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = ("com.example.nfc"))
public class NFC_App {
    public static void main(String[] args) {
        SpringApplication.run(NFC_App.class, args);
    }
}
