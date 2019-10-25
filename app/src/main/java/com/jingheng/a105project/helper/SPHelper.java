package com.jingheng.a105project.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.jingheng.a105project.model.Person;

import org.json.JSONException;
import org.json.JSONObject;

public class SPHelper {

    /**
     * User
     **/
    private static final String FILE_PERSON = "person";
    private static final String PERSON = "person";

    public static void setUser(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_PERSON, Context.MODE_PRIVATE);
        sp.edit().putString(PERSON, Person.getInstance().getJSONObject().toString()).apply();
    }

    public static boolean getUser(Context context) {
        try {
            Person u = Person.getInstance();
            u.setJSONObject(new JSONObject(context.getSharedPreferences(FILE_PERSON, Context.MODE_PRIVATE).getString(PERSON, "")));
            return !u.getSex().isEmpty();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void clearUser(Context context) {
        context.getSharedPreferences(FILE_PERSON, Context.MODE_PRIVATE).edit().clear().apply();
    }

    /**
     * LocationService
     **/
    private static final String FILE_SERVICE = "service";
    private static final String SERVICE_IS_START = "isStart";

    public static void setServiceStatus(Context context, boolean start) {
        SharedPreferences sp = context.getSharedPreferences(FILE_SERVICE, Context.MODE_PRIVATE);
        sp.edit().putBoolean(SERVICE_IS_START, start).apply();
    }

    public static boolean getServiceStatus(Context context) {
        return context.getSharedPreferences(FILE_SERVICE, Context.MODE_PRIVATE).getBoolean(SERVICE_IS_START, false);
    }
}

