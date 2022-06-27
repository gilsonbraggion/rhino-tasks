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

import com.gilsonbraggion.model.AtividadeSubProjeto;
import com.gilsonbraggion.model.SubProjeto;
import com.gilsonbraggion.repository.AtividadeSubProjetoRepository;
import com.gilsonbraggion.repository.SubProjetoRepository;
import com.gilsonbraggion.util.Util;

@Controller
@RequestMapping(value = "/atividadeSubProjeto")
public class AtividadeSubProjetoController {

	@Autowired
	private AtividadeSubProjetoRepository repo;

	@Autowired
	private SubProjetoRepository repoSub;

	@ModelAttribute
	public void getLiderados(Model model) {
		
		Long idUsuario = Util.obterIdUsuarioLogado();
		
		List<SubProjeto> listagem = repoSub.findByIdUsuario(idUsuario);
		model.addAttribute("listagemSubProjeto", listagem);
	}

	@GetMapping
	public String get(Model model) {
		Long idUsuario = Util.obterIdUsuarioLogado();
		
		List<AtividadeSubProjeto> lista = repo.findByIdUsuario(idUsuario);
		model.addAttribute("listagem", lista);
		return "logged/atividadeSubProjeto/listagem";
	}

	@GetMapping(value = "/novo")
	public String novo(AtividadeSubProjeto atividadeSubProjeto) {
		return "logged/atividadeSubProjeto/cadastro";
	}

	@GetMapping(value = "/editar")
	private String editar(Long idAtividadeSubProjeto, RedirectAttributes atributo) {
		AtividadeSubProjeto atividadeSubProjeto = repo.findById(idAtividadeSubProjeto).orElse(null);

		atributo.addFlashAttribute("atividadeSubProjeto", atividadeSubProjeto);
		return "redirect:/atividadeSubProjeto/novo";
	}

	@PostMapping(value = "/salvar")
	public String salvar(AtividadeSubProjeto atividadeSubProjeto, Model model) {

		Long idUsuario = Util.obterIdUsuarioLogado();
		
		SubProjeto subProjeto = repoSub.findById(atividadeSubProjeto.getIdSubProjeto()).orElse(null);
		atividadeSubProjeto.setSubProjeto(subProjeto);

		repo.save(atividadeSubProjeto);

		List<AtividadeSubProjeto> lista = repo.buscarAtividadesPorSubProjeto(subProjeto.getId(), idUsuario);

		model.addAttribute("listagem", lista);
		return "logged/atividadeSubProjeto/listagem";
	}

	@GetMapping(value = "/remover")
	private String remover(Long idAtividadeSubProjeto) {
		repo.deleteById(idAtividadeSubProjeto);
		return "redirect:/atividadeSubProjeto";
	}
	
	@GetMapping(value = "/listaBySub")
	private String buscarListaPirSubProjeto(Long idSubProjeto, Model model) {
		
		Long idUsuario = Util.obterIdUsuarioLogado();
		List<AtividadeSubProjeto> lista = repo.buscarAtividadesPorSubProjeto(idSubProjeto, idUsuario);

		model.addAttribute("listagem", lista);
		return "logged/atividadeSubProjeto/listagem";
		
	}


}