package com.killer.evchargingstationapi.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.killer.evchargingstationapi.domain.ChargingStation;
import com.killer.evchargingstationapi.domain.Status;
import com.killer.evchargingstationapi.repositories.ChargingStationRepository;

@Service
public class ChargingStationService {
    
    @Autowired
    private ChargingStationRepository chargingStationRepository;

    @Transactional
    public ChargingStation createChargingStation(ChargingStation chargingStation){
        return chargingStationRepository.save(chargingStation);
    }

    public ChargingStation findByAddressId(String addressId){
        return chargingStationRepository.getReferenceById(addressId);
    }

    public List<ChargingStation> findWhereStatusAvailable(){
        return chargingStationRepository.findByStatus(Status.Available);
    }

    @Transactional
    public ChargingStation updateChargingStation(ChargingStation chargingStation){
        return chargingStationRepository.save(chargingStation);
    }

    public void deleteStation(String id){
        chargingStationRepository.deleteById(id);
    }

    public boolean existsById(String id){
        return chargingStationRepository.existsById(id);
    }

    public List<ChargingStation> findAll(){
        return chargingStationRepository.findAll();
    }
}
