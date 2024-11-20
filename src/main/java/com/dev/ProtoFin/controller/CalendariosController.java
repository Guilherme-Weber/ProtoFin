package com.dev.ProtoFin.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CalendariosController {

	private static String caminhoPdf = "C:\\Users\\guilw\\eclipse-workspace-new\\ProtoFin\\src\\main\\resources\\static\\images\\pdf\\";

	@GetMapping("/admin/calendarios/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView mv = new ModelAndView("admin/calendarios/cadastro");

		return mv;
	}

	@GetMapping("/admin/calendarios/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("admin/calendarios/lista");

		return mv;
	}

	@PostMapping("/admin/salvarPdf")
	public ModelAndView salvarpdf(@RequestParam("file") MultipartFile arquivo) {

		try {
			if (!arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths.get(caminhoPdf + String.valueOf(arquivo.getOriginalFilename()));
				Files.write(caminho, bytes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cadastrar();
	}

}
