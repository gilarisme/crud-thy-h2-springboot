package net.crunchdroid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author CrunchDroid
 */
@Controller
public class PageController {
	
	@GetMapping("/login")
    public String login() {
        return "login";
    }
	
    @GetMapping("/plain-page")
    public String plainPage() {
        return "plain-page";
    }

    @GetMapping("/pricing-tables")
    public String pricingTables() {
        return "pricing-tables";
    }
    
    @PostMapping("/generate")
    public String Pos() {
        return "succes";
    }

}
