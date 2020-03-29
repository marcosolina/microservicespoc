package com.marco.pricesservice.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Pojo used in the Rest API to return a list of {@link ApiPrice}
 * 
 * @author msolina
 *
 */
@ApiModel(value = "Prices Wrapper", description = "It contains the list of prices")
public class ApiPrices implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "List of prices")
    private List<ApiPrice> prices = new ArrayList<>();

    public boolean addApiPrice(ApiPrice apiPrice) {
        return prices.add(apiPrice);
    }

    public List<ApiPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<ApiPrice> prices) {
        this.prices = prices;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
