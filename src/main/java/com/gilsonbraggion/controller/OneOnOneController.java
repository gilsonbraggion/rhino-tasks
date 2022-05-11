package com.gilsonbraggion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gilsonbraggion.model.Liderado;
import com.gilsonbraggion.model.OneOnOne;
import com.gilsonbraggion.repository.LideradoRepository;
import com.gilsonbraggion.repository.OneOnOneRepository;

@Controller
@RequestMapping(value = "/oneOnOne")
public class OneOnOneController {

	@Autowired
	private OneOnOneRepository repo;

	@Autowired
	private LideradoRepository repoLiderado;

	@ModelAttribute
	public void getLiderados(Model model) {
		List<Liderado> listagem = repoLiderado.findAll();
		model.addAttribute("listagemLiderado", listagem);
	}

	@GetMapping
	public String get(Model model) {
		List<OneOnOne> lista = repo.findByOrderByDataAsc();
		model.addAttribute("listagem", lista);
		return "logged/oneOnOne/listagem";
	}

	@GetMapping(value = "/novo")
	public String novo(OneOnOne oneOnOne) {
		return "logged/oneOnOne/cadastro";
	}

	@GetMapping(value = "/editar")
	private String editar(Long idOneOnOne, RedirectAttributes atributo) {
		OneOnOne oneOnOne = repo.findById(idOneOnOne).orElse(null);

		atributo.addFlashAttribute("oneOnOne", oneOnOne);
		return "redirect:/oneOnOne/novo";
	}

	@PostMapping(value = "/salvar")
	public String salvar(OneOnOne oneOnOne, Model model) {

		Liderado liderado = repoLiderado.findById(oneOnOne.getIdLiderado()).orElse(null);
		oneOnOne.setLiderado(liderado);

		repo.save(oneOnOne);

		return "redirect:/oneOnOne";
	}

	@GetMapping(value = "/remover")
	private String remover(Long idOneOnOne) {
		repo.deleteById(idOneOnOne);
		return "redirect:/oneOnOne";
	}

	@GetMapping(value = "/listagemOneOnOne")
	private String listagemPorLiderado(Long idLiderado, Model model) {
		
		Liderado liderado = repoLiderado.findById(idLiderado).orElse(null);
		
		List<OneOnOne> listagem = repo.findByLideradoOrderByDataAsc(liderado);
		
		model.addAttribute("listagem", listagem);
		
		return "logged/oneOnOne/listagem";
	}
	
}