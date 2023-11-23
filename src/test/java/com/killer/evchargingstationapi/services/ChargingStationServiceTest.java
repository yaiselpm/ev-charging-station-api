package com.killer.evchargingstationapi.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.killer.evchargingstationapi.domain.ChargerType;
import com.killer.evchargingstationapi.domain.ChargingStation;
import com.killer.evchargingstationapi.domain.Status;
import com.killer.evchargingstationapi.repositories.ChargingStationRepository;

@ExtendWith(MockitoExtension.class)
public class ChargingStationServiceTest {

    @MockBean
    private ChargingStationRepository stationRepository;

    @InjectMocks
    private ChargingStationService stationService;

    @Test
    void testCreateChargingStation() {

    }

    @Test
    void testDeleteStation() {

    }

    @Test
    void testExistsById() {

    }

    @Test
    void testFindAll() {
         ChargingStation chargingStation = new ChargingStation();
        chargingStation.setId("stn110");
        chargingStation.setChargerType(ChargerType.DC_FAST);
        chargingStation.setStatus(Status.Available);
        List<ChargingStation> chargingStations = new ArrayList<ChargingStation>();
        when(stationRepository.findAll()).thenReturn(chargingStations);
        assertEquals(5, chargingStations.size());
    }

    @Test
    void testFindByAddressId() {

    }

    @Test
    void testFindWhereStatusAvailable() {

    }

    @Test
    void testUpdateChargingStation() {

    }
}
