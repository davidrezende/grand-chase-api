package br.com.gc.api.repository;

import br.com.gc.api.model.UserItemAttribute;
import br.com.gc.api.model.UserItemAttributeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserItemAttributeRepository extends JpaRepository<UserItemAttribute, UserItemAttributeId> {
}
