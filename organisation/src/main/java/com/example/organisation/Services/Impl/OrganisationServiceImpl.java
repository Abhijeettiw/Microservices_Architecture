package com.example.organisation.Services.Impl;

import com.example.organisation.Dto.OrganisationDto;
import com.example.organisation.Entity.Organisation;
import com.example.organisation.Repository.OrganisationRepo;
import com.example.organisation.Services.OrganisationServices;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrganisationServiceImpl implements OrganisationServices {

    @Autowired
    private OrganisationRepo organisationRepo;
    @Autowired
    private ModelMapper mapper;
    @Override
    public OrganisationDto createOrg(OrganisationDto organisationDto) {
        Organisation organisation = mapper.map(organisationDto,Organisation.class);
        organisationRepo.save(organisation);
        return mapper.map(organisation,OrganisationDto.class);
    }

    @Override
    public OrganisationDto updateOrg(OrganisationDto organisationDto, Long orgId) {
        Organisation organisation = organisationRepo.getReferenceById(orgId);
        organisation.setOrgCode(organisationDto.getOrgCode());
        organisation.setOrgDescription(organisationDto.getOrgDescription());
        organisation.setOrganisationName(organisationDto.getOrganisationName());
        organisationRepo.save(organisation);
        return mapper.map(organisation,OrganisationDto.class);
    }

    @Override
    public Void deleteOrg(Long orgId) {
        if(orgId != null && orgId>0)
            organisationRepo.deleteById(orgId);
        return null;
    }

    @Override
    public List<OrganisationDto> getByName(String name) {
        List<Organisation> organisation = organisationRepo.getByName(name);
        List<OrganisationDto> organisationDto = organisation.stream().map((org)-> mapper.map(org,OrganisationDto.class)).collect(Collectors.toList());
        return organisationDto;
    }

    @Override
    public OrganisationDto getById(Long orgId) {
        Organisation organisation = organisationRepo.getReferenceById(orgId);
        return mapper.map(organisation,OrganisationDto.class);
    }

    @Override
    public OrganisationDto getByCode(String orgCode) {
        Organisation organisation = organisationRepo.findAllByOrgCode(orgCode);
        return mapper.map(organisation,OrganisationDto.class);    }
}
