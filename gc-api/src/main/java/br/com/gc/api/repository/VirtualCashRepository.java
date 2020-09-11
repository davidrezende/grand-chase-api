package br.com.gc.api.repository;

import br.com.gc.api.model.VirtualCash;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VirtualCashRepository extends JpaRepository<VirtualCash, Integer> {
    Optional<VirtualCash> findByLoginUID(Integer loginUID);
}
