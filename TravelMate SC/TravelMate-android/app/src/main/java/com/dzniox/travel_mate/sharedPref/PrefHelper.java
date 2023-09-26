package com.dzniox.travel_mate.sharedPref;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.dzniox.travel_mate.utils.Util;


public class PrefHelper {

    private static final String IS_STREAMER ="is_streamer" ;
    private static final String VIDEO_URL = "live_video_url";
    private SharedPreferences shared_prefs;
    private static PrefHelper helper;


    public static void setUserLoggedIn(Context context, int id, String token, String loginType, String email, String name, String mobile, String about, String picture, String dob) {
        PrefUtils preferences = PrefUtils.getInstance(context);
        PrefUtils.getInstance(context).setLoggedIn(true);
        preferences.setValue(PrefKeys.IS_LOGGED_IN, true);
        preferences.setValue(PrefKeys.USER_ID, id);
        Log.e("Token",token);
        preferences.setValue(PrefKeys.SESSION_TOKEN, token);
        preferences.setValue(PrefKeys.LOGIN_BY, loginType);
        preferences.setValue(PrefKeys.USER_EMAIL, email);
        preferences.setValue(PrefKeys.USER_NAME, name);
        preferences.setValue(PrefKeys.USER_MOBILE, mobile);
        preferences.setValue(PrefKeys.USER_ABOUT, about);
        preferences.setValue(PrefKeys.USER_PICTURE, picture);
        preferences.setValue(PrefKeys.DOB, dob);
    }

    public static void setUserLoggedOut(Context context) {
        PrefUtils preferences = PrefUtils.getInstance(context);
        PrefUtils.getInstance(context).setLoggedIn(false);
        preferences.removeKey(PrefKeys.USER_ID);
        preferences.removeKey(PrefKeys.SESSION_TOKEN);
        preferences.removeKey(PrefKeys.LOGIN_BY);
        preferences.removeKey(PrefKeys.USER_EMAIL);
        preferences.removeKey(PrefKeys.USER_NAME);
        preferences.removeKey(PrefKeys.USER_MOBILE);
        preferences.removeKey(PrefKeys.USER_ABOUT);
        preferences.removeKey(PrefKeys.USER_PICTURE);
        preferences.removeKey(PrefKeys.DOB);
    }


    private PrefHelper(Context context) {
        shared_prefs = context.getSharedPreferences(Util.PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void initPrefHelper(Context context) {
        if (helper == null) {
            helper = new PrefHelper(context);
        }
    }
    public static PrefHelper getInstance() {
        return helper;
    }


    public void setis_streamer(String is_streamer) {
        SharedPreferences.Editor edit = shared_prefs.edit();
        edit.putString(IS_STREAMER, is_streamer);
        edit.apply();
    }

    public String getis_Streamer() {
        return shared_prefs.getString(IS_STREAMER, "");
    }

    public void setlivevideoUrl(String videourl){
        SharedPreferences.Editor edit = shared_prefs.edit();
        edit.putString(VIDEO_URL, videourl);
        edit.apply();

    }

    public String getVideoUrl(){
        return shared_prefs.getString(VIDEO_URL, "");
    }

}
