package com.gilsonbraggion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gilsonbraggion.model.Projeto;
import com.gilsonbraggion.repository.ProjetoRepository;
import com.gilsonbraggion.util.Util;

@Controller
@RequestMapping(value = "/projeto")
public class ProjetoController {

	@Autowired
	private ProjetoRepository repo;

	@GetMapping
	public String get(Model model) {
		Long idUsuario = Util.obterIdUsuarioLogado();
		List<Projeto> lista = repo.findByIdUsuario(idUsuario);
		model.addAttribute("listagem", lista);
		return "logged/projeto/listagem";
	}

	@GetMapping(value = "/novo")
	public String novo(Projeto projeto) {
		return "logged/projeto/cadastro";
	}

	@GetMapping(value = "/editar")
	private String editar(Long idProjeto, RedirectAttributes atributo) {
		Projeto projeto = repo.findById(idProjeto).orElse(null);

		atributo.addFlashAttribute("projeto", projeto);
		return "redirect:/projeto/novo";
	}

	@PostMapping(value = "/salvar")
	public String salvar(Projeto projeto) {

		repo.save(projeto);

		return "redirect:/projeto";
	}

	@GetMapping(value = "/remover")
	private String remover(Long idProjeto) {
		repo.deleteById(idProjeto);
		return "redirect:/projeto";
	}

}