package br.com.gc.api.repository;

import br.com.gc.api.model.GCApiUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GCApiUsersRepository extends JpaRepository<GCApiUsers, Integer> {
    Optional<GCApiUsers> findByLogin(String login);
}
