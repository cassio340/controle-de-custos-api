package br.com.cassio340.gestaodecustos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MerchantResponse {
    private Long id;
    private String name;
    private List<ExpenseResponse> expenses;
}
