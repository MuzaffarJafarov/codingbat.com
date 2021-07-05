package uz.muzaffar.codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.muzaffar.codingbat.entity.ApiResponse;
import uz.muzaffar.codingbat.entity.Checking;
import uz.muzaffar.codingbat.entity.Solution;
import uz.muzaffar.codingbat.payload.CheckingDto;
import uz.muzaffar.codingbat.payload.SolutionDto;
import uz.muzaffar.codingbat.service.CheckingService;
import uz.muzaffar.codingbat.service.SolutionService;

import java.util.List;

@RestController
@RequestMapping("/solution")
public class SolutionController {
    @Autowired
    SolutionService solutionService;

    @GetMapping
    public List<Solution> getAll() {
        return solutionService.getAllSolution();
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        ApiResponse solution = solutionService.getSolutionById(id);
        return ResponseEntity.status(solution.isSuccess() ? 201 : 404).body(solution);
    }

    @PostMapping()
    public HttpEntity<?> add(@RequestBody SolutionDto solutionDto) {
        ApiResponse apiResponse = solutionService.addSolution(solutionDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = solutionService.deleteSolution(id);
        if (apiResponse.isSuccess())
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@RequestBody SolutionDto solutionDto, @PathVariable Integer id) {
        ApiResponse apiResponse = solutionService.editSolution(id, solutionDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
