package br.com.ucsal.anotacoes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Define a anotação @Singleton para indicar que uma classe deve seguir o padrão Singleton.
 * 
 * Classes marcadas com esta anotação terão apenas uma instância única gerenciada.
 */

@Retention(RetentionPolicy.RUNTIME) // Disponível em tempo de execução.
public @interface Singleton {

}
