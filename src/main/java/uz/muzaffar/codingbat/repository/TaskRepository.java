package uz.muzaffar.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.muzaffar.codingbat.entity.Help;
import uz.muzaffar.codingbat.entity.Language;
import uz.muzaffar.codingbat.entity.Task;
import uz.muzaffar.codingbat.entity.Topic;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    boolean existsByNameAndTopic(String name, Topic topic);
    boolean existsByNameAndTopicAndIdNot(String name, Topic topic, Integer id);

}
