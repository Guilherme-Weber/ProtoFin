package com.dev.ProtoFin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.ProtoFin.models.EntradaItens;
import com.dev.ProtoFin.models.EntradaProduto;
import com.dev.ProtoFin.models.Produto;
import com.dev.ProtoFin.repository.EntradaItensRepository;
import com.dev.ProtoFin.repository.EntradaProdutoRepository;
import com.dev.ProtoFin.repository.FuncionarioRepository;
import com.dev.ProtoFin.repository.ProdutoRepository;

@Controller
public class EntradaProdutoControle {

	private List<EntradaItens> listaEntrada = new ArrayList<>();

	@Autowired
	private EntradaProdutoRepository entradaProdutoRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private EntradaItensRepository entradaItensRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping("/admin/entrada/cadastrar")
	public ModelAndView cadastrar(EntradaProduto entrada, EntradaItens entradaItens) {
		ModelAndView mv = new ModelAndView("admin/entrada/cadastro");
		mv.addObject("entrada", entrada);
		mv.addObject("listaEntradaItens", this.listaEntrada);
		mv.addObject("entradaItens", entradaItens);
		mv.addObject("listaFuncioarios", funcionarioRepository.findAll());
		mv.addObject("listaProdutos", produtoRepository.findAll());
		return mv;
	}

//	@GetMapping("/admin/estados/listar")
//	public ModelAndView listar() {
//		ModelAndView mv = new ModelAndView("admin/estados/lista");
//		mv.addObject("listaEstados", estadoRepository.findAll());
//
//		return mv;
//	}
//
//	@GetMapping("/admin/estados/editar/{id}")
//	public ModelAndView editar(@PathVariable("id") Long id) {
//		Optional<Estado> estado = estadoRepository.findById(id);
//		return cadastrar(estado.get());
//	}
//
//	@GetMapping("/admin/estados/remover/{id}")
//	public ModelAndView remover(@PathVariable("id") Long id) {
//		Optional<Estado> estado = estadoRepository.findById(id);
//		estadoRepository.delete(estado.get());
//
//		return listar();
//	}

	@PostMapping("/admin/entrada/salvar")
	public ModelAndView salvar(String acao, EntradaProduto entrada, EntradaItens entradaItens) {

		if (acao.equals("itens")) {
			this.listaEntrada.add(entradaItens);
		} else if (acao.equals("salvar")) {
			entradaProdutoRepository.saveAndFlush(entrada);
			for (EntradaItens it : listaEntrada) {
				it.setEntrada(entrada);
				entradaItensRepository.saveAndFlush(it);
				Optional<Produto> prod = produtoRepository.findById(it.getProduto().getId());
				Produto produto = prod.get();
				produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + it.getQuantidade());
				produto.setValorVenda(it.getValorVenda());
				produtoRepository.saveAndFlush(produto);
				this.listaEntrada = new ArrayList<>();
			}
			return cadastrar(new EntradaProduto(), new EntradaItens());
		}

		System.out.println(this.listaEntrada.size());

		return cadastrar(entrada, new EntradaItens());
	}

}
