package com.microservice.storemanagement.business.mappers;

import com.microservice.storemanagement.business.entities.Store;
import com.microservice.storemanagement.business.services.StoreManagementService;
import com.microservice.storemanagement.business.vo.StoreVO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class StoreManagementMapper {

    public Store map(final StoreVO storeVO) {
        return Store.builder()
                .storeName(storeVO.getStoreName())
                .storeUsers(storeVO.getStoreUsers())
                .storeOpportunities(storeVO.getStoreOpportunities())
                .build();
    }

}
