/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alianza.api.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Asus
 */
@Entity
public class Cliente implements Serializable {

    @Id
    private String sharedKey;

    private String businessId;

    private String email;

    private String phone;

    private LocalDate startDate;
    
    private LocalDate endDate;

    public Cliente(String sharedKey, String businessId, String email, 
            String phone, LocalDate startDate, LocalDate endDate) {
        this.sharedKey = sharedKey;
        this.businessId = businessId;
        this.email = email;
        this.phone = phone;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    

    public Cliente() {
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

   

    @Override
    public String toString() {
        return String.format("Cliente[%s, %s, %s, %s, %s, %s]", this.sharedKey,
                this.businessId, this.email, this.phone, this.startDate, this.endDate);
    }

}
