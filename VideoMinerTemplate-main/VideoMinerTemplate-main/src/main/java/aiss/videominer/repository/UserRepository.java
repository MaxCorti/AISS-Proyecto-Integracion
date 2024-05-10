package aiss.videominer.repository;

import aiss.videominer.model.Channel;
import aiss.videominer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
