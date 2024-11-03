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

import com.dev.ProtoFin.models.Papel;
import com.dev.ProtoFin.repository.PapelRepository;

@Controller
public class PapelController {

	@Autowired
	private PapelRepository papelRepository;

	@GetMapping("/admin/papeis/cadastrar")
	public ModelAndView cadastrar(Papel papel) {
		ModelAndView mv = new ModelAndView("admin/papeis/cadastro");
		mv.addObject("papel", papel);
		return mv;
	}

	@GetMapping("/admin/papeis/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("admin/papeis/lista");
		mv.addObject("listaPapeis", papelRepository.findAll());

		return mv;
	}

	@GetMapping("/admin/papeis/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Papel> papel = papelRepository.findById(id);
		return cadastrar(papel.get());
	}

	@GetMapping("/admin/papeis/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Papel> papel = papelRepository.findById(id);
		papelRepository.delete(papel.get());

		return listar();
	}

	@PostMapping("/admin/papeis/salvar")
	public ModelAndView salvar(@Valid Papel papel, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return cadastrar(papel);
		}
		papelRepository.saveAndFlush(papel);

		return cadastrar(new Papel());
	}

}
