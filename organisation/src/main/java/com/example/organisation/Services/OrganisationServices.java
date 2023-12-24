package com.example.organisation.Services;

import com.example.organisation.Dto.OrganisationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrganisationServices {
    public OrganisationDto createOrg(OrganisationDto organisationDto);
    public OrganisationDto updateOrg(OrganisationDto organisationDto,Long orgId);
    public Void deleteOrg(Long orgId);
    public List<OrganisationDto> getByName(String name);
    public OrganisationDto getById(Long orgId);
    public OrganisationDto getByCode(String orgCode);

}
