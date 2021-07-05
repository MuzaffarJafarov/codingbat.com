package uz.muzaffar.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.muzaffar.codingbat.entity.Help;
import uz.muzaffar.codingbat.entity.Language;
import uz.muzaffar.codingbat.entity.Topic;

public interface TopicRepository extends JpaRepository<Topic,Integer> {
    boolean existsByNameAndLanguage(String name, Language language);
    boolean existsByNameAndLanguageAndIdNot(String name, Language language, Integer id);

}
