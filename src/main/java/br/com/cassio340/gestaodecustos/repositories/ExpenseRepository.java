package br.com.cassio340.gestaodecustos.repositories;



import br.com.cassio340.gestaodecustos.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository <Expense, Long> {

}
