package br.com.gc.api.repository;

import br.com.gc.api.model.UserItemDuration;
import br.com.gc.api.model.UserItemDurationId;
import br.com.gc.api.model.UserItemPeriod;
import br.com.gc.api.model.UserItemPeriodId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserItemPeriodRepository extends JpaRepository<UserItemPeriod, UserItemPeriodId> {
}
