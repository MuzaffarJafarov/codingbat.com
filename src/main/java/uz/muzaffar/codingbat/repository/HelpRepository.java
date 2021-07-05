package uz.muzaffar.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.muzaffar.codingbat.entity.Help;
import uz.muzaffar.codingbat.entity.Language;

public interface HelpRepository extends JpaRepository<Help,Integer> {
    boolean existsBySeccionNameAndLanguage(String seccionName, Language language);
    boolean existsBySeccionName(String seccionName);
}
