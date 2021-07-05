package uz.muzaffar.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.muzaffar.codingbat.entity.Checking;
import uz.muzaffar.codingbat.entity.Task;
import uz.muzaffar.codingbat.entity.Topic;

public interface CheckingRepository extends JpaRepository<Checking,Integer> {
    boolean existsByRunAndExpectedAndIdNot(Double run, String expected, Integer id);

}
