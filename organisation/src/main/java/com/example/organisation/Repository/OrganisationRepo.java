package com.example.organisation.Repository;

import com.example.organisation.Entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganisationRepo extends JpaRepository<Organisation,Long> {
    @Query("select o from Organisation o where o.organisationName like %:name%")
    public List<Organisation> getByName(@Param("name") String name);

    @Query("select o from Organisation o where o.orgCode =:orgCode")
    public Organisation findAllByOrgCode(@Param("orgCode") String orgCode);
}
