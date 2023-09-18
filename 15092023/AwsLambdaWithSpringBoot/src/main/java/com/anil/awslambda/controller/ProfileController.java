package com.anil.awslambda.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anil.awslambda.model.User;

@RestController
@RequestMapping("/api/v1/")
public class ProfileController {

    @GetMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUser() {
//        return List.of(new User("Anil Pal", "9870012345", "anil.pal@cloverinfotech.com"), 
//                       new User("Bhawana Wadekar", "6387212345", "bhawana.wadekar@cloverinfotech.com"));
    	System.out.println("Inside get users api");
    	List<User> list=new ArrayList<>();
    	list.add(new User("Anil Pal", "9870012345", "anil.pal@cloverinfotech.com"));
    	list.add(new User("Bhawana Wadekar", "6387212345", "bhawana.wadekar@cloverinfotech.com"));
    	
    	
    	System.out.println("Users : {} "+list);
    	
    	return list;
    
    }
}
