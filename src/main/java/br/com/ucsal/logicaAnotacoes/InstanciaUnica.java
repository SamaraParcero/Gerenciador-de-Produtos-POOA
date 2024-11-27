package br.com.ucsal.logicaAnotacoes;


import br.com.ucsal.anotacoes.Singleton;

public class InstanciaUnica {
	/**
	 * Método responsável por carregar uma instância única (Singleton) de uma
	 * classe. Verifica se a classe está anotada com @Singleton e utiliza reflexão
	 * para acessar o método estático getInstancia.
	 */
    @SuppressWarnings("unchecked")
    public static <T> T carregarSingleton(Class<T> clazz) {
        if (clazz.isAnnotationPresent(Singleton.class)) {
            try {
            	// Invoca o método estático getInstancia para obter o Singleton
                return (T) clazz.getMethod("getInstancia").invoke(null);
            } catch (Exception e) {
            	// Lança exceção em caso de erro durante a invocação
                throw new RuntimeException("Erro ao carregar Singleton", e);
            }
        }
     // Lança exceção caso a classe não esteja anotada com @Singleton
        throw new IllegalArgumentException("A classe não está anotada com @Singleton");
    }

}
