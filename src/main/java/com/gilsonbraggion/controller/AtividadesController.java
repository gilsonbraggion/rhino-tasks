package com.gilsonbraggion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gilsonbraggion.repository.AtividadeRepository;

@Controller
@RequestMapping(value = "atividades")
public class AtividadesController {

	@Autowired
	private AtividadeRepository repo;

	@GetMapping
	public String getAtividades(Model model) {
		model.addAttribute("listagem", repo.findAll());

		return "/logged/atividades/listagem";
	}

}
