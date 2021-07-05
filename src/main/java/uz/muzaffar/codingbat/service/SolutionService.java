package uz.muzaffar.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.muzaffar.codingbat.entity.*;
import uz.muzaffar.codingbat.payload.HelpDto;
import uz.muzaffar.codingbat.payload.SolutionDto;
import uz.muzaffar.codingbat.repository.HelpRepository;
import uz.muzaffar.codingbat.repository.LanguageRepository;
import uz.muzaffar.codingbat.repository.SolutionRepository;
import uz.muzaffar.codingbat.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SolutionService {
    @Autowired
    SolutionRepository solutionRepository;
    @Autowired
    TaskRepository taskRepository;

    public List<Solution> getAllSolution() {
        return solutionRepository.findAll();
    }

    public ApiResponse getSolutionById(Integer id) {
        Optional<Solution> byId = solutionRepository.findById(id);
        return byId.map(help -> new ApiResponse("Success", true, help)).orElseGet(() -> new ApiResponse("Solution not found!", false));
    }

    public ApiResponse addSolution(SolutionDto solutionDto) {
        Optional<Task> byId = taskRepository.findById(solutionDto.getTaskId());
        if (!byId.isPresent())
            return new ApiResponse("Task not found!", false);

        boolean b = solutionRepository.existsByNameAndTask(solutionDto.getName(), byId.get());
        if (b)
            return new ApiResponse("This Solution already exist!",false);

        Solution solution = new Solution();
        solution.setName(solutionDto.getName());
        solution.setTask(byId.get());
        solutionRepository.save(solution);
        return new ApiResponse("Successfully added!", true);
    }

    public ApiResponse deleteSolution(Integer id) {
        Optional<Solution> byId = solutionRepository.findById(id);
        if (!byId.isPresent())
            new ApiResponse("Solution not found!", false);
        solutionRepository.deleteById(id);
        return new ApiResponse("Deleted successfully!", true);
    }

    public ApiResponse editSolution(Integer id, SolutionDto solutionDto) {
        Optional<Solution> byId = solutionRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("Solution not found!", false);

        Optional<Task> taskOptional = taskRepository.findById(solutionDto.getTaskId());
        if (!taskOptional.isPresent())
            return new ApiResponse("Task not found!", false);

        boolean b = solutionRepository.existsByNameAndTask(solutionDto.getName(), taskOptional.get());
        if (b)
            return new ApiResponse("This Solution already exist!",false);
        Solution  solution = byId.get();
        solution.setName(solutionDto.getName());
        solution.setTask(taskOptional.get());
        solutionRepository.save(solution);
        return new ApiResponse("Edited successfully!", true);
    }

}