/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alianza.api.controller;

import com.alianza.api.dto.ClienteRq;
import com.alianza.api.dto.ClienteRs;
import com.alianza.api.model.exceptions.ApiException;
import com.alianza.api.model.exceptions.BadRequestException;
import com.alianza.api.model.exceptions.ErrorRq;
import com.alianza.api.model.exceptions.MessageExceptions;
import com.alianza.api.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gustavo Mestra Garay <gmestra@asesoftware.com>
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cliente")
@Api(tags = "cliente", value = "/cliente")
public class ClienteApiController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("get-all")
    @ApiOperation(value = "Obtener todos los clientes",
            response = ClienteRq.class,
            responseContainer = "List",
            httpMethod = "GET")
    public ResponseEntity<List<ClienteRs>> getAll(HttpServletRequest request) {
        return ResponseEntity.ok(clienteService.getAll());
    }

    @GetMapping(value = {"/get-by-sharedkey", "/get-by-sharedkey/{sharedKey}"})
    @ApiOperation(value = "Obtener cliente por id",
            response = ClienteRs.class,
            responseContainer = "Cliente",
            httpMethod = "GET")
    public ResponseEntity<ClienteRs> getAll(HttpServletRequest request,
            @PathVariable Optional<String> sharedKey) throws ApiException {
        if (!sharedKey.isPresent()) {
            throw new BadRequestException(
                    ErrorRq.getErrorRq(HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            MessageExceptions.SHARED_KEY_REQUIRED,
                            HttpStatus.BAD_REQUEST.value()));
        }
        return ResponseEntity.ok(clienteService.findById(sharedKey.get()));
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Crear cliente",
            httpMethod = "POST")
    public void doLogin(ServletRequest request,
            @RequestBody @Validated ClienteRq cliente) throws ApiException {
        clienteService.save(cliente);
    }

    @PostMapping("/get-by-filters")
    @ApiOperation(value = "Busqueda avanzada de clientes",
            response = ClienteRs.class,
            responseContainer = "Cliente",
            httpMethod = "POST")
    public ResponseEntity<List<ClienteRs>> getByFilters(HttpServletRequest request,
            @RequestBody ClienteRq cliente) throws ApiException {
        return ResponseEntity.ok(clienteService.findByFilters(cliente));
    }

}
