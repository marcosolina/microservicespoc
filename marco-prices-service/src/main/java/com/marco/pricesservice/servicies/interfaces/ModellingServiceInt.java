package com.marco.pricesservice.servicies.interfaces;

import com.marco.pricesservice.dto.ApiPrice;
import com.marco.pricesservice.model.Price;

public interface ModellingServiceInt {

    public ApiPrice fromPriceToApiPrice(Price price);

    public Price fromApiPriceToPrice(ApiPrice apiPrice);

}
