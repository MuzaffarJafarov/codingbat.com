package uz.muzaffar.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.muzaffar.codingbat.entity.ApiResponse;
import uz.muzaffar.codingbat.entity.Help;
import uz.muzaffar.codingbat.entity.Language;
import uz.muzaffar.codingbat.entity.Topic;
import uz.muzaffar.codingbat.payload.HelpDto;
import uz.muzaffar.codingbat.payload.TopicDto;
import uz.muzaffar.codingbat.repository.HelpRepository;
import uz.muzaffar.codingbat.repository.LanguageRepository;
import uz.muzaffar.codingbat.repository.TopicRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    LanguageRepository languageRepository;

    public List<Topic> getAllTopic() {
        return topicRepository.findAll();
    }

    public ApiResponse getTopicById(Integer id) {
        Optional<Topic> byId = topicRepository.findById(id);
        return byId.map(topic -> new ApiResponse("Topic found successfully!", true, topic)).orElseGet(() -> new ApiResponse("Topic not found!", true));
    }

    public ApiResponse addTopic(TopicDto topicDto) {
        Optional<Language> languageById = languageRepository.findById(topicDto.getLanguageId());
        if (!languageById.isPresent())
            return new ApiResponse("Language not found!", false);
        boolean b = topicRepository.existsByNameAndLanguage(topicDto.getName(), languageById.get());
        if (b)
            return new ApiResponse("This topuc already exist!", false);
        Topic topic = new Topic();
        topic.setName(topicDto.getName());
        topic.setLanguage(languageById.get());
        topicRepository.save(topic);
        return new ApiResponse("Successfully added!", true);
    }

    public ApiResponse deleteTopic(Integer id) {
        Optional<Topic> byId = topicRepository.findById(id);
        if (!byId.isPresent())
            new ApiResponse("Topic not found!", false);
        topicRepository.deleteById(id);
        return new ApiResponse("Deleted successfully!", true);
    }

    public ApiResponse editTopic(Integer id, TopicDto topicDto) {
        Optional<Topic> byId = topicRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("Topic not found!", false);
        Optional<Language> languageById = languageRepository.findById(topicDto.getLanguageId());
        if (!languageById.isPresent())
            return new ApiResponse("Language not found!", false);
        boolean b = topicRepository.existsByNameAndLanguageAndIdNot(topicDto.getName(), languageById.get(), id);
        if (b)
            return new ApiResponse("This Topic already exist!", false);
        Topic topic = byId.get();
        topic.setName(topicDto.getName());
        topic.setLanguage(languageById.get());
        topicRepository.save(topic);
        return new ApiResponse("Topic added successfully!",true);
    }

}