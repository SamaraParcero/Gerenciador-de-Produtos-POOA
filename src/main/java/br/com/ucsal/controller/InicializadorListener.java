package br.com.ucsal.controller;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.reflections.Reflections;
import br.com.ucsal.anotacoes.Rota;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//Listener responsável por inicializar e registrar rotas associadas a comandos.
@WebListener
public class InicializadorListener implements ServletContextListener {

	// Mapa para armazenar as associações de rotas a comandos
    private Map<String, Command> commands = new HashMap<>();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Inicializando rotas...");

        try {
        	// Reflections é usado para encontrar classes anotadas dinamicamente
            Reflections reflections = new Reflections("br.com.ucsal.controller");
            Set<Class<?>> classesAnotadas = reflections.getTypesAnnotatedWith(Rota.class);
            
            // Verifica se foram encontradas classes anotadas
            if (classesAnotadas.isEmpty()) {
                System.out.println("Nenhuma classe anotada com @Rota foi encontrada.");
            }
            
            // Processa cada classe anotada encontrada
            for (Class<?> classe : classesAnotadas) {
                System.out.println("Classe anotada encontrada: " + classe.getName());
                Rota rota = classe.getAnnotation(Rota.class);
                
                // Valida se a classe implementa a interface Command
                if (!Command.class.isAssignableFrom(classe)) {
                    throw new IllegalArgumentException("A classe " + classe.getName() + " não implementa Command.");
                }
                
                // Cria uma instância do comando e registra no mapa de rotas
                Command commandInstance = (Command) classe.getDeclaredConstructor().newInstance();
                commands.put(rota.value(), commandInstance);
                System.out.println("Rota registrada: " + rota.value() + " -> " + classe.getName());
            }

            // Armazena o mapa de comandos no contexto da aplicação
            sce.getServletContext().setAttribute("commands", commands);
            System.out.println("Rotas registradas com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inicializar rotas: " + e.getMessage(), e);
        }
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    	 // Limpa o mapa de comandos ao encerrar a aplicação
        commands.clear();
        System.out.println("Aplicação encerrada. Rotas limpas.");
    }
}
