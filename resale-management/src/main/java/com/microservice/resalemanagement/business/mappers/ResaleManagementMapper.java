package com.microservice.resalemanagement.business.mappers;

import com.microservice.resalemanagement.business.entities.Resale;
import com.microservice.resalemanagement.business.vo.ResaleVO;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;

@NoArgsConstructor
@Mapper
public class ResaleManagementMapper {

    public Resale map(final ResaleVO resaleVO) {
        return Resale.builder()
                .legalEntityCode(resaleVO.getLegalEntityCode())
                .resaleSocialName(resaleVO.getResaleSocialName())
                .build();
    }

}
