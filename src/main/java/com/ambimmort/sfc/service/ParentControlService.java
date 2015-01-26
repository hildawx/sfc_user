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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Administrator
 */
public class ParentControlService {

    public boolean addConfig(String pId, String type, String address, String urlList, String appList, String online_time) {
        JSONObject data = new JSONObject();
        data.put("type", type);
        data.put("address", address);
        data.put("url_list", JSONArray.fromObject(urlList));
        data.put("app_list", JSONArray.fromObject(appList));
        data.put("online_time", online_time);
        
        try {
            RestClient.getInstance().post("http;//" + Config.getString("classifier.host") + "/gn/fw/parent/control/json", data.toString());
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ParentControlService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
