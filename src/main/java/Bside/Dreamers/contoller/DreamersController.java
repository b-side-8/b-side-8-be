package Bside.Dreamers.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DreamersController {
    @GetMapping("hello")
    public String hello(Model model) {
        //jenkins push test3

        return "/hello";
    }
}
