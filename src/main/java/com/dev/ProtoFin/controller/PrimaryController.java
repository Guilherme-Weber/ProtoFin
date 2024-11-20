package com.dev.ProtoFin.controller;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.dev.ProtoFin.repository.FuncionarioRepository;

@Controller
public class PrimaryController {
	

	@GetMapping("/admin")
	public String acessarPrincipal() {
		return "admin/home";
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
