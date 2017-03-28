package com.aavjaav.qd.aavjaav.model.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefHelper {
    private static final String SETTINGS_NAME = "default_settings";
    private static SharedPrefHelper sSharedPrefs;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private boolean mBulkUpdate = false;

    public static class Key {
        public static final String IS_FIRST_LAUNCH = "is_app_first_time_started";
        public static final String IS_LOGGED = "is_logged";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String EMAIL_ADDRESS = "email_address";
        public static final String MOBILE_NUMBER = "mobile_number";
        public static final String TOKEN = "login_token";
        public static final String TOKEN_CREATED = "token_created";
        public static final String USER_ID = "user_id";


    }

    private SharedPrefHelper(Context context) {
        mPref = context.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE);
    }


    public static SharedPrefHelper getInstance(Context context) {
        if (sSharedPrefs == null) {
            sSharedPrefs = new SharedPrefHelper(context.getApplicationContext());
        }
        return sSharedPrefs;
    }

    public static SharedPrefHelper getInstance() {
        if (sSharedPrefs != null) {
            return sSharedPrefs;
        }
        throw new IllegalArgumentException("Should use getInstance(Context) at least once before using this method.");
    }

    public void put(String key, String val) {
        doEdit();
        mEditor.putString(key, val);
        doCommit();
    }

    public void put(String key, int val) {
        doEdit();
        mEditor.putInt(key, val);
        doCommit();
    }

    public void put(String key, boolean val) {
        doEdit();
        mEditor.putBoolean(key, val);
        doCommit();
    }

    public void put(String key, float val) {
        doEdit();
        mEditor.putFloat(key, val);
        doCommit();
    }

    public void put(String key, double val) {
        doEdit();
        mEditor.putString(key, String.valueOf(val));
        doCommit();
    }

    public void put(String key, long val) {
        doEdit();
        mEditor.putLong(key, val);
        doCommit();
    }

    public String getString(String key, String defaultValue) {
        return mPref.getString(key, defaultValue);
    }

    public String getString(String key) {
        return mPref.getString(key, null);
    }

    public int getInt(String key) {
        return mPref.getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return mPref.getInt(key, defaultValue);
    }

    public long getLong(String key) {
        return mPref.getLong(key, 0);
    }

    public long getLong(String key, long defaultValue) {
        return mPref.getLong(key, defaultValue);
    }

    public float getFloat(String key) {
        return mPref.getFloat(key, 0);
    }

    public float getFloat(String key, float defaultValue) {
        return mPref.getFloat(key, defaultValue);
    }

    public double getDouble(String key) {
        return getDouble(key, 0);
    }

    public double getDouble(String key, double defaultValue) {
        try {
            return Double.valueOf(mPref.getString(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return mPref.getBoolean(key, defaultValue);
    }

    public boolean getBoolean(String key) {
        return mPref.getBoolean(key, false);
    }

    public void remove(String... keys) {
        doEdit();
        for (String key : keys) {
            mEditor.remove(key);
        }
        doCommit();
    }

    public void clear() {
        doEdit();
        mEditor.clear();
        doCommit();
    }

    public void edit() {
        mBulkUpdate = true;
        mEditor = mPref.edit();
    }

    public void commit() {
        mBulkUpdate = false;
        mEditor.commit();
        mEditor = null;
    }

    private void doEdit() {
        if (!mBulkUpdate && mEditor == null) {
            mEditor = mPref.edit();
        }
    }

    private void doCommit() {
        if (!mBulkUpdate && mEditor != null) {
            mEditor.commit();
            mEditor = null;
        }
    }

    public void putShouldShowIntro(Boolean showIntro) {
        put(Key.IS_FIRST_LAUNCH, showIntro);
    }

    public boolean getShouldShowIntro() {
        return getBoolean(Key.IS_FIRST_LAUNCH, true);
    }

    public void putFirstName(String name) {
        put(Key.FIRST_NAME, name);
    }

    public void putLastName(String lastname) {
        put(Key.LAST_NAME, lastname);
    }

    public void putEmail(String email) {
        put(Key.EMAIL_ADDRESS, email);
    }

    public void putUserId(int uid) {
        put(Key.USER_ID, uid);
    }

    public int getUserId() {
        return getInt(Key.USER_ID);
    }

    public void putMobileNumber(String mobileNumbeer) {
        put(Key.MOBILE_NUMBER, mobileNumbeer);
    }

    public void putToken(String token) {
        put(Key.TOKEN, token);
    }

    public void putTokenCreated(String tokenCreated) {
        put(Key.TOKEN_CREATED, tokenCreated);
    }

    public void setIsLogged(boolean isLogged) {
        put(Key.IS_LOGGED, isLogged);
    }

    public boolean getIsLogged() {
        return getBoolean(Key.IS_LOGGED, false);
    }

}
