package br.com.ucsal.anotacoes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Define a anotação @Rota para mapear rotas em classes.
 * 
 * A anotação aceita um valor que representa o caminho da rota.
 */

@Target(ElementType.TYPE) // Pode ser usada em classes.
@Retention(RetentionPolicy.RUNTIME) // Disponível em tempo de execução.
public @interface Rota {
	String value(); // Caminho da rota associado à classe.
}
