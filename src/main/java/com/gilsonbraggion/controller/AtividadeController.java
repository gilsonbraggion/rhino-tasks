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

import com.gilsonbraggion.model.Atividade;
import com.gilsonbraggion.model.TipoAtividade;
import com.gilsonbraggion.repository.AtividadeRepository;
import com.gilsonbraggion.repository.TipoAtividadeRepository;

@Controller
@RequestMapping(value = "/atividade")
public class AtividadeController {

	@Autowired
	private AtividadeRepository repo;

	@Autowired
	private TipoAtividadeRepository repoTipo;

	@ModelAttribute
	public void getTiposAtividade(Model model) {
		List<TipoAtividade> listagem = repoTipo.findAll();
		model.addAttribute("listagemTipo", listagem);
	}

	@GetMapping
	public String get(Model model) {
		List<Atividade> lista = repo.findByOrderByDataExecucaoAsc();
		model.addAttribute("listagem", lista);
		return "logged/atividade/listagem";
	}

	@GetMapping(value = "/novo")
	public String novo(Atividade atividade) {
		return "logged/atividade/cadastro";
	}

	@GetMapping(value = "/filtrar")
	public String filtro(TipoAtividade tipoAtividade, Model model) {

		List<Atividade> listagem = repo.findByTipoAtividadeAndFinalizadoIsNullOrFinalizadoFalseOrderByDataExecucaoAsc(tipoAtividade);
		model.addAttribute("listagem", listagem);
		model.addAttribute("tipoAtividade", tipoAtividade);

		return "logged/atividade/listagem";
	}

	@GetMapping(value = "/editar")
	private String editar(Long idAtividade, boolean fromHome, RedirectAttributes atributo) {
		Atividade atividade = repo.findById(idAtividade).orElse(null);
		atividade.setFromHome(fromHome);
		
		atributo.addFlashAttribute("atividade", atividade);
		return "redirect:/atividade/novo";
	}

	@PostMapping(value = "/salvar")
	public String salvar(Atividade atividade, Model model) {

		TipoAtividade tipoAtividade = repoTipo.findById(atividade.getIdTipoAtividade()).orElse(null);
		atividade.setTipoAtividade(tipoAtividade);

		repo.save(atividade);
		
		if (atividade.isFromHome()) {
			return "redirect:/home";
		} else {
			return "redirect:/atividade";	
		}
	}

	@GetMapping(value = "/remover")
	private String remover(Long idAtividade) {
		repo.deleteById(idAtividade);
		return "redirect:/atividade";
	}

}