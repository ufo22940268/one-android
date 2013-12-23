/*
 * Parser.java
 * Copyright (C) 2013 ccheng <ccheng@cchengs-MacBook-Air.local>
 *
 * Distributed under terms of the MIT license.
 */
package me.biubiubiu.one.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Parser {

    public static List<Map<String, String>> items(String resp) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
            JSONObject jo = new JSONObject(resp);
            JSONArray items = jo.optJSONArray("result");
            if (items != null) {
                for (int i = 0; i < items.length(); i ++)  {
                    JSONObject item = items.optJSONObject(i);
                    Map<String, String> map = getItemMap(item);
                    list.add(map);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static Map<String, String> item(String resp) {
        try {
            JSONObject jo = new JSONObject(resp);
            JSONObject item = jo.optJSONObject("result");
            if (item != null) {
                Map<String, String> map = getItemMap(item);
                return map;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Map<String, String> getItemMap(JSONObject item) {
        Map<String, String> map = new HashMap<String, String>();
        Iterator iter = item.keys();
        String key;
        while(iter.hasNext()) {
            key = (String)iter.next();
            if (key.equals("user")) {
                //TODO move the whole sub node to the root node and then don't set subnode in
                //the responsed json structure.
                JSONObject user = item.optJSONObject(key);
                String value = user.optString("image_url");
                map.put("image_url", value);
                map.put("user_nickname", user.optString("nickname"));
            } else {
                String value = item.optString(key);
                map.put(key, value);
            }
        }
        return map;
    }
}
