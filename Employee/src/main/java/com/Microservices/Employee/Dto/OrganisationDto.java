package com.Microservices.Employee.Dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class OrganisationDto {
    private Long id;
    private String organisationName;
    private String orgDescription;
    private String orgCode;
}
