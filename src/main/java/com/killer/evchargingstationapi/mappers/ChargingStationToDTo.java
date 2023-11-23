package com.killer.evchargingstationapi.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.killer.evchargingstationapi.domain.ChargingPoint;
import com.killer.evchargingstationapi.domain.ChargingStation;
import com.killer.evchargingstationapi.services.dtos.ChargingStationDTO;
@Component
public class ChargingStationToDTo implements IMapper<ChargingStation,ChargingStationDTO>{

    List<ChargingPoint> chargingPoints= new ArrayList<ChargingPoint>();
    ChargingStationDTO stationDTO = new ChargingStationDTO();
    @Override
    public ChargingStationDTO map(ChargingStation in) {
                            
        double powerLevel=powerLevel(in.getChargingPoints());
        stationDTO.setId(in.getId());
        stationDTO.setLocation(in.getLocation());
        stationDTO.setChargerType(in.getChargerType());
        stationDTO.setChargingPoints(in.getChargingPoints());
        stationDTO.setStatus(in.getStatus());        
        stationDTO.setPowerLevel(powerLevel);
        return stationDTO;
    }
    
    private double powerLevel(List<ChargingPoint> stationPoint){
        double result=0;
        if (stationPoint== null) {
            return 0;
        }
        if (stationPoint.size()==1) {
            return stationPoint.get(0).getPowerLevel();
        }
        for (ChargingPoint chargingPoint : stationPoint) {
            chargingPoints.add(chargingPoint);
            result+=chargingPoint.getPowerLevel();
        }
        return result;
    }
}
