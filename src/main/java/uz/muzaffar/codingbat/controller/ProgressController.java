package uz.muzaffar.codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.muzaffar.codingbat.entity.ApiResponse;
import uz.muzaffar.codingbat.entity.Progress;
import uz.muzaffar.codingbat.payload.HelpDto;
import uz.muzaffar.codingbat.payload.ProgressDto;
import uz.muzaffar.codingbat.service.ProgressService;

import java.util.List;

@RestController
@RequestMapping("/progress")
public class ProgressController {
    @Autowired
    ProgressService progressService;

    @GetMapping
    public List<Progress> getAll() {
        return progressService.getAllProgress();
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        ApiResponse progressById = progressService.getProgressById(id);
        return ResponseEntity.status(progressById.isSuccess() ? 201 : 404).body(progressById);
    }

    @PostMapping()
    public HttpEntity<?> add(@RequestBody ProgressDto progressDto) {
        ApiResponse apiResponse = progressService.addProgress(progressDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = progressService.deleteProgress(id);
        if (apiResponse.isSuccess())
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@RequestBody ProgressDto progressDto, @PathVariable Integer id) {
        ApiResponse apiResponse = progressService.editProgress(id, progressDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
