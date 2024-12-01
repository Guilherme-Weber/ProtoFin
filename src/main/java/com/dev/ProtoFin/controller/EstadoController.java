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

import com.dev.ProtoFin.models.Estado;
import com.dev.ProtoFin.repository.EstadoRepository;

@Controller
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@GetMapping("/admin/estados/cadastrar")
	public ModelAndView cadastrar(Estado estado) {
		ModelAndView mv = new ModelAndView("admin/estados/cadastro");
		mv.addObject("estado", estado);
		return mv;
	}

	@GetMapping("/admin/estados/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Estado> estado = estadoRepository.findById(id);
		return cadastrar(estado.get());
	}

	@GetMapping("/admin/estados/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("admin/estados/lista");
		mv.addObject("listaEstados", estadoRepository.findAll());

		return mv;
	}

	@GetMapping("/admin/estados/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Estado> estado = estadoRepository.findById(id);
		estadoRepository.delete(estado.get());

		return listar();
	}

	@PostMapping("/admin/estados/salvar")
	public ModelAndView salvar(@Valid Estado estado, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return cadastrar(estado);
		}
		estadoRepository.saveAndFlush(estado);

		return cadastrar(new Estado());
	}

}
