/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alianza.api.repository;

import com.alianza.api.model.Cliente;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 *
 * @author Gustavo Mestra Garay <gmestra@asesoftware.com>
 */
@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    @DisplayName("Test findAll from repository")
    public void testFindAll(TestInfo info) {
        
        System.out.println("Ejecucion test "+info.getDisplayName());
        
        List<Cliente> items = clienteRepository.findAll();
        Assertions.assertEquals(1, items.size());
    }

}
