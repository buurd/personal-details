package se.attafemton.personal.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpaRedirectController {
    @RequestMapping({"/personList", "/personForm/**", "/personView/**", "/registration", "/login", "/" })
    public String index() {
        return "forward:/index.html";
    }
}