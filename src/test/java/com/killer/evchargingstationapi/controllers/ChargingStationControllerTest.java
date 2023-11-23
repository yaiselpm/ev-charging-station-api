package com.killer.evchargingstationapi.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.securityContext;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.killer.evchargingstationapi.domain.ChargerType;
import com.killer.evchargingstationapi.domain.ChargingStation;
import com.killer.evchargingstationapi.domain.Location;
import com.killer.evchargingstationapi.domain.Status;
import com.killer.evchargingstationapi.services.ChargingStationService;
import com.killer.evchargingstationapi.services.dtos.ChargingStationDTO;

// import lombok.With;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

import java.util.ArrayList;
import java.util.List;

// import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ChargingStationController.class)
@AutoConfigureMockMvc(addFilters = false)
// @WithMockUser(value = "operator")
public class ChargingStationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private ChargingStationService stationService;
    
    String content = "{\"chargerType\":\"AC\",\"status\":\"Available\"}";

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders
                    .webAppContextSetup(context)
                    
                    .apply(springSecurity())
                    .build();
    }

    // @WithMockUser(username = "operator", password = "abc123")
    @Test    
    void testCreateChargingStation() throws Exception {
        Location location= new Location("ds",23231l,143534l);
        ChargingStation chargingStation = new ChargingStation(location, ChargerType.AC,Status.Available);      
        Mockito.when(stationService.createChargingStation(Mockito.any(ChargingStation.class))).thenReturn(chargingStation);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                        .post("/stations")                        
                        .with(httpBasic("operator", "abc123"))                        
                        .header("Authorization", "Basic b3BlcmF0b3I6YWJjMTIz")
                        .accept(MediaType.APPLICATION_JSON).content(content)
                        .contentType(MediaType.APPLICATION_JSON);
        
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        

    }

    @Test
    void testDeleteStation() throws Exception {
        String id = "stn102";
        Mockito.doNothing().when(stationService).deleteStation(id);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                        .delete("/stations/{id}",id)
                        .with(httpBasic("operator", "abc123"));
                        // .header("Authorization", "Basic b3BlcmF0b3I6YWJjMTIz")
                        // .accept(MediaType.APPLICATION_JSON).content(content)
                        // .contentType(MediaType.APPLICATION_JSON);
        
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    @Test
    void testFindAllAvailables() {

    }

    @Test
    void testFindAllChargingStation() throws Exception {
        List<ChargingStationDTO> stationDTOs= new ArrayList<ChargingStationDTO>();
        ChargingStationDTO stationDTO= new ChargingStationDTO();
        stationDTO.setId("stn2");
        stationDTO.setPowerLevel(200);
        stationDTOs.add(stationDTO);
        when(stationService.findAll()).thenReturn(stationDTOs);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                        .get("/stations");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(200, response.getStatus());
    }

    @Test
    void testFindByAddressId() {
        
    }

    @Test
    void testUpdateChargingStation() {

    }
}
