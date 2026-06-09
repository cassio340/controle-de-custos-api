package br.com.cassio340.gestaodecustos.repositories;

import br.com.cassio340.gestaodecustos.entities.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant,Long> {
}
