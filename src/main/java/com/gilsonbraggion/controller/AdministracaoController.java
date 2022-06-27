package com.gilsonbraggion.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gilsonbraggion.bean.ProjetoCompartilhadoBean;
import com.gilsonbraggion.model.Projeto;
import com.gilsonbraggion.model.ProjetoCompartilhado;
import com.gilsonbraggion.model.SubProjeto;
import com.gilsonbraggion.model.Usuario;
import com.gilsonbraggion.repository.ProjetoCompartilhadoRepository;
import com.gilsonbraggion.repository.ProjetoRepository;
import com.gilsonbraggion.repository.SubProjetoRepository;
import com.gilsonbraggion.repository.UsuarioRepository;
import com.gilsonbraggion.util.Util;

@Controller
@RequestMapping(value = "/administracao")
public class AdministracaoController {

	@Autowired
	private UsuarioRepository usuRepo;

	@Autowired
	private ProjetoRepository projRepo;

	@Autowired
	private SubProjetoRepository subRepo;

	@Autowired
	private ProjetoCompartilhadoRepository projetoCompRepo;
	
	@ModelAttribute
	public void listaUsuario(Model model) {
		Long idUsuario = Util.obterIdUsuarioLogado();
		List<Usuario> listagem = new ArrayList<Usuario>();
		Usuario usuario = usuRepo.findById(idUsuario).get();
		listagem.add(usuario);

		model.addAttribute("listagemUsuario", listagem);
	}

	@GetMapping
	public String getAdministracao() {
		return "logged/administracao/listagem";
	}

	@PostMapping(value = "/buscarProjetosPorUsuario")
	public String getProjetosByUser(Long idUsuario, Long idProjeto, Long idSubProjeto, Model model) {

		List<Projeto> listagemProjetos = projRepo.findByIdUsuario(idUsuario);

		model.addAttribute("listagemProjetos", listagemProjetos);
		model.addAttribute("usuarioSelecionado", idUsuario);

		if (idProjeto != null) {
			Projeto projeto = projRepo.findById(idProjeto).get();
			List<SubProjeto> listagemSubProjetos = subRepo.findByProjetoAndIdUsuario(projeto, idUsuario);
			model.addAttribute("listagemSubProjetos", listagemSubProjetos);
			model.addAttribute("projetoSelecionado", idProjeto);
		}

		if (idSubProjeto != null) {
			
			List<ProjetoCompartilhado> buscarProjetosCompartilhados = projetoCompRepo.buscarProjetosCompartilhados(idUsuario, idSubProjeto);
			List<ProjetoCompartilhadoBean> listagemRetorno = new ArrayList<ProjetoCompartilhadoBean>();
			
			for (ProjetoCompartilhado item : buscarProjetosCompartilhados) {
				ProjetoCompartilhadoBean bean = new ProjetoCompartilhadoBean();
				
				Usuario userOwner = usuRepo.findById(item.getIdOwner()).get();
				Usuario userConvidado = usuRepo.findById(item.getIdInvited()).get();
				SubProjeto subProjeto = subRepo.findById(idSubProjeto).get();
				
				bean.setIdProprietario(userOwner.getId());
				bean.setNomeProprietario(userOwner.getUserName());
				
				bean.setIdConvidado(userConvidado.getId());
				bean.setNomeConvidado(userConvidado.getUserName());
				
				bean.setIdSubProjeto(subProjeto.getId());
				bean.setNomeSubProjeto(subProjeto.getNomeSubProjeto());
				
				listagemRetorno.add(bean);
			}
			
			model.addAttribute("listagemCompartilhados", listagemRetorno);
		}

		return "logged/administracao/listagem";

	}

	@PostMapping(value = "/buscarCompartilhamentos")
	public String getCopartilhados(Long idUsuarioInvited, Long idSubProjeto, Model model) {

		model.addAttribute("exibirSelecionados", true);

		return "logged/administracao/listagem";
	}

	@PostMapping(value = "/compartilhar")
	public String compartilhar(Long idUsuarioInvited, Long idSubProjeto, Model model) {
		
		model.addAttribute("exibirSelecionados", true);
		
		return "logged/administracao/listagem";
	}

	@PostMapping(value = "/removerCompartilhamento")
	public String removerCompartilhamento(Long idConvidado, Long idSubProjeto, Model model) {
		
		ProjetoCompartilhado projetoCompartilhado = projetoCompRepo.findByIdInvitedAndIdSubProjeto(idConvidado, idSubProjeto);
		projetoCompRepo.delete(projetoCompartilhado);
		
		return "redirect:/administracao";
	}
	
}
