package com.Utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class RestApiHelper {

    private static Logger log = LogManager.getLogger(RestApiHelper.class.getName());

    private static HashMap<String, String> CurrentHeader = new HashMap<>();

    public static HttpResponse HttpGet(String apiUrl, boolean setHeader) {
        HttpClient httpClient;
        httpClient = HttpClientBuilder.create().build();
        LogHelper.debug(log, "API Call Type: GET, URL: " + apiUrl);

        try {
            HttpGet request = new HttpGet(apiUrl);
            if (setHeader) {
                Set set = CurrentHeader.entrySet();
                for (Object aSet : set) {
                    Map.Entry mapEntry = (Map.Entry) aSet;
                    if (mapEntry.getValue() == null)
                        request.addHeader(mapEntry.getKey().toString(), null);
                    else
                        request.addHeader(mapEntry.getKey().toString(), mapEntry.getValue().toString());
                }
            }
            return httpClient.execute(request);
        } catch (Exception ex) {
            LogHelper.error(log, ex.getMessage());
            return null;
        }
    }

    public static HttpResponse HttpPatch(String apiUrl, boolean setHeader, String apiBody) {

        HttpClient httpClient;
        httpClient = HttpClientBuilder.create().build();

        LogHelper.debug(log, "API Call Type: PUT, URL: " + apiUrl + ", Body: " + apiBody);

        try {
            HttpPatch request = new HttpPatch(apiUrl);
            StringEntity params = new StringEntity(apiBody);
            params.setContentType("application/json");
            request.setEntity(params);

            if (setHeader) {
                Set set = CurrentHeader.entrySet();
                for (Object aSet : set) {
                    Map.Entry mapEntry = (Map.Entry) aSet;
                    if (mapEntry.getValue() == null)
                        request.addHeader(mapEntry.getKey().toString(), null);
                    else
                        request.addHeader(mapEntry.getKey().toString(), mapEntry.getValue().toString());
                }
            }
            return httpClient.execute(request);
        } catch (Exception ex) {
            LogHelper.error(log, ex.getMessage());
            return null;
        }
    }


    public static void SetHeader(String key, String value) {
        CurrentHeader.put(key, value);
    }

    public static void SetDefaultHeader() {
        ClearHeader();
        SetHeader("Content-Type", "application/json");
        SetHeader("Accept", "application/json");
    }


    public static void SetDefaultHeaderWithAuthenticationToken(String authToken) {
        SetDefaultHeader();
        SetAuthenticationToken(authToken);
    }

    public static void SetAuthenticationToken(String authToken) {
        SetHeader("Authorization", "Bearer "+authToken);
    }


    public static void ClearHeader() {
        CurrentHeader = new HashMap<>();
    }
}
