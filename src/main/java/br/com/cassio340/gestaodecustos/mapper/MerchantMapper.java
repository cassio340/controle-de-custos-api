package br.com.cassio340.gestaodecustos.mapper;


import br.com.cassio340.gestaodecustos.dto.MerchantRequest;
import br.com.cassio340.gestaodecustos.dto.MerchantResponse;
import br.com.cassio340.gestaodecustos.entities.Merchant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;


@Component
@RequiredArgsConstructor
public class MerchantMapper {

    public MerchantResponse toResponse (Merchant merchant){

        if (merchant == null) {
            return null;
        }
        return new MerchantResponse(merchant.getId(), merchant.getName());
    }

    public Merchant toEntity (MerchantRequest request){

        return new Merchant(null,request.getName(),new ArrayList<>());
    }

    public void update (Merchant merchant, MerchantRequest request){
        merchant.setName(request.getName());
    }
}
