package com.marco.pricesservice.servicies.implementations;

import com.marco.pricesservice.dto.ApiPrice;
import com.marco.pricesservice.model.Price;
import com.marco.pricesservice.servicies.interfaces.ModellingServiceInt;

public class ModellingServiceImpl implements ModellingServiceInt {

    @Override
    public ApiPrice fromPriceToApiPrice(Price price) {
        if (price == null) {
            return null;
        }
        ApiPrice ap = new ApiPrice();
        ap.setDishName(price.getDishName());
        ap.setPrice(price.getPrice());
        return ap;
    }

    @Override
    public Price fromApiPriceToPrice(ApiPrice apiPrice) {
        if (apiPrice == null) {
            return null;
        }
        Price p = new Price();
        p.setDishName(apiPrice.getDishName());
        p.setPrice(apiPrice.getPrice());
        return p;
    }

}
