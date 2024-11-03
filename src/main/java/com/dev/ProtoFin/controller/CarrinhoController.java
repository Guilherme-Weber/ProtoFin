package com.dev.ProtoFin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.dev.ProtoFin.models.Compra;
import com.dev.ProtoFin.models.ItensCompra;
import com.dev.ProtoFin.models.Produto;
import com.dev.ProtoFin.repository.ProdutoRepository;

@Controller
public class CarrinhoController {

	private List<ItensCompra> itensCompra = new ArrayList<ItensCompra>();

	private Compra compra = new Compra();

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping("/carrinho")
	public ModelAndView chamarCarrinho() {
		ModelAndView mv = new ModelAndView("/client/carrinho");

		calcularTotal();
		//System.out.println(compra.getValorTotal());

		mv.addObject("compra", compra);
		mv.addObject("listaItens", itensCompra);
		return mv;
	}
	
	@GetMapping("/finalizar")
	public ModelAndView finalizarCompra() {
		ModelAndView mv = new ModelAndView("/client/finalizar");

		calcularTotal();

		mv.addObject("compra", compra);
		mv.addObject("listaItens", itensCompra);
		return mv;
	}

	private void calcularTotal() {
		compra.setValorTotal(0.);
		for (ItensCompra it : itensCompra) {
			compra.setValorTotal(compra.getValorTotal() + it.getValorTotal());
		}
	}

	@GetMapping("/removerProduto/{id}")
	public String removerProdutoCarrinho(@PathVariable Long id) {

		for (ItensCompra it : itensCompra) {
			if (it.getProduto().getId().equals(id)) { // madness

				itensCompra.remove(it);
				break;
			}
		}

		return "redirect:/carrinho";
	}

	@GetMapping("/alterarQuantidade/{id}/{acao}")
	public String alterarCarrinho(@PathVariable Long id, @PathVariable Integer acao) {

		for (ItensCompra it : itensCompra) {
			if (it.getProduto().getId().equals(id)) { // madness

				if (acao.equals(1)) {
					it.setQuantidade(it.getQuantidade() + 1);
					it.setValorTotal(it.getQuantidade() * it.getValorUnitario());
				} else if (acao == 0) {
					it.setQuantidade(it.getQuantidade() - 1);
					it.setValorTotal(it.getQuantidade() * it.getValorUnitario());
					if (it.getQuantidade() == 0 || it.getQuantidade() < 0) {
						itensCompra.remove(it);
					}
				}
				break;
			}
		}

		return "redirect:/carrinho";
	}

	@GetMapping("/adicionarCarrinho/{id}")
	public String adicionarCarrinho(@PathVariable Long id) {
//		System.out.println("ID do Produto " + id);

		Optional<Produto> prod = produtoRepository.findById(id);
		Produto produto = prod.get();

		int controle = 0;

		for (ItensCompra it : itensCompra) {

			if (it.getProduto().getId().equals(produto.getId())) { // madness
				it.setQuantidade(it.getQuantidade() + 1);
				it.setValorTotal(it.getQuantidade() * it.getValorUnitario());
				controle = 1;
				break;
			}
		}
		if (controle == 0) {
			ItensCompra item = new ItensCompra();
			item.setProduto(produto);
			item.setValorUnitario(produto.getValorVenda());
			item.setQuantidade(item.getQuantidade() + 1);
			item.setValorTotal(item.getQuantidade() * item.getValorUnitario());

			itensCompra.add(item);
		}

		return "redirect:/carrinho";
	}
}
