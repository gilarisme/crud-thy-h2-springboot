package net.crunchdroid.controller;


import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.crunchdroid.entity.User;
import net.crunchdroid.repo.RoleRepo;
@Controller
public class UserController {

	@Autowired
	net.crunchdroid.repo.UserRepo userRepo;
	
	@Autowired
    RoleRepo roleService;

	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
    @GetMapping("/register")
    public String register(ModelMap model, @AuthenticationPrincipal User principal) {
    	model.addAttribute("principal", principal);
    	model.addAttribute("roles", roleService.findAll());
    	
        return "register";
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(Model model) {   
    	
    	List<User> listUsers = (List<User>) userRepo.findAll();
    	model.addAttribute("users", listUsers);
    	
        return "user";
    }
     
    @PostMapping("/adduser")
    public String addUser(@RequestParam HashMap<String, String> body, Model model) {


    	User user = new User();
        user.setUserName(body.get("userName"));
        user.setUserPassword(passwordEncoder.encode(body.get("userPassword")));
        user.setRole((roleService.findRoleByidRole(UUID.fromString(body.get("role")))));
        user.setEmail(body.get("email"));
        user.setNamaLengkap(body.get("namaLengkap"));
        userRepo.save(user);
        model.addAttribute("users", userRepo.findAll());
        
        return "redirect:/user";
    }
    
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = (userRepo.findById(id));
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAll());
        return "updateUser";
    }
    
    @PostMapping("/updateUser/{id}")
    public String updateUser(@RequestParam HashMap<String, String> body, @PathVariable("id") long id, Model model) {
    	User user = (userRepo.findById(id));
        user.setUserName(body.get("userName"));
        user.setUserPassword(passwordEncoder.encode(body.get("userPassword")));
        user.setRole((roleService.findRoleByidRole(UUID.fromString(body.get("role")))));
        user.setEmail(body.get("email"));
        user.setNamaLengkap(body.get("namaLengkap"));
        userRepo.save(user);
        return "redirect:/user";
    }
    
    
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userRepo.findById(id);
        userRepo.delete(user);
        model.addAttribute("users", userRepo.findAll());
        return "redirect:/user";
    }
}
