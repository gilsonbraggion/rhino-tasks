package com.gilsonbraggion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gilsonbraggion.model.Reuniao;
import com.gilsonbraggion.repository.ReuniaoRepository;

@Controller
@RequestMapping(value = "/reuniao")
public class ReuniaoController {

	@Autowired
	private ReuniaoRepository repo;

	@GetMapping
	public String get(Model model) {
		List<Reuniao> lista = repo.findAll();
		model.addAttribute("listagem", lista);
		return "logged/reuniao/listagem";
	}

	@GetMapping(value = "/novo")
	public String novo(Reuniao reuniao) {
		return "logged/reuniao/cadastro";
	}

	@GetMapping(value = "/editar")
	private String editar(Long idReuniao, RedirectAttributes atributo) {
		Reuniao reuniao = repo.findById(idReuniao).orElse(null);

		atributo.addFlashAttribute("reuniao", reuniao);
		return "redirect:/reuniao/novo";
	}

	@PostMapping(value = "/salvar")
	public String salvar(Reuniao reuniao) {

		repo.save(reuniao);

		return "redirect:/reuniao";
	}

	@GetMapping(value = "/remover")
	private String remover(Long idReuniao) {
		repo.deleteById(idReuniao);
		return "redirect:/reuniao";
	}

}