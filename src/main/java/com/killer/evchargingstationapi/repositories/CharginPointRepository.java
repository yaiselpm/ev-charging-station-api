package com.killer.evchargingstationapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.killer.evchargingstationapi.domain.ChargingPoint;

@Repository
public interface CharginPointRepository extends JpaRepository<ChargingPoint, Long>{
    
}
