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

import com.gilsonbraggion.model.Projeto;
import com.gilsonbraggion.model.SubProjeto;
import com.gilsonbraggion.repository.ProjetoRepository;
import com.gilsonbraggion.repository.SubProjetoRepository;
import com.gilsonbraggion.util.Util;

@Controller
@RequestMapping(value = "/subProjeto")
public class SubProjetoController {

	@Autowired
	private SubProjetoRepository repo;

	@Autowired
	private ProjetoRepository repoProjeto;

	@ModelAttribute
	public void getLiderados(Model model) {
		
		Long idUsuario = Util.obterIdUsuarioLogado();
		List<Projeto> listagem = repoProjeto.findByIdUsuario(idUsuario);
		model.addAttribute("listagemProjeto", listagem);
	}

	@GetMapping
	public String get(Model model) {
		
		Long idUsuario = Util.obterIdUsuarioLogado();
		List<SubProjeto> lista = repo.buscarSubrojetosMeusCompartilhados(idUsuario);
		model.addAttribute("listagem", lista);
		model.addAttribute("idUsuarioLogado", idUsuario);
		
		return "logged/subProjeto/listagem";
	}

	@GetMapping(value = "/novo")
	public String novo(SubProjeto subProjeto) {
		return "logged/subProjeto/cadastro";
	}

	@GetMapping(value = "/editar")
	private String editar(Long idSubProjeto, RedirectAttributes atributo) {
		SubProjeto subProjeto = repo.findById(idSubProjeto).orElse(null);

		atributo.addFlashAttribute("subProjeto", subProjeto);
		return "redirect:/subProjeto/novo";
	}

	@PostMapping(value = "/salvar")
	public String salvar(SubProjeto subProjeto) {

		Projeto projeto = repoProjeto.findById(subProjeto.getIdProjeto()).orElse(null);
		subProjeto.setProjeto(projeto);
		
		repo.save(subProjeto);
		

		return "redirect:/subProjeto";
	}

	@GetMapping(value = "/remover")
	private String remover(Long idSubProjeto) {
		repo.deleteById(idSubProjeto);
		return "redirect:/subProjeto";
	}

}