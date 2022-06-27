package com.gilsonbraggion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gilsonbraggion.bean.ResumoAtividadeSubProjetoBean;
import com.gilsonbraggion.service.RelatorioService;
import com.gilsonbraggion.util.Util;

@RestController
@RequestMapping(value = "/relatorio")
public class RelatorioController {

	@Autowired
	private RelatorioService relatService;

	@RequestMapping(value = "/buscarGraficoResumoAtividades", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> buscarResumoAtividadeSubProjeto(@RequestParam("idSubProjeto") Long idSubProjeto, Model model) {

		Long idUsuario = Util.obterIdUsuarioLogado();

		List<ResumoAtividadeSubProjetoBean> listaResumo = relatService.buscarResumoAtividadeSubProjeto(idSubProjeto, idUsuario);

		return ResponseEntity.ok(listaResumo);
	}

}
