package org.example.springdemoapi.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ModuleController {

    @GetMapping("/")
    public String sayHello(){
        return "Hello World";
    }
    
    @GetMapping("/get-home")
    public String getHome(){
        return "i want to go home";
    }

    @GetMapping("/get-calc")
    public int getCalc(){
        return 1 + 1;
    }

}
