package br.com.gc.api.repository;

import br.com.gc.api.model.CharacterInfo;
import br.com.gc.api.model.VirtualCash;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CharacterInfoRepository extends JpaRepository<CharacterInfo, Integer> {
    Optional<List<CharacterInfo>> findByLoginUID(Integer loginUID);
}
