package com.killer.evchargingstationapi.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.killer.evchargingstationapi.domain.ChargerType;
import com.killer.evchargingstationapi.domain.ChargingStation;
import com.killer.evchargingstationapi.domain.Status;
import com.killer.evchargingstationapi.repositories.ChargingStationRepository;
import com.killer.evchargingstationapi.services.dtos.ChargingStationDTO;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChargingStationsServicesTest {
    
    @MockBean
    @Autowired
    private ChargingStationRepository chargingStationRepository = mock(ChargingStationRepository.class);

    @InjectMocks
    @Autowired
    private ChargingStationService chargingStationService;

    @BeforeEach
    public void setup(){
        chargingStationService= new ChargingStationService(chargingStationRepository, null);
    }
    @Test
    public void shouldReturnAllStations(){
        // Given
        ChargingStation chargingStation = new ChargingStation();
        chargingStation.setId("stn110");
        chargingStation.setChargerType(ChargerType.DC_FAST);
        chargingStation.setStatus(Status.Available);
        List<ChargingStation> chargingStations = new ArrayList<ChargingStation>();
        // chargingStations.add(chargingStation);
        // When
        when(chargingStationRepository.findAll()).thenReturn(chargingStations);
        List<ChargingStationDTO> stations = chargingStationService.findAll();

        // Then
        assertEquals(5,stations.size());
        verify(this.chargingStationRepository).findAll();

    }
    

}
