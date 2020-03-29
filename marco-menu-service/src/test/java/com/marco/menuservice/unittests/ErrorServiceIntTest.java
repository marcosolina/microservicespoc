package com.marco.menuservice.unittests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.marco.menuservice.errors.MarcoException;
import com.marco.menuservice.errors.ServiceError;
import com.marco.menuservice.services.implementations.ErrorServiceImpl;
import com.marco.menuservice.services.interfaces.MessageServiceInt;

@ExtendWith(MockitoExtension.class)
public class ErrorServiceIntTest {
    
    @InjectMocks
    private ErrorServiceImpl service = new ErrorServiceImpl();

    @Mock
    private MessageServiceInt msgService;

    private String mockErrorMessage = "This is a message";

    @BeforeEach
    public void init() {
        when(msgService.getMessage(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(mockErrorMessage);
    }

    @Test
    public void buildErrorTest() {
        ServiceError error = service.buildError("AA");
        assertThat(error).isNotNull();
    }

    @Test
    public void buildSimpleExceptionTest() {
        MarcoException ex = service.buildSimpleException("AA");
        assertThat(ex).isNotNull();
    }

    @Test
    public void buildSimpleExceptionWithStatus() {
        MarcoException ex = service.buildSimpleExceptionWithStatus(HttpStatus.BAD_REQUEST, "AA");
        assertThat(ex).isNotNull();
    }
}
