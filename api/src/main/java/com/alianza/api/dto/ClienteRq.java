/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alianza.api.dto;


import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 *
 * @author Gustavo Mestra Garay <gmestra@asesoftware.com>
 */

public class ClienteRq implements Serializable{

    private String sharedKey;

    private String businessId;        
    
    @NotBlank(message = "name no puede ser vacio")
    @NotNull(message = "name es obligatorio")
    private String name;

    @NotBlank(message = "email no puede ser vacio")
    @NotNull(message = "email es obligatorio")
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "email inválido")
    private String email;

    @NotBlank(message = "phone no puede ser vacio")
    @NotNull(message = "phone es obligatorio")
    @Pattern(regexp = "^[0-9]{10}$", message = "Celular inválido")
    private String phone;

    
    @NotNull(message = "startDate es obligatorio")
    private LocalDate startDate;
    
    
    @NotNull(message = "endDate es obligatorio")
    private LocalDate endDate;

    public ClienteRq() {
    }

    public ClienteRq(String sharedKey, String businessId, String email, String phone, LocalDate startDate, LocalDate endDate) {
        this.sharedKey = sharedKey;
        this.businessId = businessId;
        this.email = email;
        this.phone = phone;
        this.startDate = startDate;
        this.endDate = endDate;
    }

   

    public String getSharedKey() {
        return sharedKey;
    }

    public void setSharedKey(String sharedKey) {
        this.sharedKey = sharedKey;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    
    
    

}
