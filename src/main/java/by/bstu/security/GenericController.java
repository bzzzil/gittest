package by.bstu.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenericController {

    @GetMapping
    String getDefault() {
        return "Main page";
    }

    @GetMapping("/members")
    String getMembers() {
        return "Members area";
    }


    @GetMapping("/admin")
    String getAdminArea() {
        return "Admin area!";
    }
}
