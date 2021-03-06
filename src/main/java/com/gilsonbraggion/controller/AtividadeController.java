package com.gilsonbraggion.controller;

import java.util.List;
import java.util.Optional;

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
import com.gilsonbraggion.util.Util;

@Controller
@RequestMapping(value = "/atividade")
public class AtividadeController {

	@Autowired
	private AtividadeRepository repo;

	@Autowired
	private TipoAtividadeRepository repoTipo;

	@ModelAttribute
	public void getTiposAtividade(Model model) {
		Long idUsuario = Util.obterIdUsuarioLogado();
		
		List<TipoAtividade> listagem = repoTipo.findByIdUsuario(idUsuario);
		model.addAttribute("listagemTipo", listagem);
	}

	@GetMapping
	public String get(Model model) {
		Long idUsuario = Util.obterIdUsuarioLogado();

		List<Atividade> lista = repo.findByIdUsuarioOrderByDataExecucaoAsc(idUsuario);
		model.addAttribute("listagem", lista);
		return "logged/atividade/listagem";
	}

	@GetMapping(value = "/novo")
	public String novo(Atividade atividade) {
		return "logged/atividade/cadastro";
	}

	@GetMapping(value = "/atividadeFromHome")
	public String atividadeFromHome(Long idTipoAtividade, Model model) {
		Atividade atividade = new Atividade();
		atividade.setFromHome(true);

		Optional<TipoAtividade> tipoAtividade = repoTipo.findById(idTipoAtividade);

		if (tipoAtividade.isPresent()) {
			atividade.setTipoAtividade(tipoAtividade.get());
		}

		model.addAttribute("atividade", atividade);

		return "logged/atividade/cadastro";
	}

	@GetMapping(value = "/filtrar")
	public String filtro(TipoAtividade tipoAtividade, Model model) {
		
		Long idUsuario = Util.obterIdUsuarioLogado();

		List<Atividade> listagem = repo.buscarAtividadesPorTipoAtividadeAtivos(tipoAtividade.getId(), idUsuario);
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
	public String salvar(Atividade atividade, Model model, RedirectAttributes atributo) {

		TipoAtividade tipoAtividade = repoTipo.findById(atividade.getIdTipoAtividade()).orElse(null);
		atividade.setTipoAtividade(tipoAtividade);

		repo.save(atividade);

		if (atividade.isFromHome()) {
			atributo.addFlashAttribute("idTipoAtividade", tipoAtividade.getId());
			return "redirect:/home/listagemHome";
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