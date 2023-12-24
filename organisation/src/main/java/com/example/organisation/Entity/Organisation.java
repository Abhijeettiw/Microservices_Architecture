package com.example.organisation.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "organisations")
@Getter
@Setter
@RequiredArgsConstructor
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "org_name")
    private String organisationName;
    private String orgDescription;
    private String orgCode;
}
