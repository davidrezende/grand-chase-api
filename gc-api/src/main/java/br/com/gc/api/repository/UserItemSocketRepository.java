package br.com.gc.api.repository;

import br.com.gc.api.model.UserItemSocket;
import br.com.gc.api.model.UserItemSocketId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserItemSocketRepository extends JpaRepository<UserItemSocket, UserItemSocketId> {

}
