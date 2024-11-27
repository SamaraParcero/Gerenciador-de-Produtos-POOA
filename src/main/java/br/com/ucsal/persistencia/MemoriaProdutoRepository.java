package br.com.ucsal.persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import br.com.ucsal.anotacoes.Singleton;
import br.com.ucsal.model.Produto;

/**
 * Implementação de ProdutoRepository usando memória para armazenamento.
 * Adequada para testes ou quando persistência não é necessária.
 */
@Singleton
public class MemoriaProdutoRepository implements ProdutoRepository<Produto, Integer> {

	private Map<Integer, Produto> produtos = new HashMap<>();
	private AtomicInteger currentId = new AtomicInteger(1);

	private static MemoriaProdutoRepository instancia;
	private static boolean instanciaUnica = false;

	private MemoriaProdutoRepository() {
	}

	// Obtém a instância Singleton do repositório em memória.
	   public static synchronized MemoriaProdutoRepository getInstancia() {
	        if (instancia == null) {
	            instancia = new MemoriaProdutoRepository();
	            System.out.println("Singleton: Criando nova instância de MemoriaProdutoRepository.");
	            instanciaUnica = true; // Marca que a instância foi criada
	        } else if (!instanciaUnica) {
	            System.out.println("Singleton: Retornando instância existente de MemoriaProdutoRepository.");
	            instanciaUnica = true; // Marca que a mensagem já foi exibida
	        }
	        return instancia;
	    }
	    


	// Adiciona um novo produto ao armazenamento em memória.
	@Override
	public void adicionar(Produto entidade) {
		int id = currentId.getAndIncrement();
		entidade.setId(id);
		produtos.put(entidade.getId(), entidade);
	}

	// Atualiza um produto existente no armazenamento.
	@Override
	public void atualizar(Produto entidade) {
		produtos.put(entidade.getId(), entidade);
	}

	// Remove um produto do armazenamento com base no ID.
	@Override
	public void remover(Integer id) {
		produtos.remove(id);
	}

	// Lista todos os produtos armazenados.
	@Override
	public List<Produto> listar() {
		return new ArrayList<>(produtos.values());
	}

	// Obtém um produto pelo ID do armazenamento.
	@Override
	public Produto obterPorID(Integer id) {
		return produtos.get(id);
	}

}
