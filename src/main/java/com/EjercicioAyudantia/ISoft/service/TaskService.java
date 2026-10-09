package com.EjercicioAyudantia.ISoft.service;

import com.EjercicioAyudantia.ISoft.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import java.util.Optional;

@Service
public class TaskService {
    protected final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    protected final AtomicLong idGenerator = new AtomicLong(1);

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

    public Optional<Task> completeTask(Long id) {
        Task task = tasks.get(id);
        if (task != null) {
            task.setCompletada(true);
            return Optional.of(task);
        }
        return Optional.empty(); 
    }
}
