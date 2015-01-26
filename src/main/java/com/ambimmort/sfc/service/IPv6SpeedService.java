/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambimmort.sfc.service;

import com.ambimmort.sfc.util.Config;
import com.ambimmort.sfc.util.RestClient;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class IPv6SpeedService {

    public boolean setConfig(String pId, String config) {
        try {
            String resp = RestClient.getInstance().post("http://" + Config.getString("classifier.host") + "/gn/dpi/ipv6/speedup/json", config);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(IPv6SpeedService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
