package com.hub.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthCheckController {

    @GetMapping("/check")
    public String check() {
        return "Hi, I am Alive 😝 Welcome to Hub 👉";
    }

    @GetMapping("/developer")
    public String developer() {
        return "Hi Welcome to Hub, Ajay and Nishu Here!👫";
    }
}
