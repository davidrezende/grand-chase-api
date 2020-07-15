package br.com.gc.api.repository;

import br.com.gc.api.model.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserItemRepository extends JpaRepository<UserItem, Long> {

}
