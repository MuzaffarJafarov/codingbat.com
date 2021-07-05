package uz.muzaffar.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.muzaffar.codingbat.entity.Solution;
import uz.muzaffar.codingbat.entity.Task;

public interface SolutionRepository extends JpaRepository<Solution,Integer> {
    boolean existsByNameAndTask(String name, Task task);
}
