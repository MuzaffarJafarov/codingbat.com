package uz.muzaffar.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.muzaffar.codingbat.entity.*;
import uz.muzaffar.codingbat.payload.HelpDto;
import uz.muzaffar.codingbat.payload.ProgressDto;
import uz.muzaffar.codingbat.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class ProgressService {
    @Autowired
    ProgressRepository progressRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    public List<Progress> getAllProgress() {
        return progressRepository.findAll();
    }

    public ApiResponse getProgressById(Integer id) {
        Optional<Progress> byId = progressRepository.findById(id);
        return byId.map(help -> new ApiResponse("Success", true, help)).orElseGet(() -> new ApiResponse("Progress not found!", false));
    }

    public ApiResponse addProgress(ProgressDto progressDto) {
        Optional<User> user = userRepository.findById(progressDto.getUserId());
        if (!user.isPresent())
            return new ApiResponse("User not found!",false);

        Optional<Task> optionalTask = taskRepository.findById(progressDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("Task not found!", false);

        Progress progress = new Progress();
        progress.setChance(progressDto.getChance());
        progress.setPassed(progressDto.getIsPassed());
        progress.setStar(progressDto.getIsStar());
        progress.setTask(optionalTask.get());
        progress.setUser(user.get());
        progressRepository.save(progress);
        return new ApiResponse("Successfully added!", true);
    }

    public ApiResponse deleteProgress(Integer id) {
        Optional<Progress> byId = progressRepository.findById(id);
        if (!byId.isPresent())
            new ApiResponse("Progress not found!", false);
        progressRepository.deleteById(id);
        return new ApiResponse("Deleted successfully!", true);
    }

    public ApiResponse editProgress(Integer id, ProgressDto progressDto) {
        Optional<Progress> byId = progressRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("Progress not found!", false);

        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent())
            return new ApiResponse("Task not found!", false);

        Optional<User> user = userRepository.findById(progressDto.getUserId());
        if (!user.isPresent())
            return new ApiResponse("User not found!",false);


        Progress progress = byId.get();
        progress.setChance(progressDto.getChance());
        progress.setPassed(progressDto.getIsPassed());
        progress.setStar(progressDto.getIsStar());
        progress.setTask(optionalTask.get());
        progress.setUser(user.get());
        progressRepository.save(progress);
        return new ApiResponse("Edited successfully!", true);
    }

}