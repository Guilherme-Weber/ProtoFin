package com.dev.ProtoFin.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImagemController {

	private static String caminhoImages = "C:\\Users\\guilw\\eclipse-workspace-new\\ProtoFin\\Imagens\\";

	// todo: colocar limite de apenas imagens, e colocar um
	// tamanho limite tbm
	@GetMapping("/mostrarImagem/{imagem}")
	@ResponseBody
	public byte[] retornarImage(@PathVariable("imagem") String imagem) throws IOException {

		File ImagemArquivo = new File(caminhoImages + imagem);

		if (imagem != null || imagem.trim().length() > 0) {
			return Files.readAllBytes(ImagemArquivo.toPath());
		}
		return null;
	}
}
