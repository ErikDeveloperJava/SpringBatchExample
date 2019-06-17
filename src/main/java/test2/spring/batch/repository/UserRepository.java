package test2.spring.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test2.spring.batch.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
}
