/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambimmort.sfc.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Config {
    private static Properties properties = new Properties();
    
    static {
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(Config.class.getClassLoader().getResourceAsStream("sfc.properties"), "utf-8");
            properties.load(isr);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static String getString(String key) {
        return properties.getProperty(key);
    }
    
    public static int getInt(String key) {
        return Integer.parseInt(key);
    }
}
