package uz.muzaffar.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.muzaffar.codingbat.entity.ApiResponse;
import uz.muzaffar.codingbat.entity.Checking;
import uz.muzaffar.codingbat.entity.Task;
import uz.muzaffar.codingbat.entity.Topic;
import uz.muzaffar.codingbat.payload.CheckingDto;
import uz.muzaffar.codingbat.payload.TaskDto;
import uz.muzaffar.codingbat.repository.CheckingRepository;
import uz.muzaffar.codingbat.repository.TaskRepository;
import uz.muzaffar.codingbat.repository.TopicRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CheckingService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    CheckingRepository checkingRepository;

    public List<Checking> getAllChecking() {
        return checkingRepository.findAll();
    }

    public ApiResponse getCheckingById(Integer id) {
        Optional<Checking> byId = checkingRepository.findById(id);
        return byId.map(help -> new ApiResponse("Success", true, help)).orElseGet(() -> new ApiResponse("Checking not found!", false));
    }

    public ApiResponse addChecking(CheckingDto checkingDto) {
        Optional<Task> taskOptional = taskRepository.findById(checkingDto.getTaskId());
        if (!taskOptional.isPresent())
            return new ApiResponse("Task not found!", false);

        Checking checking = new Checking();
        checking.setExpected(checkingDto.getExpected());
        checking.setRun(checkingDto.getRun());
        checking.setTask(taskOptional.get());
        checkingRepository.save(checking);
        return new ApiResponse("Successfully added!", true);
    }

    public ApiResponse deleteChecking(Integer id) {
        Optional<Checking> byId = checkingRepository.findById(id);
        if (!byId.isPresent())
            new ApiResponse("Checking not found!", false);
        checkingRepository.deleteById(id);
        return new ApiResponse("Deleted successfully!", true);
    }

    public ApiResponse editChecking(Integer id, CheckingDto checkingDto) {
        Optional<Checking> byId = checkingRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("Checking not found!", false);

        Optional<Task> b = taskRepository.findById(checkingDto.getTaskId());
        if (!b.isPresent())
            return new ApiResponse("Task not found!", false);

        boolean b1 = checkingRepository.existsByRunAndExpectedAndIdNot(checkingDto.getRun(), checkingDto.getExpected(), id);
        if (b1)
            return new ApiResponse("This 'Checking' already exist!",false);

        Checking checking = byId.get();
        checking.setTask(b.get());
        checking.setExpected(checkingDto.getExpected());
        checking.setRun(checkingDto.getRun());
        checkingRepository.save(checking);
        return new ApiResponse("Successfully 'Checking' edited!", true);
    }

}