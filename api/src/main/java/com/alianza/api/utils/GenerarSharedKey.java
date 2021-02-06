/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alianza.api.utils;

/**
 *
 * @author Gustavo Mestra Garay <gmestra@asesoftware.com>
 */
public abstract class GenerarSharedKey {
    
    public static String getSharedKey(String name){
        
        String[] names = name.split("\\s+");
        
        if(names.length == 1){
            return names[0].toLowerCase();
        }
        
        return (names[0].charAt(0) + names[1]).toLowerCase();
    }
    
}
