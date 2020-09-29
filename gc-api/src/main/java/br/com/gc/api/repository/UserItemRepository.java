package br.com.gc.api.repository;

import br.com.gc.api.model.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserItemRepository extends JpaRepository<UserItem, Long> {

    Optional<UserItem> findFirstByItemIDAndLoginUIDOrderByItemUIDDesc(Integer itemID, Integer loginUID);
    Optional<List<UserItem>> findByLoginUIDAndItemID(Integer loginUID, Integer itemID);

}
