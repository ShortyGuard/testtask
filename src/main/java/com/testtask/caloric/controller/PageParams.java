package com.testtask.caloric.controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
public class PageParams  {
    @Positive(message = "page must be greater than 0")
    Integer page = 1;

    @Positive(message = "page must be greater than  0")
    Integer size = 5;

    @Pattern(regexp = "ASC|DESC", message = "sortDir can be only {ASC or DESC}")
    String sortDir = "ASC";

    @Pattern(regexp = "name|manufacturer", message = "sortDirection can be only {name or manufacturer}")
    String sort = "name";
}
