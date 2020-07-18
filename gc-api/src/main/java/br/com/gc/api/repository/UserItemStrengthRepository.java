package br.com.gc.api.repository;

import br.com.gc.api.model.UserItemStrength;
import br.com.gc.api.model.UserItemStrengthId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserItemStrengthRepository extends JpaRepository<UserItemStrength, UserItemStrengthId> {
}
