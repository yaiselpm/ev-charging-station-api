package com.killer.evchargingstationapi;

import java.net.URI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.killer.evchargingstationapi.domain.ChargerType;
import com.killer.evchargingstationapi.domain.ChargingPoint;
import com.killer.evchargingstationapi.domain.ChargingStation;
import com.killer.evchargingstationapi.domain.Location;
import com.killer.evchargingstationapi.domain.Status;
import com.killer.evchargingstationapi.repositories.ChargingStationRepository;
import com.killer.evchargingstationapi.repositories.LocationRepository;

import net.minidev.json.JSONArray;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EvChargingStationApiApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

    @Autowired
    private ChargingStationRepository chargingStationRepository;
    
    @Autowired
    private LocationRepository locationRepository;

	@Test
    @DirtiesContext
    void shouldCreateANewChargingStation() {
		Location location= new Location("Station2Address", 123456l,324654l);
		// ChargingPoint chargingPoint = new ChargingPoint(1l,32, false,);
		
        ChargingStation newChargingStation = new ChargingStation();
        newChargingStation.setChargerType(ChargerType.AC);
        newChargingStation.setStatus(Status.Available);
        ResponseEntity<Void> createResponse = restTemplate
                .withBasicAuth("operator", "abc123")
                .postForEntity("/stations", newChargingStation, Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // URI locationOfNewStation = createResponse.getHeaders().getLocation();
        // ResponseEntity<String> getResponse = restTemplate
        //         .withBasicAuth("operator", "abc123")
        //         .getForEntity(locationOfNewStation, String.class);
        // assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        // DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        // String id = documentContext.read("$.id");
        // // Double amount = documentContext.read("$.amount");

        // assertThat(id).isNotNull();
        // // assertThat(amount).isEqualTo(250.00);
    }

	@Test
    void shouldReturnAllChargingStationsWhenListIsRequested() {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("operator", "abc123")
                .getForEntity("/stations", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        
    }
	
	@Test
    void shouldReturnAllStationsAvailables() {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("operator", "abc123")
                .getForEntity("/stations/availables", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);        
    }

    @Test
    void shouldNotReturnAStationWithAnUnknownId() {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("operator", "abc123")
                .getForEntity("/stations/stn1000", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();
    }
    
	@Test
	void contextLoads() {
	}

}
