/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambimmort.sfc.service;

import com.ambimmort.sfc.util.RestClient;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class OFFLineDownloadService {

    public boolean addDownload(String storeHost, String config) {
        StringBuilder sb = new StringBuilder();
        sb.append("http://").append(storeHost).append("/gn/storage/remote/download/json");
        try {
            RestClient.getInstance().post(sb.toString(), config);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(OFFLineDownloadService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
