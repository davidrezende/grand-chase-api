package br.com.gc.api.repository;

import br.com.gc.api.model.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserItemRepository extends JpaRepository<UserItem, Long> {

    Optional<UserItem> findFirstByItemIDAndLoginUIDOrderByItemUIDDesc(Integer itemID, Integer loginUID);

 /*   set @itemuid1 = (SELECT TOP 1 (ItemUID) from gc.dbo.UIGAUserItem
    where ItemID = @itemid1 and LoginUID = @loginid1 ORDER BY ItemUID Desc)
    */
}
