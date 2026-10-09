package com.EjercicioAyudantia.ISoft.service;

import com.EjercicioAyudantia.ISoft.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {
    protected final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    protected final AtomicLong idGenerator = new AtomicLong(1);
    private Long taskId= 1l;


    public Task createTask(Task task) {
        task.setId(idGenerator.getAndIncrement());
        task.setCompletada(false);
        tasks.put(task.getId(), task);
        return task;
    }

    public List<Task> getTasks(
            String prioridad,
            String titulo,
            String fechaLimite) {

        List<Task> resultado = new ArrayList<>();
        
        for (Task task : tasks.values()) {

            if (prioridad != null
                    && !prioridad.equalsIgnoreCase(task.getPrioridad())) {
                continue;
            }

            if (titulo != null
                    && (task.getTitulo() == null
                    || !task.getTitulo().toLowerCase()
                    .contains(titulo.toLowerCase()))) {
                continue;
            }

            if (fechaLimite != null
                    && !fechaLimite.equals(task.getFechaLimite())) {
                continue;
            }

            resultado.add(task);
        }

        return resultado;
    }

    public Task updateTask(Task task) {


        if (task.getId() == taskId) {
            task.setCompletada(true);
        }
        return task;
    }
}
