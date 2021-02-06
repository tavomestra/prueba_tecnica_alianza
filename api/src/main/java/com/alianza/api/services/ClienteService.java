/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alianza.api.services;

import java.util.List;
import com.alianza.api.dto.ClienteRq;
import com.alianza.api.dto.ClienteRs;
import com.alianza.api.model.exceptions.ApiException;
/**
 *
 * @author Gustavo Mestra Garay <gmestra@asesoftware.com>
 */
public interface ClienteService {
    
    /**
     * Obtener todos los registros.
     * @return lista de clientes.
     */
    List<ClienteRs> getAll();
    
    /**
     * Obtener cliente por id.
     * @param id id
     * @return Cliente
     * @throws com.alianza.api.model.exceptions.ApiException
     */
    ClienteRs findById(String id) throws ApiException;
    
    /**
     * Guardar cliente en bd.
     * @param cliente cliente
     * @throws ApiException 
     */
    void save(ClienteRq cliente) throws ApiException;
    
    /**
     * Busqueda por filtros.
     * @param cliente
     * @return
     * @throws ApiException 
     */
    List<ClienteRs> findByFilters(ClienteRq cliente) throws ApiException;
    
    
}
