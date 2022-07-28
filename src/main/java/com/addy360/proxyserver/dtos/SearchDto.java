package com.addy360.proxyserver.dtos;


import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Data
@Component
public class SearchDto {

    @NotBlank
    @URL
    String url;

}
