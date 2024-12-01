package com.dev.ProtoFin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

		List<Compra> compra = new ArrayList<>();

		List<Compra> compraTrue = new ArrayList<>();

		compra = compraRepository.findAll();

		for (Compra c : compra) {
			System.out.println(c.getGone());

			if (c.getGone().equals("2")) {
				compraTrue.add(c);
			}
		}

		mv.addObject("listaCompras", compraTrue);

		return mv;
	}

	@GetMapping("/client/pagamentos/listar")
	public ModelAndView listarId() {
		ModelAndView mv = new ModelAndView("client/pagamentos/lista");

		List<Compra> compra = new ArrayList<>();

		List<Compra> compraTrue = new ArrayList<>();

		compra = compraRepository.findAll();

		buscarUsuarioLogado();

		for (Compra c : compra) {
			if (c.getFuncionario().getId().equals(funcionario.getId())) {
				if (c.getGone().equals("2")) {
					compraTrue.add(c);
				}
			}
		}

		mv.addObject("funcionario", funcionario);
		mv.addObject("listaCompras", compraTrue);

		return mv;
	}

	@GetMapping("/admin/pagamentos/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Compra> compra = compraRepository.findById(id);

		Compra compra1 = new Compra();

		compra1 = compra.get();
		compra1.setGone("1");

		compraRepository.saveAndFlush(compra1);

		return listar();
	}

}
