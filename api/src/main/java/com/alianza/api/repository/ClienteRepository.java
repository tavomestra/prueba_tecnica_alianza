/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alianza.api.repository;

import com.alianza.api.model.Cliente;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gustavo Mestra Garay <gmestra@asesoftware.com>
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {

    @Query("SELECT c FROM Cliente c "
            + "where (:name is null or c.businessId like %:name%) "
            + "and (:email is null or c.email = :email)"
            + "and (:phone is null or c.phone = :phone)"
            + "and (:startDate is null or c.startDate = :startDate)"
            + "and (:endDate is null or c.endDate = :endDate)"
    )
    List<Cliente> findByFilters(String name, String email, String phone,
            LocalDate startDate, LocalDate endDate);

}
