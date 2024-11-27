package br.com.ucsal.controller;

import java.io.IOException;

import br.com.ucsal.anotacoes.Inject;
import br.com.ucsal.anotacoes.Rota;
import br.com.ucsal.logicaAnotacoes.Injector;
import br.com.ucsal.persistencia.HSQLProdutoRepository;
import br.com.ucsal.service.ProdutoService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet para adicionar produtos. Processa requisições HTTP GET e POST para
 * exibir o formulário e salvar o produto.
 */
@Rota(value = "/adicionarProdutos")
public class ProdutoAdicionarServlet implements Command {
	private static final long serialVersionUID = 1L;

	// Dependência injetada para manipulação de produtos
	@Inject
	private ProdutoService produtoService;

	
     //Construtor que executa a injeção de dependências.  
	public ProdutoAdicionarServlet() {
		Injector.injectDependencies(this);
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getMethod();

		// Redireciona para o método adequado com base no verbo HTTP
		if ("GET".equalsIgnoreCase(method)) {
			doGet(request, response);
		} else if ("POST".equalsIgnoreCase(method)) {
			doPost(request, response);
		}
	}

	  //Exibe o formulário para adicionar um produto.
	private void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/produtoformulario.jsp");
		dispatcher.forward(request, response);
	}

	 
     //Processa a submissão do formulário de adição de produto.  
	private void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		double preco = Double.parseDouble(request.getParameter("preco"));
		// Salva o novo produto usando o serviço
		produtoService.adicionarProduto(nome, preco);
		// Redireciona para a lista de produtos após a adição
		response.sendRedirect("listarProdutos");
	}

}
