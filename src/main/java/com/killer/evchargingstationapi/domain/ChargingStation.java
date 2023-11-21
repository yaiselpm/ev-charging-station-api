package com.killer.evchargingstationapi.domain;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "charging_station")
// @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})   
public class ChargingStation {
    
    @Id
    @GenericGenerator(name = "stationId", strategy = "com.killer.evchargingstationapi.config.CustomIdGenerator") 
    @GeneratedValue(generator = "stationId",strategy = GenerationType.IDENTITY)
    // @Id
    // @GeneratedValue(generator = "system-uuid")
    // @GenericGenerator(name = "system-uuid", strategy = "uuid")
    // @Column(length = 20)
    private String id;
    
    @OneToOne
    // @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    // @PrimaryKeyJoinColumn
    // @JsonIgnore
    private Location location;

    @Enumerated(EnumType.STRING)
    private ChargerType chargerType;

    @OneToMany(mappedBy = "chargingStation")
    private List<ChargingPoint> chargingPoints; 

    @Enumerated(EnumType.STRING)
    private Status status;

    /**
     * 
     */
    public ChargingStation() {}

    /**
     * @param location
     * @param chargerType
     * @param status
     */
    public ChargingStation(Location location, ChargerType chargerType, Status status) {
        this.location = location;
        this.chargerType = chargerType;
        this.status = status;
    }

    /**
     * @param id
     * @param location
     * @param chargerType
     * @param status
     */
    public ChargingStation(String id, Location location, ChargerType chargerType, Status status) {
        this.id = id;
        this.location = location;
        this.chargerType = chargerType;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ChargingStation [id=" + id + ", location=" + location + ", chargerType=" + chargerType
                + ", chargingPoints=" + chargingPoints + ", status=" + status + "]";
    }
    
}
