package com.gilsonbraggion.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gilsonbraggion.bean.PainelBean;
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

		List<PainelBean> listaPainel = new ArrayList<PainelBean>();

		Integer iterador = 1;
		List<TipoAtividadeBean> listaInternaBean = new ArrayList<>();
		for (int i = 0; i < listagemTipo.size(); i++) {

			PainelBean painel = new PainelBean();
			painel.setIterador(iterador);

			TipoAtividade tipoAtividade = listagemTipo.get(i);

			TipoAtividadeBean bean = new TipoAtividadeBean();
			bean.setNomeTipoAtividade(tipoAtividade.getNome());
			bean.setListaAtividades(ativRepo.findByTipoAtividadeAndDataFinalizacaoIsNullOrderByDataExecucaoAsc(tipoAtividade));
			listaInternaBean.add(bean);

			if (i % 2 != 0) {

				iterador++;

			} else {
				continue;
			}

			if (i + 1 == listagemTipo.size()) {
				painel.setListaTipoAtividade(listaInternaBean.stream().collect(Collectors.toList()));
				listaPainel.add(painel);
				listaInternaBean.clear();

			}
		}

		model.addAttribute("listaPainel", listaPainel);

		return "home";
	}

}
