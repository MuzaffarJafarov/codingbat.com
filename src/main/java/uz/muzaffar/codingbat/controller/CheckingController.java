package uz.muzaffar.codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.muzaffar.codingbat.entity.ApiResponse;
import uz.muzaffar.codingbat.entity.Checking;
import uz.muzaffar.codingbat.entity.Task;
import uz.muzaffar.codingbat.payload.CheckingDto;
import uz.muzaffar.codingbat.payload.TaskDto;
import uz.muzaffar.codingbat.service.CheckingService;
import uz.muzaffar.codingbat.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/checking")
public class CheckingController {
    @Autowired
    CheckingService checkingService;

    @GetMapping
    public List<Checking> getAll() {
        return checkingService.getAllChecking();
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        ApiResponse checking = checkingService.getCheckingById(id);
        return ResponseEntity.status(checking.isSuccess() ? 201 : 404).body(checking);
    }

    @PostMapping()
    public HttpEntity<?> add(@RequestBody CheckingDto checkingDto) {
        ApiResponse apiResponse = checkingService.addChecking(checkingDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = checkingService.deleteChecking(id);
        if (apiResponse.isSuccess())
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@RequestBody CheckingDto checkingDto, @PathVariable Integer id) {
        ApiResponse apiResponse = checkingService.editChecking(id, checkingDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
