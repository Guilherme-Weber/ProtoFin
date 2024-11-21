package com.dev.ProtoFin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PdfController {

	private static String caminhoPdf = "C:\\Users\\guilw\\eclipse-workspace-new\\ProtoFin\\src\\main\\resources\\static\\images\\pdf";

	// todo: colocar limite de apenas imagens, e colocar um
	// tamanho limite tbm
	@GetMapping("/mostrarPdf/{pdf}")
	@ResponseBody
	public URI retornarPdf(@PathVariable("pdf") String pdf) throws IOException {

		File PdfArquivo = new File(caminhoPdf + pdf);

		System.out.println(pdf);

		if (pdf != null || pdf.trim().length() > 0) {
			return PdfArquivo.toURI();
		}
		return null;
	}


	@GetMapping("/file")
	public ResponseEntity<InputStreamResource> getFile() throws IOException {
		try (FileInputStream fileInputStream = new FileInputStream(caminhoPdf)) {
			InputStreamResource inputStreamResource = new InputStreamResource(fileInputStream);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentLength(Files.size(Paths.get(caminhoPdf)));// optional
			return new ResponseEntity(inputStreamResource, headers, HttpStatus.OK);
		}
	}
}
