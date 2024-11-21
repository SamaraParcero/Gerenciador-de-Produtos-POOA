package br.com.ucsal.controller;

import java.io.IOException;

import br.com.ucsal.persistencia.HSQLProdutoRepository;
import br.com.ucsal.service.ProdutoService;
import br.com.ucsal.util.DependencyInjector;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProdutoExcluirServlet implements Command {
	private static final long serialVersionUID = 1L;
	private ProdutoService produtoService;

	public ProdutoExcluirServlet() {
		ProdutoService service = new ProdutoService();
		DependencyInjector.injectDependencies(service);
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Lógica de exclusão
		Integer id = Integer.parseInt(request.getParameter("id"));
		produtoService.removerProduto(id);
		response.sendRedirect("listarProdutos");
	}

}
