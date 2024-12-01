package com.dev.ProtoFin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.ProtoFin.models.Funcionario;
import com.dev.ProtoFin.models.Produto;
import com.dev.ProtoFin.repository.FuncionarioRepository;
import com.dev.ProtoFin.repository.ProdutoRepository;

@Controller
public class PrimaryController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	private Funcionario funcionario;

	@GetMapping("/admin")
	public String acessarPrincipal() {
		return "admin/home";
	}

	@GetMapping("/prof")
	public ModelAndView acessarProfessor() {
		ModelAndView mv = new ModelAndView("/admin/professores/cadastro");
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		if (!(autenticado instanceof AnonymousAuthenticationToken)) {
			String email = autenticado.getName();
			funcionario = funcionarioRepository.buscarFuncionariEmail(email).get(0);
		}
		mv.addObject("funcionario", funcionario);
		return mv;
	}

	@GetMapping("/professor/modalidade/listar")
	public ModelAndView index1() {
		ModelAndView mv = new ModelAndView("/admin/produtos/listaP");
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		if (!(autenticado instanceof AnonymousAuthenticationToken)) {
			String email = autenticado.getName();
			funcionario = funcionarioRepository.buscarFuncionariEmail(email).get(0);
		}
		mv.addObject("funcionario", funcionario);

		List<Produto> produto = new ArrayList<>();

		List<Produto> produtoTrue = new ArrayList<>();

		produto = produtoRepository.findAll();

		for (Produto c : produto) {
			if (c.getMarca().equals(funcionario.getNome())) {

//				System.out.println(funcionario.getNome());
//				System.out.println(c.getMarca());

				produtoTrue.add(c);
			}
		}

		mv.addObject("listaProdutos", produtoTrue);
		return mv;
	}

}
