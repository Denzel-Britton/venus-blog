package com.example.venusblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HellController {

    @Controller
    public class HelloController {

        @GetMapping("/hello") //endpoints
        @ResponseBody // a response to be sent to the server
        public String hello() {
            return "Hello from Spring!";
        }
    }

        @GetMapping("/hello/{name}") //endpoints
        @ResponseBody // a response to be sent to the server
        public String hello(@PathVariable String name) {
            return "Hello, " + name + "!";
        }
    }

