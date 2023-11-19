package com.killer.evchargingstationapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import com.killer.evchargingstationapi.domain.ChargingStation;

@JsonTest
public class ChargingStationJsonTest {
    
    @Autowired
    private JacksonTester<ChargingStation> json;

    @Autowired
    private JacksonTester<ChargingStation[]> jsonList;
    

}
