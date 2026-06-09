package br.com.cassio340.gestaodecustos.dto;

import br.com.cassio340.gestaodecustos.entities.Expense;
import br.com.cassio340.gestaodecustos.entities.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExpenseResponse {
    private Long id;
    private String name;
    private BigDecimal amount;
    private Category category;
    private MerchantResponse merchant;



}
