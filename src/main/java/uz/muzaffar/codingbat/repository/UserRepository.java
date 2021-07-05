package uz.muzaffar.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.muzaffar.codingbat.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByName(String name);

}
