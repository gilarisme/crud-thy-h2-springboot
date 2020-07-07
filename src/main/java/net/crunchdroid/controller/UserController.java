package net.crunchdroid.controller;

import javax.validation.Valid;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.crunchdroid.entity.Role;
import net.crunchdroid.entity.User;
import net.crunchdroid.entity.UserDTO;
@Controller
public class UserController {

	@Autowired
	net.crunchdroid.repo.userRepo userRepo;
	
	@Autowired
	net.crunchdroid.repo.roleRepo roleRepo;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	private User user;
	
	
    @GetMapping("/register")
    public String register(User user) {
        return "register";
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(Model model) {   
    	
    	List<User> listUsers = (List<User>) userRepo.findAll();
    	model.addAttribute("users", listUsers);
    	
        return "user";
    }
     
    @PostMapping("/adduser")
    public String addUser(@Valid UserDTO user, BindingResult result, Model model) {
    	ModelMapper modelMapper = new ModelMapper(); 	
        if (result.hasErrors()) {
        	return "register";
            
        }
        
        User users = new User();
        users = modelMapper.map(user, User.class);
        users.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(users);
        model.addAttribute("users", userRepo.findAll());
        
        return "redirect:/user";
    }
    
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = (userRepo.findById(id));
         
        model.addAttribute("user", user);
        return "updateUser";
    }
    
    @PostMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "user";
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));     
        userRepo.save(user);
        model.addAttribute("users", userRepo.findAll());
        return "redirect:/user";
    }
    
    
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userRepo.findById(id);
        userRepo.delete(user);
        model.addAttribute("users", userRepo.findAll());
        return "user";
    }
}
