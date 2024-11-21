package br.com.ucsal.service;

import java.util.List;

import br.com.ucsal.model.Produto;
import br.com.ucsal.persistencia.ProdutoRepository;
import br.com.ucsal.util.DependencyInjector;
import br.com.ucsal.util.Inject;

public class ProdutoService {

 @Inject
 private ProdutoRepository<Produto, Integer> produtoRepository;

    public ProdutoService() {
        // Injetar dependências
        DependencyInjector.injectDependencies(this);
    }

 public void adicionarProduto(String nome, double preco) {
     Produto produto = new Produto(null, nome, preco);
     produtoRepository.adicionar(produto);
 }

 public void removerProduto(Integer id) {
     produtoRepository.remover(id);
 }

 public Produto obterProdutoPorId(Integer id) {
     return produtoRepository.obterPorID(id);
 }
 
 public void atualizarProduto(Produto p) {
	produtoRepository.atualizar(p);
}

 public List<Produto> listarProdutos() {
     return produtoRepository.listar();
 }
}

