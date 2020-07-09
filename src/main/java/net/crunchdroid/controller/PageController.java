package net.crunchdroid.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.crunchdroid.entity.User;


/**
 * @author CrunchDroid
 */
@Controller
public class PageController {
	
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String index(ModelMap modelMap, @AuthenticationPrincipal User principal) {
        return "redirect:/plain-page";
    }
	
    @RequestMapping(value = {"plain-page"}, method = RequestMethod.GET)
    public String plainPage(ModelMap modelMap, @AuthenticationPrincipal User principal) {
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
