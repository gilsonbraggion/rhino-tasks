package com.gilsonbraggion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gilsonbraggion.model.Liderado;
import com.gilsonbraggion.repository.LideradoRepository;

@Controller
@RequestMapping(value = "/liderado")
public class LideradoController {

	@Autowired
	private LideradoRepository repo;

	@GetMapping
	public String get(Model model) {
		List<Liderado> lista = repo.findAll();
		model.addAttribute("listagem", lista);
		return "logged/liderado/listagem";
	}

	@GetMapping(value = "/novo")
	public String novo(Liderado liderado) {
		return "logged/liderado/cadastro";
	}

	@GetMapping(value = "/editar")
	private String editar(Long idLiderado, RedirectAttributes atributo) {
		Liderado liderado = repo.findById(idLiderado).orElse(null);

		atributo.addFlashAttribute("liderado", liderado);
		return "redirect:/liderado/novo";
	}

	@PostMapping(value = "/salvar")
	public String salvar(Liderado liderado, Model model) {

		repo.save(liderado);

		return "redirect:/liderado";
	}

	@GetMapping(value = "/remover")
	private String remover(Long idLiderado) {
		repo.deleteById(idLiderado);
		return "redirect:/liderado";
	}

}