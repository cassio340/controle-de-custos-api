package br.com.cassio340.gestaodecustos.mapper;

import br.com.cassio340.gestaodecustos.dto.ExpenseResponse;
import br.com.cassio340.gestaodecustos.dto.MerchantResponse;
import br.com.cassio340.gestaodecustos.entities.Merchant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class MerchantMapper {
    private final ExpenseMapper expenseMapper;
    public MerchantResponse toResponse (Merchant merchant){
        List<ExpenseResponse> list = merchant.getExpenses().
                stream().map(e -> expenseMapper.toResponse(e)).toList();
        return new MerchantResponse(merchant.getId(), merchant.getName(),list);
    }
}
