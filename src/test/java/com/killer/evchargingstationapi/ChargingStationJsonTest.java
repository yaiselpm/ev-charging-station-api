package com.killer.evchargingstationapi;

import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import com.killer.evchargingstationapi.domain.ChargerType;
import com.killer.evchargingstationapi.domain.ChargingStation;
import com.killer.evchargingstationapi.domain.Location;
import com.killer.evchargingstationapi.domain.Status;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class ChargingStationJsonTest {
    
    @Autowired
    private JacksonTester<ChargingStation> json;

    private JacksonTester<ChargingStation[]> jsonList;
    
    private ChargingStation[] chargingStations;

    @BeforeEach
    void setUp(){
        Location location= new Location("Station1Address", 123456l,324654l);
        chargingStations =Arrays.array(
             new ChargingStation("station1", location , ChargerType.AC, null, Status.Available));
    }


    @Test
    public void chargingStatinSerializationTest() throws IOException {
        ChargingStation charging = chargingStations[0];
        assertThat(json.write(charging)).isStrictlyEqualToJson("single.json");
        assertThat(json.write(charging)).hasJsonPathStringValue("@.id");
        assertThat(json.write(charging)).extractingJsonPathStringValue("@.id")
                .isEqualTo("station1");
        // assertThat(json.write(charging)).hasJsonPathNumberValue("@.amount");
        // assertThat(json.write(charging)).extractingJsonPathNumberValue("@.amount")
        //         .isEqualTo(123.45);
    }

}
