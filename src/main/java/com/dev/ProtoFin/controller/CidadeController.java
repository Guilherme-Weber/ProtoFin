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

import com.dev.ProtoFin.models.Cidade;
import com.dev.ProtoFin.repository.CidadeRepository;
import com.dev.ProtoFin.repository.EstadoRepository;

@Controller
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@GetMapping("/admin/cidades/cadastrar")
	public ModelAndView cadastrar(Cidade cidade) {
		ModelAndView mv = new ModelAndView("admin/cidades/cadastro");
		mv.addObject("cidade", cidade);
		mv.addObject("listaEstados", estadoRepository.findAll());
		return mv;
	}

	@GetMapping("/admin/cidades/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("admin/cidades/lista");
		mv.addObject("listaCidades", cidadeRepository.findAll());

		return mv;
	}

	@GetMapping("/admin/cidades/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Cidade> cidade = cidadeRepository.findById(id);
		return cadastrar(cidade.get());
	}

	@GetMapping("/admin/cidades/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Cidade> cidade = cidadeRepository.findById(id);
		cidadeRepository.delete(cidade.get());

		return listar();
	}

	@PostMapping("/admin/cidades/salvar")
	public ModelAndView salvar(@Valid Cidade cidade, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return cadastrar(cidade);
		}

		cidadeRepository.saveAndFlush(cidade);

		return cadastrar(new Cidade());
	}

}
