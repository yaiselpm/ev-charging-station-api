package com.killer.evchargingstationapi.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Location {
    @Id
    private String address;
    private Long latitude;
    private Long longitude;
}
