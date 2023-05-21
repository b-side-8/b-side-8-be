package Bside.Dreamers.contoller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class DreamersController {
    @GetMapping("hello")
    public String hello(Model model) {

        return "/hello";
    }
}
