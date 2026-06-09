package br.com.cassio340.gestaodecustos.dto;

import br.com.cassio340.gestaodecustos.entities.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExpenseRequest {
    private String name;
    private BigDecimal amount;
    private Category category;
    private Long merchantId;
    private Long userId;


}
