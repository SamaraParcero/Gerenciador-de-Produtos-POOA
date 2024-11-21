package br.com.ucsal.util;
import br.com.ucsal.persistencia.PersistenciaFactory;
import java.lang.reflect.Field;
public class DependencyInjector {
    private static final int REPOSITORY_TYPE = PersistenciaFactory.HSQL; // Alternar para MEMORIA conforme necessário.

    public static void injectDependencies(Object target) {
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                try {
                    Object dependency = PersistenciaFactory.getProdutoRepository(REPOSITORY_TYPE);
                    field.set(target, dependency);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Erro ao injetar dependência no campo: " + field.getName(), e);
                }
            }
        }
    }
}
