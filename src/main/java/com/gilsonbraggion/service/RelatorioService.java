package com.gilsonbraggion.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gilsonbraggion.bean.ResumoAtividadeSubProjetoBean;
import com.gilsonbraggion.model.AtividadeSubProjeto;
import com.gilsonbraggion.repository.AtividadeSubProjetoRepository;

@Service
public class RelatorioService {

	@Autowired
	private AtividadeSubProjetoRepository atividadeSub;

	public List<ResumoAtividadeSubProjetoBean> buscarResumoAtividadeSubProjeto(Long idSubProjeto) {

		List<AtividadeSubProjeto> retorno = atividadeSub.buscarAtividadesPorSubProjeto(idSubProjeto);

		double totalDesenvolvimento = 0d;
		double totalInicial = 0d;
		double totalCompleto = 0d;

		Map<String, Double> mapValores = new HashMap<String, Double>();
		for (AtividadeSubProjeto item : retorno) {
			totalDesenvolvimento = item.getPercentualDesenvolvimento() + totalDesenvolvimento;
			totalInicial = item.getPercentualTesteEssencial() + totalInicial;
			totalCompleto = item.getPercentualTesteCompleto() + totalCompleto;
		}

		List<ResumoAtividadeSubProjetoBean> listaRetorno = new ArrayList<ResumoAtividadeSubProjetoBean>();
		mapValores.put("desenvolvimento", totalDesenvolvimento);
		mapValores.put("inicial", totalInicial);
		mapValores.put("completo", totalCompleto);

		listaRetorno.add(new ResumoAtividadeSubProjetoBean("Desenvolvimento", mapValores.get("desenvolvimento") / retorno.size()));
		listaRetorno.add(new ResumoAtividadeSubProjetoBean("Inicial", mapValores.get("inicial") / retorno.size()));
		listaRetorno.add(new ResumoAtividadeSubProjetoBean("Completo", mapValores.get("completo") / retorno.size()));

		return listaRetorno;

	}

}
