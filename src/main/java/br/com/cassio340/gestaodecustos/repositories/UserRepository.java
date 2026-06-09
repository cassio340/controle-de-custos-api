package br.com.cassio340.gestaodecustos.repositories;

import br.com.cassio340.gestaodecustos.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
}
