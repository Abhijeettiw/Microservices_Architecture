package com.example.organisation.Controller;

import com.example.organisation.Dto.OrganisationDto;
import com.example.organisation.Services.OrganisationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organisation/")
public class OrganisationController {

    @Autowired
    private OrganisationServices organisationServices;

    @PostMapping("newOrg")
    public ResponseEntity<?> newOrg(@RequestBody OrganisationDto organisationDto){
        return new ResponseEntity<>(organisationServices.createOrg(organisationDto), HttpStatus.CREATED);
    }
    @PutMapping("updateOrg")
    public ResponseEntity<?> updateOrg(@RequestBody OrganisationDto organisationDto , @RequestParam("orgId") Long orgId){
        return new ResponseEntity<>(organisationServices.updateOrg(organisationDto,orgId),HttpStatus.OK);
    }
    @GetMapping("orgById/{orgId}")
    public ResponseEntity<?> orgById(@PathVariable("orgId") Long orgId){
        return new ResponseEntity<>(organisationServices.getById(orgId),HttpStatus.FOUND);
    }
    @GetMapping("orgByCode")
    public ResponseEntity<?> orgByCode(@RequestParam("orgCode") String orgCode){
        return new ResponseEntity<>(organisationServices.getByCode(orgCode),HttpStatus.FOUND);
    }
    @GetMapping("orgByName")
    public ResponseEntity<?> orgByName(@RequestParam("orgName") String orgName){
        return new ResponseEntity<>(organisationServices.getByName(orgName),HttpStatus.FOUND);
    }
    @DeleteMapping("deleteOrg")
    public ResponseEntity<?> deleteOrg(@RequestParam("orgId") Long orgId){

        return new ResponseEntity<>(organisationServices.deleteOrg(orgId),HttpStatus.OK);
    }
}
