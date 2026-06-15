package br.com.cassio340.gestaodecustos.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MerchantRequest {
    @NotBlank (message = "Name is required")
    private String name;

}
