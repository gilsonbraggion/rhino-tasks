package com.gilsonbraggion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gilsonbraggion.model.TipoAtividade;
import com.gilsonbraggion.repository.TipoAtividadeRepository;
import com.gilsonbraggion.util.Util;

@Controller
@RequestMapping(value = "/tipoAtividade")
public class TipoAtividadeController {

	@Autowired
	private TipoAtividadeRepository repo;

	@GetMapping
	public String getTipoAtividadesAtividades(Model model) {
		Long idUsuario = Util.obterIdUsuarioLogado();
		model.addAttribute("listagem", repo.findByIdUsuario(idUsuario));
		return "logged/tipoAtividade/listagem";
	}

	@GetMapping(value = "/novo")
	public String novo(TipoAtividade tipoAtividade) {
		return "logged/tipoAtividade/cadastro";
	}

	@GetMapping(value = "/editar")
	private String editar(Long idTipoAtividade, RedirectAttributes atributo) {
		TipoAtividade tipoAtividade = repo.findById(idTipoAtividade).orElse(null);
		atributo.addFlashAttribute("tipoAtividade", tipoAtividade);
		return "redirect:/tipoAtividade/novo";
	}

	@PostMapping(value = "/salvar")
	public String salvar(TipoAtividade tipoAtividade, Model model) {
		repo.save(tipoAtividade);
		return "redirect:/tipoAtividade";
	}

	@GetMapping(value = "/remover")
	private String remover(Long idTipoAtividade) {
		repo.deleteById(idTipoAtividade);
		return "redirect:/tipoAtividade";
	}

	@GetMapping(value = "/filtrar")
	private String filtrar(Long idTipoAtividade, RedirectAttributes atributo) {

		TipoAtividade tipoAtividade = repo.findById(idTipoAtividade).orElse(null);
		atributo.addFlashAttribute("tipoAtividade", tipoAtividade);

		return "redirect:/atividade/filtrar";
	}

}