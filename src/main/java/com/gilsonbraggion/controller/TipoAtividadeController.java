package com.gilsonbraggion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gilsonbraggion.model.TipoAtividade;
import com.gilsonbraggion.repository.TipoAtividadeRepository;

@Controller
@RequestMapping(value = "/tipoAtividade")
public class TipoAtividadeController {

	@Autowired
	private TipoAtividadeRepository repo;

	@GetMapping
	public String getTipoAtividadesAtividades(Model model) {
		model.addAttribute("listagem", repo.findAll());
		return "logged/tipoAtividade/listagem";
	}

	@GetMapping(value = "/novo")
	public String novo(TipoAtividade tipoAtividade) {
		return "logged/tipoAtividade/cadastro";
	}
	
	@PostMapping(value = "/salvar")
	@CacheEvict("tipoAtividades")
	public String salvar(TipoAtividade tipoAtividade, Model model) {
		repo.save(tipoAtividade);
		return "redirect: /tipoAtividade";
	}
	
}