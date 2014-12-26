/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambimmort.sfc.service;

import com.ambimmort.sfc.util.Config;
import com.ambimmort.sfc.util.RestClient;
import java.io.IOException;
import net.sf.json.JSONObject;

/**
 *
 * @author Administrator
 */
public class AccountService {

    public JSONObject login(String username, String password, String userIP) throws IOException {
        JSONObject sendObj = new JSONObject();
        sendObj.put("IP", userIP);
        sendObj.put("username", username);
        sendObj.put("passwd", password);
        String resp = RestClient.getInstance().post("http://" + Config.getString("classifier.host") + "/gn/classifier/portal/auth/json", sendObj.toString());
        return JSONObject.fromObject(resp);
    }
    
}
