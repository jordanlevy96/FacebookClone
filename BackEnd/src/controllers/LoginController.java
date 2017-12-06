package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.User;
import model.UserManager;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class LoginController {
    
    @RequestMapping(value = "/authenticate")
	public String authenticate() {
    		System.out.println("Authenticating...");
    		UserManager um = UserManager.getInstance();
    		if (um.validateUser()) {
    			return "valid";
    		}
    		else {
    			return "invalid";
    		}
    		
	}
    
    @RequestMapping(method=RequestMethod.POST, value="/login")
    public void login(@RequestBody User user) { 
        System.out.println(user.getUsername() + " attempting login");
        UserManager um = UserManager.getInstance();
        um.startValidation(user);
    }
}