package auca.ac.rw.Assignment2.controller.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.rw.Assignment2.model.task.Task;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    public TaskController() {
        tasks.add(new Task(1L, "Finish assignment", "Complete REST API assignment", true, "HIGH", "2026-02-12"));
        tasks.add(new Task(2L, "Buy groceries", "Milk, bread, eggs", true, "MEDIUM", "2026-02-15"));
        tasks.add(new Task(3L, "sport", "Workout session", true, "LOW", "2026-02-10"));
    }

    // GET /api/tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }

    // GET /api/tasks/{taskId}
    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable Long taskId) {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                return task;
            }
        }
        return null;
    }

    // GET /api/tasks/status?completed=true/false
    @GetMapping("/status")
    public List<Task> getByStatus(@RequestParam boolean completed) {
        List<Task> result = new ArrayList<>();

        for (Task task : tasks) {
            if (task.isCompleted() == completed) {
                result.add(task);
            }
        }

        return result;
    }

    // GET /api/tasks/priority/{priority}
    @GetMapping("/priority/{priority}")
    public List<Task> getByPriority(@PathVariable String priority) {
        List<Task> result = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getPriority().equalsIgnoreCase(priority)) {
                result.add(task);
            }
        }

        return result;
    }

    // POST /api/tasks
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        tasks.add(task);
        return task;
    }

    // PUT /api/tasks/{taskId}
    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable Long taskId,
                           @RequestBody Task updatedTask) {

        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                task.setTitle(updatedTask.getTitle());
                task.setDescription(updatedTask.getDescription());
                task.setCompleted(updatedTask.isCompleted());
                task.setPriority(updatedTask.getPriority());
                task.setDueDate(updatedTask.getDueDate());
                return task;
            }
        }

        return null;
    }

    // PATCH /api/tasks/{taskId}/complete
    @PatchMapping("/{taskId}/complete")
    public Task markCompleted(@PathVariable Long taskId) {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                task.setCompleted(true);
                return task;
            }
        }

        return null;
    }

    // DELETE /api/tasks/{taskId}
    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable Long taskId) {
        tasks.removeIf(task -> task.getTaskId().equals(taskId));
        return "Task deleted";
    }
}

