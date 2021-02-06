/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alianza.api.services.impl;

import com.alianza.api.dto.ClienteRq;
import com.alianza.api.dto.ClienteRs;
import com.alianza.api.model.Cliente;
import com.alianza.api.model.exceptions.ApiException;
import com.alianza.api.model.exceptions.BadRequestException;
import com.alianza.api.model.exceptions.ErrorRq;
import com.alianza.api.model.exceptions.MessageExceptions;
import com.alianza.api.repository.ClienteRepository;
import com.alianza.api.services.ClienteService;
import com.alianza.api.utils.GenerarSharedKey;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gustavo Mestra Garay <gmestra@asesoftware.com>
 */
@Slf4j
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<ClienteRs> getAll() {
        log.info("Se consulta todos los clientes.");

        return clienteRepository.findAll().stream().map(c -> {
            return new ClienteRs(c.getSharedKey(), c.getBusinessId(),
                    c.getEmail(), c.getPhone(), c.getStartDate());
        }).collect(Collectors.toList());

    }

    @Override
    public ClienteRs findById(String id) throws ApiException {

        log.info("Se consulta cliente por id {}", id);

        return clienteRepository.findById(id)
                .map(c -> {
                    return new ClienteRs(c.getSharedKey(), c.getBusinessId(),
                            c.getEmail(), c.getPhone(), c.getStartDate());
                }).orElseThrow(
                () -> new BadRequestException(
                        ErrorRq.getErrorRq(HttpStatus.BAD_REQUEST.getReasonPhrase(),
                                MessageExceptions.CLIENTE_NOT_FOUND,
                                HttpStatus.BAD_REQUEST.value())));
    }

    @Override
    public void save(ClienteRq clienteDTO) throws ApiException {

        String sharedKey = GenerarSharedKey.getSharedKey(clienteDTO.getName());

        Cliente cliente = new Cliente(sharedKey,
                clienteDTO.getName(),
                clienteDTO.getEmail(),
                clienteDTO.getPhone(),
                clienteDTO.getStartDate(),
                clienteDTO.getEndDate()
        );

        clienteRepository.save(cliente);
        log.info("Se crear cliente {}", cliente.toString());
    }

    @Override
    public List<ClienteRs> findByFilters(ClienteRq cl) throws ApiException {
        log.info("Se consulta todos los clientes por filtros.");
        
        if (cl.getName() != null && cl.getName().isEmpty()){
            cl.setName(null);
        }
        
        if (cl.getPhone() != null && cl.getPhone().isEmpty()){
            cl.setPhone(null);
        }
        
        if (cl.getEmail()!= null && cl.getEmail().isEmpty()){
            cl.setEmail(null);
        }

        return clienteRepository.findByFilters(cl.getName(), cl.getEmail(),
                cl.getPhone(), cl.getStartDate(), cl.getEndDate()).stream().map(c -> {
            return new ClienteRs(c.getSharedKey(), c.getBusinessId(),
                    c.getEmail(), c.getPhone(), c.getStartDate());
        }).collect(Collectors.toList());
    }

}
