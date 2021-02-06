/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alianza.api.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Gustavo Mestra Garay <gmestra@asesoftware.com>
 */
public class GenerarSharedKeyTest {
    
    public GenerarSharedKeyTest() {
    }

    @Test
    public void testSharedKey1() {
        String sharedKey = GenerarSharedKey.getSharedKey("gustavo mestra");        
        assertEquals("gmestra", sharedKey);
    }
    
    @Test
    public void testSharedKey2() {
        String sharedKey = GenerarSharedKey.getSharedKey("gustavo");        
        assertEquals("gustavo", sharedKey);
    }
    
}
