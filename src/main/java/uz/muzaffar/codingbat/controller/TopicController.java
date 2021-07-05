package uz.muzaffar.codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.muzaffar.codingbat.entity.ApiResponse;
import uz.muzaffar.codingbat.entity.Help;
import uz.muzaffar.codingbat.entity.Topic;
import uz.muzaffar.codingbat.payload.HelpDto;
import uz.muzaffar.codingbat.payload.TopicDto;
import uz.muzaffar.codingbat.service.HelpService;
import uz.muzaffar.codingbat.service.TopicService;

import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    TopicService topicService;

    @GetMapping
    public List<Topic> getAll() {
        return topicService.getAllTopic();
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        ApiResponse topicById = topicService.getTopicById(id);
        return ResponseEntity.status(topicById.isSuccess() ? 201 : 404).body(topicById);
    }

    @PostMapping()
    public HttpEntity<?> add(@RequestBody TopicDto topicDto) {
        ApiResponse apiResponse = topicService.addTopic(topicDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = topicService.deleteTopic(id);
        if (apiResponse.isSuccess())
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@RequestBody TopicDto topicDto, @PathVariable Integer id) {
        ApiResponse apiResponse = topicService.editTopic(id, topicDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
