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
import com.gilsonbraggion.repository.RoleRepository;
import com.gilsonbraggion.repository.UserRepository;

@RestController
@RequestMapping(value = "/carregarUsuarios")
public class DataBaseController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@GetMapping()
	public String chargeUsersDatabase() {

		userRepo.deleteAll();

		Role roleAdmin = new Role();
		roleAdmin.setName("ADMIN");

		Role roleSaved = roleRepo.save(roleAdmin);

		User userAdmin = new User();
		userAdmin.setEmail("gilson.braggion@gmail.com");
		userAdmin.setUserName("Gilson Braggion");
		userAdmin.setPassword(new BCryptPasswordEncoder().encode("1"));

		List<Role> rolesAdmin = new ArrayList<Role>();
		rolesAdmin.add(roleSaved);
		userAdmin.setRoles(rolesAdmin);

		userRepo.save(userAdmin);

		return "executou";
	}

}
