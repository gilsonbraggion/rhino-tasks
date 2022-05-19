package com.gilsonbraggion.controller;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gilsonbraggion.bean.FiltroDatas;
import com.gilsonbraggion.model.Liderado;
import com.gilsonbraggion.model.OneOnOne;
import com.gilsonbraggion.repository.LideradoRepository;
import com.gilsonbraggion.repository.OneOnOneRepository;

@Controller
@RequestMapping(value = "/oneOnOne")
public class OneOnOneController {

	@Autowired
	private OneOnOneRepository repo;

	@Autowired
	private LideradoRepository repoLiderado;

	@ModelAttribute
	public void getDatasFiltro(Model model) {
		List<FiltroDatas> datas = repo.buscarDatas();
		Locale BRAZIL = new Locale("pt", "BR");

		for (FiltroDatas item : datas) {
			String nomeMes = StringUtils.capitalize(Month.of(item.getMes()).getDisplayName(TextStyle.FULL_STANDALONE, BRAZIL));
			item.setNomeMes(nomeMes);
		}
		model.addAttribute("datas", datas);
	}

	@ModelAttribute
	public void getLiderados(Model model) {
		List<Liderado> listagem = repoLiderado.findAll();
		model.addAttribute("listagemLiderado", listagem);
	}
	
	@GetMapping
	public String get(Model model) {
		List<OneOnOne> lista = repo.findByOrderByDataAsc();
		model.addAttribute("listagem", lista);
		return "logged/oneOnOne/listagem";
	}

	@GetMapping(value = "naoRealizados")
	public String getNaoRealizados(Model model) {
		List<OneOnOne> lista = repo.buscarNaoRealizados();
		model.addAttribute("listagem", lista);
		return "logged/oneOnOne/listagem";
	}
	
	@GetMapping(value = "/novo")
	public String novo(OneOnOne oneOnOne) {
		return "logged/oneOnOne/cadastro";
	}

	@GetMapping(value = "/editar")
	private String editar(Long idOneOnOne, RedirectAttributes atributo) {
		OneOnOne oneOnOne = repo.findById(idOneOnOne).orElse(null);

		atributo.addFlashAttribute("oneOnOne", oneOnOne);
		return "redirect:/oneOnOne/novo";
	}

	@PostMapping(value = "/salvar")
	public String salvar(OneOnOne oneOnOne, Model model) {

		Liderado liderado = repoLiderado.findById(oneOnOne.getIdLiderado()).orElse(null);
		oneOnOne.setLiderado(liderado);

		repo.save(oneOnOne);

		return "redirect:/oneOnOne";
	}

	@GetMapping(value = "/remover")
	private String remover(Long idOneOnOne) {
		repo.deleteById(idOneOnOne);
		return "redirect:/oneOnOne";
	}

	@GetMapping(value = "/datas")
	private String popularDatas(Model model) {
		return "logged/oneOnOne/datas";
	}

	@PostMapping(value = "/pesquisarDatas")
	private String pesquisarDatas(Model model, String dataSelecionada) {

		String[] selecao = dataSelecionada.split(" ");
		List<OneOnOne> listagem = repo.buscarOneonOnePorData(Integer.valueOf(selecao[0]),Integer.valueOf(selecao[1]));

		model.addAttribute("listagem", listagem);
		
		FiltroDatas filtro = new FiltroDatas(Integer.valueOf(selecao[0]), Integer.valueOf(selecao[1]));
		model.addAttribute("dataSelecionada", filtro);

		return "logged/oneOnOne/datas";
	}

	@GetMapping(value = "/liderado")
	private String buscarPorLiderado() {

		return "redirect:/oneOnOne/liderados";
	}

	@GetMapping(value = "/listagemOneOnOne")
	private String listagemPorLiderado(Long idLiderado, Model model) {

		Liderado liderado = repoLiderado.findById(idLiderado).orElse(null);

		List<OneOnOne> listagem = repo.findByLideradoOrderByDataAsc(liderado);

		model.addAttribute("listagem", listagem);

		return "logged/oneOnOne/listagem";
	}

}