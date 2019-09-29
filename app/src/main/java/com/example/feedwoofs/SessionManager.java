package com.example.feedwoofs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    public static final String PREF_NAME="LOGIN";
    public static final String LOGIN = "IS_LOGIN";
    public static final String FULLNAME = "FULLNAME";
    public static final String ID_USER = "ID_USER";
    public static final String EMAIL = "EMAIL";
    public static final String ADDRESS = "ADDRESS";
    public static final String PHONE = "PHONE";
    public static final String AVATAR = "AVATAR";


    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String id_user, String namaRegister, String emailRegister, String alamatRegister, String phoneRegister, String avatar){
        editor.putBoolean(LOGIN,true);
        editor.putString(ID_USER,id_user);
        editor.putString(FULLNAME,namaRegister);
        editor.putString(EMAIL,emailRegister);
        editor.putString(ADDRESS,alamatRegister);
        editor.putString(PHONE,phoneRegister);
        editor.putString(AVATAR,avatar);

        editor.apply();
    }

    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN,false);
    }

    public void checkLogin(){
        if (!this.isLoggin()) {
            Intent i = new Intent(context, MainActivity.class);
            context.startActivity(i);
            ((HomeActivity1)context).finish();
        }
    }


    public HashMap<String, String> getUserDetail(){
        HashMap<String,String>user = new HashMap<>();
        user.put(FULLNAME,sharedPreferences.getString(FULLNAME,null));
        user.put(EMAIL,sharedPreferences.getString(EMAIL,null));
        user.put(ADDRESS,sharedPreferences.getString(ADDRESS,null));
        user.put(PHONE,sharedPreferences.getString(PHONE,null));
        user.put(AVATAR,sharedPreferences.getString(AVATAR,null));

        user.put(ID_USER,sharedPreferences.getString(ID_USER,null));

        return user;
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context,MainActivity.class);
        context.startActivity(i);
        ((ProfileUserActivity)context).finish();
    }
}
