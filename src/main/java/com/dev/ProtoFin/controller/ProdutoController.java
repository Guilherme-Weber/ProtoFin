package com.dev.ProtoFin.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dev.ProtoFin.models.Produto;
import com.dev.ProtoFin.repository.ProdutoRepository;

@Controller
public class ProdutoController {

	private static String caminhoImages = "C:\\Users\\guilw\\eclipse-workspace-new\\ProtoFin\\Imagens\\";

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping("/admin/produtos/cadastrar")
	public ModelAndView cadastrar(Produto produto) {
		ModelAndView mv = new ModelAndView("admin/produtos/cadastro");
		mv.addObject("produto", produto);

		return mv;
	}

	@GetMapping("/admin/produtos/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		return cadastrar(produto.get());
	}

	@GetMapping("/admin/produtos/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("admin/produtos/lista");
		mv.addObject("listaProdutos", produtoRepository.findAll());

		return mv;
	}

	@GetMapping("/admin/produtos/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		produtoRepository.delete(produto.get());

		return listar();
	}

	// todo: colocar limite de apenas imagens, e colocar um
	// tamanho limite tbm
	@GetMapping("/admin/produtos/mostrarImagem/{imagem}")
	@ResponseBody
	public byte[] retornarImage(@PathVariable("imagem") String imagem) throws IOException {

		File ImagemArquivo = new File(caminhoImages + imagem);

		if (imagem != null || imagem.trim().length() > 0) {
			return Files.readAllBytes(ImagemArquivo.toPath());
		}
		return null;
	}

	@PostMapping("/admin/produtos/salvar")
	public ModelAndView salvar(@Valid Produto produto, BindingResult result,
			@RequestParam("file") MultipartFile arquivo) {

		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return cadastrar(produto);
		}

		produtoRepository.saveAndFlush(produto);

		try {
			if (!arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths
						.get(caminhoImages + String.valueOf(produto.getId()) + arquivo.getOriginalFilename());
				Files.write(caminho, bytes);

				produto.setNomeImagem(String.valueOf(produto.getId()) + arquivo.getOriginalFilename());
				produtoRepository.saveAndFlush(produto);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cadastrar(new Produto());
	}
}
