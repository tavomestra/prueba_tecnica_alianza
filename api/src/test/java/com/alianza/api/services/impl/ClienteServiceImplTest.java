/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alianza.api.services.impl;

import com.alianza.api.dto.ClienteRq;
import com.alianza.api.dto.ClienteRs;
import com.alianza.api.model.Cliente;
import com.alianza.api.repository.ClienteRepository;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Gustavo Mestra Garay <gmestra@asesoftware.com>
 */
@ExtendWith(MockitoExtension.class)
public class ClienteServiceImplTest {

    @InjectMocks
    private ClienteServiceImpl business;

    @Mock
    private ClienteRepository itemRepository;

    @Test
    @DisplayName("Test impl getAll")
    public void getAll(TestInfo info) {
        
        System.out.println("Ejecucion test "+info.getDisplayName());
        
        when(itemRepository.findAll()).thenReturn(Arrays.asList(
                new Cliente("gmestra", "gustavo",
                        "tavo@gmail.com", "3145357861", LocalDate.now(), LocalDate.now()),
                new Cliente("jmestra", "jeremy",
                        "jeremy@gmail.com", "3145357861", LocalDate.now(), LocalDate.now())
        ));

        List<ClienteRs> items = business.getAll();

        assertEquals("gmestra", items.get(0).getSharedKey());
        assertEquals("jmestra", items.get(1).getSharedKey());

    }

}
