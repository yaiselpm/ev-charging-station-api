package com.killer.evchargingstationapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String address;
    private Long latitude;
    private Long longitude;
    
    /**
     * @param address
     * @param latitude
     * @param longitude
     */
    public Location(String address, Long latitude, Long longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
