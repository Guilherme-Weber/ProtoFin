package com.dev.ProtoFin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrimaryController {

	@GetMapping("/admin")
	public String acessarPrincipal() {
		return "admin/home";
	}

}
