package br.com.gc.api.repository;

import br.com.gc.api.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.ArrayList;
import java.util.Optional;

public interface CharacterRepository extends JpaRepository<Character, Integer> {
    Optional<ArrayList<Character>> findByLoginUID(Integer loginUID);
    Optional<Character> findByLoginUIDAndCharType(Integer loginUID, Integer charType);
}
