package com.nicmora.cleanarchitecture.infrastructure.delivery.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;

import java.io.Serializable;

@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRequest implements Serializable {

    String firstName;
    String lastName;
    String email;

}
