package com.killer.evchargingstationapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.killer.evchargingstationapi.domain.ChargingStation;
import com.killer.evchargingstationapi.domain.Location;
import com.killer.evchargingstationapi.domain.Status;
import com.killer.evchargingstationapi.mappers.ChargingStationToDTo;
import com.killer.evchargingstationapi.repositories.ChargingStationRepository;
import com.killer.evchargingstationapi.repositories.LocationRepository;
import com.killer.evchargingstationapi.services.dtos.ChargingStationDTO;

@Service
public class ChargingStationService {
    
    @Autowired
    private ChargingStationRepository chargingStationRepository;

    @Autowired
    private LocationRepository locationRepository;

    private ChargingStationToDTo mapper;

    /**
     * @param chargingStationRepository
     */
    public ChargingStationService(ChargingStationRepository chargingStationRepository, ChargingStationToDTo mapper) {
        this.chargingStationRepository = chargingStationRepository;
        this.mapper = mapper;
    }

    @Transactional
    public ChargingStation createChargingStation(ChargingStation chargingStation){
        Location location = locationRepository.save(chargingStation.getLocation());
        chargingStation.setLocation(location);
        return chargingStationRepository.save(chargingStation);
    }

    public ChargingStationDTO findByAddressId(String addressId){
       ChargingStation chargingStation = chargingStationRepository.getReferenceById(addressId);
       ChargingStationDTO chargingStationDTO = mapper.map(chargingStation);
       return chargingStationDTO;

    }

    public List<ChargingStationDTO> findWhereStatusAvailable(){
        List<ChargingStation> chargingStations = chargingStationRepository.findByStatus(Status.Available);
        List<ChargingStationDTO> chargingStationDTOs= new ArrayList<ChargingStationDTO>();
        for (ChargingStation chargingStation : chargingStations) {
            ChargingStationDTO stationDTO = mapper.map(chargingStation);
            chargingStationDTOs.add(stationDTO);
        }
        return chargingStationDTOs;
    }

    @Transactional
    public ChargingStation updateChargingStation(ChargingStation chargingStation){
        locationRepository.save(chargingStation.getLocation());        
        return chargingStationRepository.save(chargingStation);
    }

    public void deleteStation(String id){        
        chargingStationRepository.deleteById(id);
    }

    public boolean existsById(String id){
        return chargingStationRepository.existsById(id);
    }

    public List<ChargingStationDTO> findAll(){
        List<ChargingStation> chargingStations = chargingStationRepository.findAll();
        List<ChargingStationDTO> chargingStationDTOs= new ArrayList<ChargingStationDTO>();
        for (ChargingStation chargingStation : chargingStations) {
            ChargingStationDTO stationDTO = mapper.map(chargingStation);
            chargingStationDTOs.add(stationDTO);
        }
        return chargingStationDTOs;
    }
}
