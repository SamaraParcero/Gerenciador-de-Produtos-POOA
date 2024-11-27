package br.com.ucsal.persistencia;

import br.com.ucsal.logicaAnotacoes.InstanciaUnica;

/**
 * Fábrica para criação de instâncias de repositórios de produtos.
 * Permite alternar entre diferentes implementações de persistência.
 */
public class PersistenciaFactory {

    public static final int MEMORIA = 0;
    public static final int HSQL = 1;

 // Retorna a implementação de repositório com base no tipo especificado. 
    public static ProdutoRepository<?, ?> getProdutoRepository(int type) {
        switch (type) {
            case MEMORIA:
                return InstanciaUnica.carregarSingleton(MemoriaProdutoRepository.class);
            case HSQL:
                return new HSQLProdutoRepository();
            default:
                throw new IllegalArgumentException("Unexpected value: " + type);
        }
    }
}
