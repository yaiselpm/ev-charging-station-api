package com.killer.evchargingstationapi.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "chargingPoint")
public class ChargingPoint {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCharging;
    private Double powerLevel;
    private Boolean inUse;
    @ManyToOne()
    private ChargingStation chargingStation;
}
