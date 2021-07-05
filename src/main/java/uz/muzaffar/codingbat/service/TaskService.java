package uz.muzaffar.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.muzaffar.codingbat.entity.*;
import uz.muzaffar.codingbat.payload.HelpDto;
import uz.muzaffar.codingbat.payload.TaskDto;
import uz.muzaffar.codingbat.repository.HelpRepository;
import uz.muzaffar.codingbat.repository.LanguageRepository;
import uz.muzaffar.codingbat.repository.TaskRepository;
import uz.muzaffar.codingbat.repository.TopicRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TopicRepository topicRepository;

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public ApiResponse getTaskById(Integer id) {
        Optional<Task> byId = taskRepository.findById(id);
        return byId.map(help -> new ApiResponse("Success", true, help)).orElseGet(() -> new ApiResponse("Task not found!", false));
    }

    public ApiResponse addTask(TaskDto taskDto) {
        Optional<Topic> topicById = topicRepository.findById(taskDto.getTopicId());
        if (!topicById.isPresent())
            return new ApiResponse("Topic not found!", false);

        boolean b = taskRepository.existsByNameAndTopic(taskDto.getName(), topicById.get());
        if (b)
            return new ApiResponse("This task already exist!", false);

        Task task = new Task();
        task.setDescription(taskDto.getDescription());
        task.setName(taskDto.getName());
        task.setLevel(taskDto.getLevel());
        task.setTopic(topicById.get());
        taskRepository.save(task);
        return new ApiResponse("Successfully task added!", true);
    }

    public ApiResponse deleteTask(Integer id) {
        Optional<Task> byId = taskRepository.findById(id);
        if (!byId.isPresent())
            new ApiResponse("Task not found!", false);
        taskRepository.deleteById(id);
        return new ApiResponse("Deleted successfully!", true);
    }

    public ApiResponse editTask(Integer id, TaskDto taskDto) {
        Optional<Task> byId = taskRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("Task not found!", false);

        Optional<Topic> topicById = topicRepository.findById(taskDto.getTopicId());
        if (!topicById.isPresent())
            return new ApiResponse("Topic not found!", false);

        boolean b = taskRepository.existsByNameAndTopicAndIdNot(taskDto.getName(), topicById.get(), id);
        if (b)
            return new ApiResponse("This 'Task' already exist!", false);

        Task task = byId.get();
        task.setDescription(taskDto.getDescription());
        task.setName(taskDto.getName());
        task.setLevel(taskDto.getLevel());
        task.setTopic(topicById.get());
        taskRepository.save(task);
        return new ApiResponse("Successfully Task edited!", true);
    }

}