package com.gojek.api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class APIComparator {

    public static boolean jsonCompareObject(JSONObject jsonBaseObj, JSONObject jsonDumpObj) throws Exception {
        Iterator<String> itr = jsonBaseObj.keys();
        Set<String> keys = jsonDumpObj.keySet();
        while(itr.hasNext())
        {
            String key = itr.next();
            if(!jsonDumpObj.has(key))
                return false;
            if(jsonBaseObj.get(key) instanceof JSONArray)
            {
                JSONArray arr1 = (JSONArray) jsonBaseObj.get(key);
                JSONArray arr2 = (JSONArray) jsonDumpObj.get(key);
                if(arr1.length()!=arr2.length())
                    return false;
                int l=arr2.length();
                for (int i = 0; i < arr1.length(); i++)
                {
                    JSONObject baseObj = (JSONObject) arr1.get(i);
                    int j=0;
                    JSONObject dumpObj = (JSONObject) arr2.get(j);
                    while(!jsonCompareObject(baseObj, dumpObj) && j<arr2.length()-1) {
                        dumpObj = (JSONObject) arr2.get(++j);
                        l--;
                    }
                }
                System.out.println(l + " " + arr2.length());
            }
            else if (jsonBaseObj.get(key) instanceof JSONObject) {
                JSONObject baseObj = (JSONObject) jsonBaseObj.get(key);
                JSONObject dumpObj = (JSONObject) jsonDumpObj.get(key);
                if(!jsonCompareObject(baseObj, dumpObj)) {
                    return false;
                }
            } else {
                Object baseVal = jsonBaseObj.get(key);
                Object newVal = jsonDumpObj.get(key);
                if(!baseVal.equals(newVal))
                    return false;
            }
            //jsonDumpObj.remove(key);
        }
        if(!itr.hasNext()) {
            Set<String> keys1 = jsonDumpObj.keySet();
            List<String> keyList = new ArrayList<>();
            keyList.addAll(keys1);
            for (int k = 0; k < keyList.size(); k++) {
                jsonDumpObj.remove(keyList.get(k));
            }
        }

        if(jsonDumpObj.length()!=0)
            return false;
        return true;

    }

}
