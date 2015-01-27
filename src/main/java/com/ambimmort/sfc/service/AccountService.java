/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambimmort.sfc.service;

import com.ambimmort.sfc.util.Config;
import com.ambimmort.sfc.util.RestClient;
import java.io.IOException;
import java.util.Iterator;
import net.sf.json.JSONArray;
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
        JSONObject receiveObj = JSONObject.fromObject(resp);
        JSONObject returnObj = new JSONObject();
        returnObj.put("status", receiveObj.getString("status"));
        returnObj.put("id", receiveObj.getInt("roleid"));
        return returnObj;
    }

    public JSONArray getChildAccount(String parentID) throws IOException {
        String resp = RestClient.getInstance().get("http://" + Config.getString("classifier.host") + "/gn/classifier/user/info/json&pid="+ parentID);
        JSONObject obj = JSONObject.fromObject(resp);
        JSONArray arr = obj.getJSONArray("account");
        Iterator<JSONObject> it = arr.iterator();
        JSONArray returnArr = new JSONArray();
        while(it.hasNext()) {
            JSONObject o = it.next();
            JSONArray item = new JSONArray();
            item.add(o.getString("username"));
            item.add(o.getString("passwd"));
            returnArr.add(item);
        }
        return returnArr;
    }

    public JSONObject getAccountInfo(String id, String role) throws IOException {
        String resp = RestClient.getInstance().get("http://" + Config.getString("classifier.host") + "/gn/classifier/user/info/json");
        JSONObject obj = JSONObject.fromObject(resp);
        JSONArray tmpArr = obj.getJSONArray("user");
        if (tmpArr.size() > 0) {
            return tmpArr.getJSONObject(0);
        } else {
            return null;
        }
    }

    public boolean addChildrenAccount(String parentId, String childName, String childPassword) throws IOException {
        JSONObject sendObj = new JSONObject();
        sendObj.put("pid", parentId);
        sendObj.put("username", childName);
        sendObj.put("passwd", childPassword);
        String resp = RestClient.getInstance().post("http://"+ Config.getString("classifier.host") + "/gn/classifier/account/config/child/json", sendObj.toString());
        JSONObject rsJSON = JSONObject.fromObject(resp);
        if ("success".equals(rsJSON.getString("status"))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteChildrenAccount(String parentId, String childName, String childPasswd) throws IOException {
        JSONObject sendObj = new JSONObject();
        sendObj.put("pid", parentId);
        sendObj.put("username", childName);
        sendObj.put("passwd", childPasswd);
        String resp = RestClient.getInstance().delete("http://" + Config.getString("classifier.host") + "/gn/classifier/account/config/child/json", sendObj.toString());
        JSONObject rsJSON = JSONObject.fromObject(resp);
        if ("success".equals(rsJSON.getString("status"))) {
            return true;
        } else {
            return false;
        }
    }
    
}
