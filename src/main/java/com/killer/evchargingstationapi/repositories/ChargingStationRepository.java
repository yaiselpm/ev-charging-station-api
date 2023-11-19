package com.killer.evchargingstationapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.killer.evchargingstationapi.domain.ChargingStation;

public interface ChargingStationRepository extends JpaRepository<ChargingStation,String>{
    
}
