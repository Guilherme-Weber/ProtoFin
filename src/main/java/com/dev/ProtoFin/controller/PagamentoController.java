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

import com.dev.ProtoFin.models.Compra;
import com.dev.ProtoFin.models.Funcionario;
import com.dev.ProtoFin.repository.CompraRepository;
import com.dev.ProtoFin.repository.FuncionarioRepository;

@Controller
public class PagamentoController {

	private Funcionario funcionario;

	@Autowired
	private CompraRepository compraRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	private void buscarUsuarioLogado() {
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		if (!(autenticado instanceof AnonymousAuthenticationToken)) {
			String email = autenticado.getName();
			funcionario = funcionarioRepository.buscarFuncionariEmail(email).get(0);
		}
	}

	@GetMapping("/admin/pagamentos/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("admin/pagamentos/lista");

		mv.addObject("listaCompras", compraRepository.findAll());

		return mv;
	}

	@GetMapping("/client/pagamentos/listar")
	public ModelAndView listarId() {
		ModelAndView mv = new ModelAndView("client/pagamentos/lista");

		List<Compra> compra = new ArrayList<Compra>();

		List<Compra> compraTrue = new ArrayList<Compra>();

		compra = compraRepository.findAll();

		buscarUsuarioLogado();

		for (Compra c : compra) {
			if (c.getFuncionario().getId().equals(funcionario.getId())) {
				compraTrue.add(c);
			}
		}

		mv.addObject("funcionario", funcionario);
		mv.addObject("listaCompras", compraTrue);

		return mv;
	}

}
