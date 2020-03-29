package com.marco.dishesservice.unittests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.marco.dishesservice.errors.ServiceError;
import com.marco.dishesservice.errors.MarcoException;
import com.marco.dishesservice.services.implementations.ErrorServiceImpl;
import com.marco.dishesservice.services.interfaces.ErrorServiceInt;
import com.marco.dishesservice.services.interfaces.MessageServiceInt;

/**
 * This class provides some unit tests for the Dish Error service <br>
 * TODO add some bad scenarios
 * 
 * @author msolina
 *
 */
@ExtendWith(MockitoExtension.class)
public class DishErrorServiceTests {

    @InjectMocks
    private ErrorServiceInt service = new ErrorServiceImpl();

    @Mock
    private MessageServiceInt msgService;

    private String mockErrorMessage = "This is a message";

    @Test
    public void buildErrorTest() {
        when(msgService.getMessage(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(mockErrorMessage);
        ServiceError error = service.buildError("AA");
        assertThat(error).isNotNull();
    }

    @Test
    public void buildSimpleExceptionTest() {
        MarcoException ex = service.buildSimpleException("AA");
        assertThat(ex).isNotNull();
    }
}
