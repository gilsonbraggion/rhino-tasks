package com.gilsonbraggion.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gilsonbraggion.model.Role;
import com.gilsonbraggion.model.Usuario;
import com.gilsonbraggion.repository.RoleRepository;
import com.gilsonbraggion.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/carregarUsuarios")
public class DataBaseController {

	@Autowired
	private UsuarioRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@GetMapping()
	public String chargeUsersDatabase() {

		Usuario userAdmin = new Usuario();
		userAdmin.setEmail("rs.senne@gmail.com");
		userAdmin.setUserName("Rafael Senne");
		userAdmin.setPassword(new BCryptPasswordEncoder().encode("1"));

		List<Role> rolesAdmin = new ArrayList<Role>();
		rolesAdmin.add(roleRepo.findAll().get(0));
		userAdmin.setRoles(rolesAdmin);

		userRepo.save(userAdmin);

		return "executou";
	}

}
