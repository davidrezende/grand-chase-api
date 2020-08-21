package br.com.gc.api.repository;

import br.com.gc.api.model.UserItemAttribute;
import br.com.gc.api.model.UserItemAttributeId;
import br.com.gc.api.model.UserItemDuration;
import br.com.gc.api.model.UserItemDurationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserItemDurationRepository extends JpaRepository<UserItemDuration, UserItemDurationId> {
}
