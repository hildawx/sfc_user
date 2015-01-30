/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambimmort.sfc.service;

import com.ambimmort.sfc.util.Config;
import com.ambimmort.sfc.util.RestClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Administrator
 */
public class IPv6SpeedService {

    public boolean setConfig(String pId, String config) {
        try {
            String resp = RestClient.getInstance().post("http://" + Config.getString("dpi.host") + "/gn/dpi/ipv6/speedup/json", config);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(IPv6SpeedService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public JSONArray getProtoList() {
        JSONArray rs = new JSONArray();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(IPv6SpeedService.class.getClassLoader().getResourceAsStream("proto.list"), "utf-8"));
            String line = null;
            while((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    JSONObject ob = new JSONObject();
                    ob.put("value", data[1]);
                    ob.put("name", data[2]);
                    rs.add(ob);
                } 
            }
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(IPv6SpeedService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IPv6SpeedService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(IPv6SpeedService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rs;
    }
    
}
