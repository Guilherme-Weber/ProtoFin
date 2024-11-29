package com.dev.ProtoFin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrimaryController {

	@GetMapping("/admin")
	public String acessarPrincipal() {
		return "admin/home";
	}

	@GetMapping("/prof")
	public String acessarProfessor() {

		return "admin/homeTeacher";
	}

//	@GetMapping("/administrativo")
//	public String acessarPrincipalYes() {
//		return "login";
//	}
//
//	@PostMapping("/admin")
//	public String acessarPrincipalPost() {
//		return "admin/home";
//	}
}
