package com.gilsonbraggion.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gilsonbraggion.bean.TipoAtividadeBean;
import com.gilsonbraggion.model.TipoAtividade;
import com.gilsonbraggion.repository.AtividadeRepository;
import com.gilsonbraggion.repository.TipoAtividadeRepository;

@Controller
public class HomeController {

	@Autowired
	private AtividadeRepository ativRepo;

	@Autowired
	private TipoAtividadeRepository tipoRepo;

	@GetMapping(value = { "/home" })
	public String home(Model model) {

		List<TipoAtividade> listagemTipo = tipoRepo.findAll();

		List<TipoAtividadeBean> listaInternaBean = new ArrayList<>();

		for (int i = 0; i < listagemTipo.size(); i++) {

			TipoAtividade tipoAtividade = listagemTipo.get(i);

			TipoAtividadeBean bean = new TipoAtividadeBean();
			bean.setIdTipoAtividade(tipoAtividade.getId());
			bean.setNomeTipoAtividade(tipoAtividade.getNome());
			bean.setListaAtividades(ativRepo.buscarAtividadesPorTipoAtividadeAtivos(tipoAtividade.getId()));

			listaInternaBean.add(bean);

		}

		model.addAttribute("listaPainel", listaInternaBean);

		return "home";
	}

}
