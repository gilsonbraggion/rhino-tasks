package com.gilsonbraggion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gilsonbraggion.model.AtividadeSubProjeto;
import com.gilsonbraggion.model.SubProjeto;
import com.gilsonbraggion.repository.AtividadeSubProjetoRepository;
import com.gilsonbraggion.repository.SubProjetoRepository;
import com.gilsonbraggion.util.Util;

@Controller
@RequestMapping(value = "/grafico")
public class GraficoController {

	@Autowired
	private SubProjetoRepository subRepo;

	@Autowired
	private AtividadeSubProjetoRepository ativRepo;

	@ModelAttribute
	public void getSubProjetos(Model model) {
		List<SubProjeto> listaSubProjetos = subRepo.findAll();
		model.addAttribute("listaSubProjeto", listaSubProjetos);
	}

	@GetMapping(value = "/resumoAtividadeSubProjeto")
	public String getResumoAtividadesSubProjeto(Model model) {
		return "logged/atividadeSubProjeto/resumo";
	}

	@PostMapping(value = "/buscarResumoAtividadeSubProjeto")
	public String buscarResumoAtividadesSubProjeto(Long idSubProjeto, Model model) {
		Long idUsuario = Util.obterIdUsuarioLogado();
		List<AtividadeSubProjeto> listagemAtividades = ativRepo.buscarAtividadesPorSubProjeto(idSubProjeto, idUsuario);

		model.addAttribute("listagemAtividades", listagemAtividades);
		model.addAttribute("subSelecionado", idSubProjeto);
		model.addAttribute("exibirDetalhe", true);

		return "logged/atividadeSubProjeto/resumo";
	}

}
