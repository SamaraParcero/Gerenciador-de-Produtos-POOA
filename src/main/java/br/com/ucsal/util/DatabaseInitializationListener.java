package br.com.ucsal.util;

import br.com.ucsal.persistencia.PersistenciaFactory;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.logging.Logger;

@WebListener
public class DatabaseInitializationListener implements ServletContextListener {


    private static final Logger logger = Logger.getLogger(DatabaseInitializationListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Iniciando o banco de dados HSQLDB...");
        try {
            DatabaseUtil.iniciarBanco();
            logger.info("Banco de dados inicializado com sucesso.");

            // Configuração dinâmica do repositório
            String repoTypeParam = sce.getServletContext().getInitParameter("repositoryType");
            int repositoryType = "MEMORIA".equalsIgnoreCase(repoTypeParam) ? PersistenciaFactory.MEMORIA : PersistenciaFactory.HSQL;
            sce.getServletContext().setAttribute("repositoryType", repositoryType);

            logger.info("Repositório configurado para: " +
                    (repositoryType == PersistenciaFactory.HSQL ? "HSQLDB" : "Memória"));

        } catch (Exception e) {
            logger.severe("Erro ao inicializar o banco de dados: " + e.getMessage());
            throw new RuntimeException("Falha na inicialização do banco de dados.", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Aplicação sendo finalizada.");
    }
}