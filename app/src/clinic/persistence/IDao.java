package clinic.persistence;

import java.util.List;

public interface IDao<T> {

    void crearTablas();
    T guardar(T t);
    List<T> listarTodos();
}
