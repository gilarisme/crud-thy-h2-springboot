package net.crunchdroid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import net.crunchdroid.generatexml.TestGenerateInterface;

@RestController
public class Controller {
	
	@Autowired
	TestGenerateInterface testGenerate;
	
    @PostMapping("/generateXML")
    public String Pos() {
    	testGenerate.generateXML(true);
        return "succes";
    }
}
