package uz.muzaffar.codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.muzaffar.codingbat.entity.ApiResponse;
import uz.muzaffar.codingbat.entity.Help;
import uz.muzaffar.codingbat.entity.Task;
import uz.muzaffar.codingbat.payload.HelpDto;
import uz.muzaffar.codingbat.payload.TaskDto;
import uz.muzaffar.codingbat.service.HelpService;
import uz.muzaffar.codingbat.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping
    public List<Task> getAll() {
        return taskService.getAllTask();
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        ApiResponse taskById = taskService.getTaskById(id);
        return ResponseEntity.status(taskById.isSuccess() ? 201 : 404).body(taskById);
    }

    @PostMapping()
    public HttpEntity<?> add(@RequestBody TaskDto taskDto) {
        ApiResponse apiResponse = taskService.addTask(taskDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = taskService.deleteTask(id);
        if (apiResponse.isSuccess())
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@RequestBody TaskDto taskDto, @PathVariable Integer id) {
        ApiResponse apiResponse = taskService.editTask(id, taskDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
