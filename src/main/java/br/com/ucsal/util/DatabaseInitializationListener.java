package br.com.ucsal.util;

import br.com.ucsal.logicaAnotacoes.InstanciaUnica;
import br.com.ucsal.persistencia.MemoriaProdutoRepository;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class DatabaseInitializationListener implements ServletContextListener {

	// Método executado ao iniciar o contexto da aplicação
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Iniciando o banco de dados HSQLDB...");
        DatabaseUtil.iniciarBanco();
        
        
        /* PARA TESTE DE INSTÂNCIA USE:
        MemoriaProdutoRepository repo1 = InstanciaUnica.carregarSingleton(MemoriaProdutoRepository.class);
        MemoriaProdutoRepository repo2 = InstanciaUnica.carregarSingleton(MemoriaProdutoRepository.class);

        System.out.println(repo1 == repo2);
        */  

    }

 // Método executado ao finalizar o contexto da aplicação
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Aplicação sendo finalizada.");
    }
}