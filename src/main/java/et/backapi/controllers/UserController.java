package et.backapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping({"getStatus"})
    public String getStatus(){
        return "Aplicacao on";
    }
}
