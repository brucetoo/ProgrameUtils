package com.bruce.News.utils;

import com.google.gson.Gson;

/**
 * Created by bruce-too
 * on 2014/10/15
 * Time 15:17.
 */
public class GsonUtils {

    public static<T> T jsonToBean(String json,Class<T> clz){
        Gson gson = new Gson();
         T t = gson.fromJson(json,clz);
        return t;
    }

    public static String createGsonString(Object object) {
        Gson gson = new Gson();
        String gsonString = gson.toJson(object);
        return gsonString;
    }
}
