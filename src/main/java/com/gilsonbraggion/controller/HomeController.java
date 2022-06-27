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
import com.gilsonbraggion.util.Util;

@Controller
public class HomeController {

	@Autowired
	private AtividadeRepository ativRepo;

	@Autowired
	private TipoAtividadeRepository tipoRepo;

	@GetMapping(value = { "/home" })
	public String home(Model model) {
		Long idUsuario = Util.obterIdUsuarioLogado();
		List<TipoAtividade> listagemTipo = tipoRepo.findAll();

		List<TipoAtividadeBean> listaInternaBean = new ArrayList<>();

		for (int i = 0; i < listagemTipo.size(); i++) {

			TipoAtividade tipoAtividade = listagemTipo.get(i);

			TipoAtividadeBean bean = new TipoAtividadeBean();
			bean.setIdTipoAtividade(tipoAtividade.getId());
			bean.setNomeTipoAtividade(tipoAtividade.getNome());

			bean.setListaAtividades(ativRepo.buscarAtividadesPorTipoAtividadeAtivos(tipoAtividade.getId(), idUsuario));

			if (!bean.getListaAtividades().isEmpty()) {
				bean.setDataMaisProxima(Util.getDateString(bean.getListaAtividades().get(0).getDataExecucao()));
			}

			listaInternaBean.add(bean);

		}

		model.addAttribute("listaPainel", listaInternaBean);

		return "home";
	}

}
