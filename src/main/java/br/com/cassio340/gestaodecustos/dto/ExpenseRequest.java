package br.com.cassio340.gestaodecustos.dto;
import br.com.cassio340.gestaodecustos.entities.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    @NotNull (message = "Category is required ")
    private Category category;


    @NotNull (message = "Merchant is required")
    private Long merchantId;

    @NotNull (message =  "User is required")
    private Long userId;

}
