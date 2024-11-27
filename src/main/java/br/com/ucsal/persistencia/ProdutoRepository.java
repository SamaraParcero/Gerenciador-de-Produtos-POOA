package br.com.ucsal.persistencia;

import java.util.List;

import br.com.ucsal.model.Produto;

/**
 * Interface genérica para repositórios.
 * Define os métodos básicos para manipulação de entidades.
 */
public interface ProdutoRepository<T,I> {
	
    void adicionar(T entidade);
    
    void remover(I id);
    
    void atualizar(T entidade);
    
    List<T> listar();
    
    Produto obterPorID(I id);

}
