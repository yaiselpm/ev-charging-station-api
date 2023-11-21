package com.killer.evchargingstationapi.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.killer.evchargingstationapi.domain.ChargingStation;
import com.killer.evchargingstationapi.domain.Status;

import java.util.List;

@Repository
public interface ChargingStationRepository extends JpaRepository<ChargingStation,String>{

   List<ChargingStation> findByStatus(Status status);
}
