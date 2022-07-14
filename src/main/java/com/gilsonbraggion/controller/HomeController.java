package com.gilsonbraggion.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gilsonbraggion.bean.TipoAtividadeBean;
import com.gilsonbraggion.model.Atividade;
import com.gilsonbraggion.model.TipoAtividade;
import com.gilsonbraggion.repository.AtividadeRepository;
import com.gilsonbraggion.repository.TipoAtividadeRepository;
import com.gilsonbraggion.util.Util;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

	@Autowired
	private AtividadeRepository ativRepo;

	@Autowired
	private TipoAtividadeRepository tipoRepo;

	@ModelAttribute
	public void getListagem(Model model) {
		Long idUsuario = Util.obterIdUsuarioLogado();
		List<TipoAtividade> listagemTipo = tipoRepo.findByIdUsuario(Util.obterIdUsuarioLogado());

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
	}

	@GetMapping
	public String home(Model model) {
		return "home";
	}

	@GetMapping(value = "/listagemHome")
	public String listagemHome(Long idTipoAtividade, Model model) {

		Long idUsuario = Util.obterIdUsuarioLogado();
		
		TipoAtividade tipoAtividade = tipoRepo.findById(idTipoAtividade).orElse(null);

		List<Atividade> listagem = ativRepo.buscarAtividadesPorTipoAtividadeAtivos(idTipoAtividade, idUsuario);
		model.addAttribute("listagemAtividades", listagem);
		model.addAttribute("tipoAtividade", tipoAtividade);

		return "home";
	}

}
