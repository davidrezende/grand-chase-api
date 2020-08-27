package br.com.gc.api.repository;

import br.com.gc.api.model.Characters;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.ArrayList;
import java.util.Optional;

public interface CharacterRepository extends JpaRepository<Characters, Integer> {
    Optional<ArrayList<Characters>> findByLoginUID(Integer loginUID);
    Optional<Characters> findByLoginUIDAndCharType(Integer loginUID, Integer charType);
}
