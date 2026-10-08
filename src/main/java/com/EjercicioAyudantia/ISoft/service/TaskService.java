package com.EjercicioAyudantia.ISoft;

import com.example.todo.model.Task;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {
    protected final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    protected final AtomicLong idGenerator = new AtomicLong(1);
}