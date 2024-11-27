package br.com.ucsal.anotacoes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Define a anotação @Inject para injeção de dependências.
 * 
 * Aplicável a classes e campos. A anotação é mantida em tempo de execução.
 */

@Target({ElementType.TYPE, ElementType.FIELD}) // Alvo: classes e campos.
@Retention(RetentionPolicy.RUNTIME) // Disponível em tempo de execução.
public @interface Inject {
}
