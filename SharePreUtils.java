package com.bruce.News.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by bruce-too
 * on 2014/10/15
 * Time 15:40.
 */
public class SharePreUtils {

    public static SharedPreferences preferences;
    public static String PreName = "CONFIG";

    public interface KEY {

        String FUNCTION_ALL_JSON = "all_function_json";//所有的Funcation Json
        String FUNCTION_SELECTED_ID = "selcted_function_ids";//选中的function ids

        String CATE_ALL_JSON = "all_cate_json";//所有的新闻目录 Json
        String CATE_SELECTED_JSON = "selcted_cate_json";//选中的新闻目录ids
        String CATE_EXTEND_ID = "extend_cate_ids";//推荐的新闻 目录ids

        String VOTE_SELECTED_ID = "selcted_vote_ids";//选中的function ids
    }


    public static void saveString(Context ct, String key, String value) {
        if (preferences == null) {
            preferences = ct.getSharedPreferences(PreName, ct.MODE_PRIVATE);
        }
        preferences.edit().putString(key, value).commit();
    }

    public static String getString(Context ct,String key) {
        if (preferences == null) {
            preferences = ct.getSharedPreferences(PreName, ct.MODE_PRIVATE);
        }
        return preferences.getString(key, "");
    }
}
