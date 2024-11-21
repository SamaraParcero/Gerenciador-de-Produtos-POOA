package br.com.ucsal.controller;

import java.io.IOException;

import br.com.ucsal.anotacoes.Inject;
import br.com.ucsal.anotacoes.Rota;
import br.com.ucsal.logicaAnotacoes.Injector;
import br.com.ucsal.service.ProdutoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Rota(value = "/excluirProdutos")
public class ProdutoExcluirServlet implements Command {
	private static final long serialVersionUID = 1L;
	
    @Inject
	private ProdutoService produtoService;

	
	public ProdutoExcluirServlet() {
		Injector.injectDependencies(this);
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Lógica de exclusão
		Integer id = Integer.parseInt(request.getParameter("id"));
		produtoService.removerProduto(id);
		response.sendRedirect("listarProdutos");
	}

}
