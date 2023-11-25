package com.killer.evchargingstationapi.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.killer.evchargingstationapi.domain.ChargingStation;
import com.killer.evchargingstationapi.domain.Status;
import com.killer.evchargingstationapi.services.ChargingStationService;
import com.killer.evchargingstationapi.services.dtos.ChargingStationDTO;

@RestController
@RequestMapping(path = "/stations")
public class ChargingStationController {
    
    @Autowired
    private ChargingStationService chargingStationService;
    

    /**
     * @param chargingStationService
     */
    public ChargingStationController(ChargingStationService chargingStationService) {
        this.chargingStationService = chargingStationService;
    }

    @PostMapping
    public ResponseEntity<Void> createChargingStation(@RequestBody ChargingStation chargingStation, UriComponentsBuilder ucb){
        ChargingStation savedChargingStation = chargingStationService.createChargingStation(chargingStation);
        URI locationNewChargingStation= ucb
            .path("stations/{id}")
            .buildAndExpand(savedChargingStation.getId())
            .toUri();
        return ResponseEntity.created(locationNewChargingStation).build();
    }
    
    @GetMapping
    public ResponseEntity<List<ChargingStationDTO>> findAllChargingStation(){
        List<ChargingStationDTO> lChargingStations = chargingStationService.findAll();
        return ResponseEntity.ok(lChargingStations);
    }

    @GetMapping("/{requestedId}")
    public ResponseEntity<ChargingStationDTO> findByAddressId(@PathVariable String requestedId){
        ChargingStationDTO chargingStation = findChargingStation(requestedId);
        return (chargingStation!=null)? ResponseEntity.ok(chargingStation): ResponseEntity.notFound().build();
    }

    @PutMapping("/{requestedId}")
    public ResponseEntity<Void> updateChargingStation(@PathVariable String requestedId,@RequestBody ChargingStation stationUpdate){
        boolean exists = chargingStationService.existsById(requestedId);
        if (exists) {            
            ChargingStation updatedStation = new ChargingStation(requestedId, 
                                                stationUpdate.getLocation(),
                                                stationUpdate.getChargerType(),
                                                stationUpdate.getChargingPoints(), 
                                                stationUpdate.getStatus());
            chargingStationService.updateChargingStation(updatedStation);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable String id){
        boolean exist = chargingStationService.existsById(id);
        ChargingStationDTO station= findChargingStation(id);
        if (exist && station.getStatus()==Status.Available) {
            chargingStationService.deleteStation(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/availables")
    public ResponseEntity<List<ChargingStationDTO>> findAllAvailables(){
        List<ChargingStationDTO> availableStations = chargingStationService.findWhereStatusAvailable();
        return ResponseEntity.ok(availableStations);
    }

    private ChargingStationDTO findChargingStation(String requestedId){
        return chargingStationService.findByAddressId(requestedId);
    }
}
