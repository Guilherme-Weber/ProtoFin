package com.dev.ProtoFin.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.ProtoFin.models.Funcionario;
import com.dev.ProtoFin.repository.CidadeRepository;
import com.dev.ProtoFin.repository.FuncionarioRepository;

@Controller
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@GetMapping("/admin/funcionarios/cadastrar")
	public ModelAndView cadastrar(Funcionario funcionario) {
		ModelAndView mv = new ModelAndView("admin/funcionarios/cadastro");
		mv.addObject("funcionario", funcionario);
		mv.addObject("listaCidades", cidadeRepository.findAll());
		return mv;
	}

	@GetMapping("/cliente/funcionarios/cadastrar")
	public ModelAndView cadastrarCliente(Funcionario funcionario) {
		ModelAndView mv = new ModelAndView("client/perfil/cadastro");
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		if (!(autenticado instanceof AnonymousAuthenticationToken)) {
			String email = autenticado.getName();
			funcionario = funcionarioRepository.buscarFuncionariEmail(email).get(0);
		}
		mv.addObject("funcionario", funcionario);
		return mv;
	}

	@GetMapping("/admin/funcionarios/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("admin/funcionarios/lista");
		mv.addObject("listaFuncionarios", funcionarioRepository.findAll());

		return mv;
	}

	@GetMapping("/admin/funcionarios/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		return cadastrar(funcionario.get());
	}

	@GetMapping("/admin/funcionarios/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		funcionarioRepository.delete(funcionario.get());

		return listar();
	}

	@PostMapping("/admin/funcionarios/salvar")
	public ModelAndView salvar(@Valid Funcionario funcionario, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return cadastrar(funcionario);
		}

		funcionario.setSenha(new BCryptPasswordEncoder().encode(funcionario.getSenha()));

		funcionarioRepository.saveAndFlush(funcionario);

		return cadastrar(new Funcionario());
	}

	@PostMapping("/cliente/funcionarios/salvar")
	public String salvarCliente(@Valid Funcionario funcionario, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "redirect:/";
		}

		funcionario.setSenha(new BCryptPasswordEncoder().encode(funcionario.getSenha()));

		funcionarioRepository.saveAndFlush(funcionario);

		return "redirect:/";
	}

}
