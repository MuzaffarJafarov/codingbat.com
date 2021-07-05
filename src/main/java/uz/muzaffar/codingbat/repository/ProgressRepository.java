package uz.muzaffar.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.muzaffar.codingbat.entity.Help;
import uz.muzaffar.codingbat.entity.Language;
import uz.muzaffar.codingbat.entity.Progress;

public interface ProgressRepository extends JpaRepository<Progress,Integer> {


}
