package br.com.ucsal.service;

import java.util.List;
import br.com.ucsal.model.Produto;
import br.com.ucsal.persistencia.ProdutoRepository;

public class ProdutoService {

	private final ProdutoRepository<Produto, Integer> produtoRepository;

	// Construtor para inicializar o ProdutoService com um repositório específico
	public ProdutoService(ProdutoRepository<Produto, Integer> repository) {
		this.produtoRepository = repository;
	}
	

	// Construtor sem parâmetros, útil para inicializações genéricas
	public ProdutoService() {
		this.produtoRepository = null;
	}

	// Adiciona um novo produto ao repositório
	public void adicionarProduto(String nome, double preco) {
		Produto produto = new Produto(null, nome, preco);
		produtoRepository.adicionar(produto);
	}

	// Remove um produto do repositório pelo ID
	public void removerProduto(Integer id) {
		produtoRepository.remover(id);
	}

	// Obtém um produto específico pelo ID
	public Produto obterProdutoPorId(Integer id) {
		return produtoRepository.obterPorID(id);
	}

	// Atualiza as informações de um produto no repositório
	public void atualizarProduto(Produto produto) {
		produtoRepository.atualizar(produto);
	}

	// Lista todos os produtos disponíveis no repositório
	public List<Produto> listarProdutos() {
		return produtoRepository.listar();
	}
}
