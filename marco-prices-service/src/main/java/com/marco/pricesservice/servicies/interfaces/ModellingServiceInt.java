package com.marco.pricesservice.servicies.interfaces;

import com.marco.pricesservice.dto.ApiPrice;
import com.marco.pricesservice.model.Price;

/**
 * Simple service to help the controller to retrieve the data from the data
 * source
 * 
 * @author msolina
 *
 */
public interface ModellingServiceInt {

    /**
     * It converts a {@link Price} into an {@link ApiPrice}
     * 
     * @param price
     * @return
     */
    public ApiPrice fromPriceToApiPrice(Price price);

    /**
     * It converts an {@link ApiPrice} into a {@link Price}
     * 
     * @param apiPrice
     * @return
     */
    public Price fromApiPriceToPrice(ApiPrice apiPrice);

}
