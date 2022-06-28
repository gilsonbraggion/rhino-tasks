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
import com.gilsonbraggion.model.ProjetoCompartilhado;
import com.gilsonbraggion.model.SubProjeto;
import com.gilsonbraggion.model.Usuario;
import com.gilsonbraggion.repository.ProjetoCompartilhadoRepository;
import com.gilsonbraggion.repository.SubProjetoRepository;
import com.gilsonbraggion.repository.UsuarioRepository;
import com.gilsonbraggion.util.Util;

@Controller
@RequestMapping(value = "/administracao")
public class AdministracaoController {

	@Autowired
	private UsuarioRepository usuRepo;

	@Autowired
	private SubProjetoRepository subRepo;

	@Autowired
	private ProjetoCompartilhadoRepository projetoCompRepo;

	@ModelAttribute
	public void listaUsuario(Model model) {
		Long idUsuario = Util.obterIdUsuarioLogado();
		List<Usuario> listagemUsuario = usuRepo.buscarUsuariosForaLogado(idUsuario);

		model.addAttribute("listagemUsuario", listagemUsuario);
	}

	@ModelAttribute
	public void listaSubProjetos(Model model) {
		Long idUsuario = Util.obterIdUsuarioLogado();
		List<SubProjeto> subProjetos = subRepo.findByIdUsuario(idUsuario);

		model.addAttribute("listagemSubProjetos", subProjetos);
	}

	@GetMapping
	public String getAdministracao(Model model) {

		Long idUsuarioLogado = Util.obterIdUsuarioLogado();
		List<ProjetoCompartilhado> listagemCompartilhados = projetoCompRepo.buscarProjetosCompartilhados(idUsuarioLogado);
		
		List<ProjetoCompartilhadoBean> listaRetorno = new ArrayList<ProjetoCompartilhadoBean>();
		for (ProjetoCompartilhado item : listagemCompartilhados ) {
			ProjetoCompartilhadoBean bean = new ProjetoCompartilhadoBean();
			
			Usuario usuarioConvidado = usuRepo.findById(item.getIdInvited()).get();
			bean.setIdConvidado(idUsuarioLogado);
			bean.setNomeConvidado(usuarioConvidado.getUserName());
			
			Usuario usuarioProprietario = usuRepo.findById(item.getIdOwner()).get();
			bean.setIdProprietario(idUsuarioLogado);
			bean.setNomeProprietario(usuarioProprietario.getUserName());
			
			SubProjeto subProjeto = subRepo.findById(item.getIdSubProjeto()).orElse(null);
			bean.setIdSubProjeto(idUsuarioLogado);
			bean.setNomeSubProjeto(subProjeto != null ?  subProjeto.getNomeSubProjeto() : "");
			
			listaRetorno.add(bean);
		}

		model.addAttribute("idUsuarioLogado", idUsuarioLogado);
		model.addAttribute("listagemCompartilhados", listaRetorno);

		return "logged/administracao/listagem";
	}

	@PostMapping(value = "/compartilhar")
	public String compartilhar(Long idUsuarioInvited, Long idSubProjeto, Model model) {

		ProjetoCompartilhado comp = new ProjetoCompartilhado();
		comp.setIdOwner(Util.obterIdUsuarioLogado());
		comp.setIdInvited(idUsuarioInvited);
		comp.setIdSubProjeto(idSubProjeto);

		projetoCompRepo.save(comp);

		return "redirect:/administracao";
	}

	@PostMapping(value = "/removerCompartilhamento")
	public String removerCompartilhamento(Long idConvidado, Long idSubProjeto, Model model) {

		ProjetoCompartilhado projetoCompartilhado = projetoCompRepo.findByIdInvitedAndIdSubProjeto(idConvidado, idSubProjeto);
		projetoCompRepo.delete(projetoCompartilhado);

		return "redirect:/administracao";
	}

	@GetMapping(value = "/zerarCompartilhamento")
	public String zerarCompartilhamento() {
		
		projetoCompRepo.deleteAll();
		
		return "redirect:/administracao";
	}
	
}
