package com.killer.evchargingstationapi.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.killer.evchargingstationapi.domain.ChargerType;
import com.killer.evchargingstationapi.domain.ChargingPoint;
import com.killer.evchargingstationapi.domain.ChargingStation;
import com.killer.evchargingstationapi.domain.Location;
import com.killer.evchargingstationapi.domain.Status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChargingStationsRepositoryTest {
    
    @Autowired
    private ChargingStationRepository chargingStationRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CharginPointRepository chargingPointRepository;
   


   @Test
   @DisplayName("Should Insert new Station")
   public void saveShouldInsertNewStation() {
        // Given
        Location location= new Location("Station4Address",123456l,123456l);
        ChargingStation chargingStation = new ChargingStation();
        ChargingPoint chargingPoint= new ChargingPoint();
        chargingPoint.setPowerLevel(200.00);
        chargingPoint.setInUse(false);
        List<ChargingPoint> list = new ArrayList<ChargingPoint>();
        Location persistedLocation = locationRepository.save(location);
        
        chargingStation.setLocation(persistedLocation);
        chargingStation.setChargerType(ChargerType.AC);
        chargingStation.setStatus(Status.Available);
        // When
        ChargingStation persistedStation = chargingStationRepository.save(chargingStation);
        chargingPoint.setChargingStation(persistedStation);
        list.add(chargingPoint);
        List<ChargingPoint>presistedChargingPoint = chargingPointRepository.saveAll(list);

        chargingStation.setChargingPoints(list);
        // Then
        assertNotNull(persistedStation);
        assertEquals("stn105", persistedStation.getId());
    }

    @Test
    @DisplayName("Should return a Station with a know Id")
    public void shouldReturnAStationWithAKnowId() {
        // Given
        ChargingStation chargingStation = chargingStationRepository.getReferenceById("stn101");
                
        // Then        
        assertEquals("stn101", chargingStation.getId());
    }

    @Test
    @DisplayName("Should return all Stations")
    public void shouldReturnAllStations() {
        // Given
        List<ChargingStation> chargingStation = chargingStationRepository.findAll();
                
        // Then        
        assertEquals(5, chargingStation.size());
    }

    @Test
    @DisplayName("Should return all Availables Stations")
    public void shouldReturnAllStationsAvailables() {
        // Given
        List<ChargingStation> chargingStation = chargingStationRepository.findByStatus(Status.Available);
                
        // Then        
        assertEquals(3, chargingStation.size());
    }

    @Test
    @DisplayName("Should update an existing  Station")
    public void shouldUpdateAnExistingStation(){
        Location location = new Location(1l, null, null, null);
        ChargingStation chargingStation = new ChargingStation("stn102",location, ChargerType.AC,Status.In_Use);
        boolean exist = chargingStationRepository.existsById(chargingStation.getId());
        if (exist) {
            chargingStationRepository.save(chargingStation);
        }
        assertEquals(true, exist);
    }
}
