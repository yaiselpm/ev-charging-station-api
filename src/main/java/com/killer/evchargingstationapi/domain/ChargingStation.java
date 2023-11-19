package com.killer.evchargingstationapi.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "charging_station")
public class ChargingStation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @OneToOne
    private Location location;

    private ChargerType chargerType;

    @OneToMany(mappedBy = "chargingStation")
    private List<ChargingPoint> chargingPoints; 

    private Status status;
    
}
