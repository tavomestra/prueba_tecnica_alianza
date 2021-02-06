/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alianza.api.controller;

import com.alianza.api.dto.ClienteRq;
import com.alianza.api.dto.ClienteRs;
import com.alianza.api.services.impl.ClienteServiceImpl;
import java.time.LocalDate;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Gustavo Mestra Garay <gmestra@asesoftware.com>
 */
@WebMvcTest(ClienteApiController.class)
public class ClienteApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteServiceImpl clienteService;

    /**
     * Test endponit get-all
     * @throws Exception 
     */
    @Test
    public void getAll() throws Exception {

        when(clienteService.getAll())
                .thenReturn(Arrays.asList(new ClienteRs("gmestra", "gustavo",
                                        "tavo@gmail.com", "3145357861", 
                                        LocalDate.now())
                        )
                );

        RequestBuilder request = MockMvcRequestBuilders
                .get("/cliente/get-all")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{sharedKey:gmestra}]"))
                .andReturn();
    }

}
