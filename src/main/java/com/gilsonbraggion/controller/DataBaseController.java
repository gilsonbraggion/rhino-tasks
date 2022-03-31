package com.gilsonbraggion.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gilsonbraggion.model.Role;
import com.gilsonbraggion.model.User;
import com.gilsonbraggion.repository.UserRepository;

@RestController
@RequestMapping(value = "/carregarUsuarios")
public class DataBaseController {

	@Autowired
	private UserRepository userRepo;

	@GetMapping()
	public String chargeUsersDatabase() {
		
		userRepo.deleteAll();

		Role roleAdmin = new Role();
		roleAdmin.setName("ADMIN");
		
		Role roleUser = new Role();
		roleUser.setName("USER");
		
		Role roleGeneral = new Role();
		roleGeneral.setName("GENERAL");
		
		User userAdmin = new User();
		userAdmin.setEmail("admin@gmail.com");
		userAdmin.setUserName("Admin User");
		userAdmin.setPassword(new BCryptPasswordEncoder().encode("admin"));
		List<Role> rolesAdmin = new ArrayList<Role>();
		rolesAdmin.add(roleAdmin);
		userAdmin.setRoles(rolesAdmin);

		userRepo.save(userAdmin);
		
		User userUser = new User();
		userUser.setEmail("user@gmail.com");
		userUser.setUserName("Common User");
		userUser.setPassword(new BCryptPasswordEncoder().encode("user"));
		List<Role> rolesUser = new ArrayList<Role>();
		rolesUser.add(roleUser);
		userUser.setRoles(rolesUser);
		
		userRepo.save(userUser);
		
		User userGeneral = new User();
		userGeneral.setEmail("general@gmail.com");
		userGeneral.setUserName("General User");
		userGeneral.setPassword(new BCryptPasswordEncoder().encode("general"));
		List<Role> rolesGeneral = new ArrayList<Role>();
		rolesGeneral.add(roleGeneral);
		userGeneral.setRoles(rolesGeneral);
		
		userRepo.save(userGeneral);

		return "executou";
	}

}
