package com.dev.ProtoFin.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.ProtoFin.models.Permissao;
import com.dev.ProtoFin.repository.FuncionarioRepository;
import com.dev.ProtoFin.repository.PapelRepository;
import com.dev.ProtoFin.repository.PermissaoRepository;

@Controller
public class PermissaoController {

	@Autowired
	private PermissaoRepository permissaoRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private PapelRepository papelRepository;

	@GetMapping("/admin/permissoes/cadastrar")
	public ModelAndView cadastrar(Permissao permissao) {
		ModelAndView mv = new ModelAndView("admin/permissoes/cadastro");
		mv.addObject("permissao", permissao);
		mv.addObject("listaFuncionarios", funcionarioRepository.findAll());
		mv.addObject("listaPapeis", papelRepository.findAll());
		return mv;
	}

	@GetMapping("/admin/permissoes/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Permissao> permissao = permissaoRepository.findById(id);
		return cadastrar(permissao.get());
	}

	@GetMapping("/admin/permissoes/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("admin/permissoes/lista");
		mv.addObject("listaPermissoes", permissaoRepository.findAll());

		return mv;
	}

	@GetMapping("/admin/permissoes/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Permissao> permissao = permissaoRepository.findById(id);
		permissaoRepository.delete(permissao.get());

		return listar();
	}

	@PostMapping("/admin/permissoes/salvar")
	public ModelAndView salvar(@Valid Permissao permissao, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return cadastrar(permissao);
		}

		permissaoRepository.saveAndFlush(permissao);

		return cadastrar(new Permissao());
	}

}
