package efrei.asyria.m1a.session;

import java.util.HashMap;

import efrei.asyria.m1a.smartcard.HomeActivity;
import efrei.asyria.m1a.smartcard.StartActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionLogin {

	SharedPreferences pref;

	Editor editor;
	Context context;
	int PRIVATE_MODE = 0;

	public static final String KEY_ID = "idUser";
	public static final String KEY_USERNAME = "username";
	public static final String KEY_EMAIL = "email";
	public static final String KEY_NAME = "name";
	public static final String KEY_SURNAME = "surname";
	public static final String KEY_PHONE1 = "phone1";
	public static final String KEY_PHONE2 = "phone2";
	public static final String KEY_JOB = "job";
	public static final String KEY_CNAME = "cname";
	public static final String KEY_CADRESS = "cadress";
	public static final String KEY_CCITY = "ccity";
	public static final String KEY_CCOUNTRY = "ccountry";
	public static final String KEY_TEMPLATE = "idTemp";
	public static final String KEY_CARD = "idCard";
	
	private static final String IS_LOGIN = "isLog";
	private static final String PREF_NAME = "LoginPref";
	
	public SessionLogin(Context c) {
		context = c;
		pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}
	/*
	 {
	 "idUser":"2",
	 "username":"Dupont",
	 "email":"dupont@plop.fr",
	 "name":"test1",
	 "surname":"test1",
	 "phone1":"21608722",
	 "phone2":"21608722",
	 "statut":"user",
	 "idCard":"2",
	 "job":"developer",
	 "idTemplate":"2",
	 "idCompany":"2",
	 "cName":"Resd",
	 "cAdress":"1 rue de tata",
	 "cCity":"Paris",
	 "cCountry":"France"}
	 */
	public void createLoginSession(String id, String u, String email, String name, String surname,
			String phone1, String phone2, String job, String cname, String ccity, String cadress,
			String ccountry, String tt, String idc) {
		//editor.putInt(KEY_ID, id);

        editor.putBoolean(IS_LOGIN, true);
		editor.putString(KEY_ID, id);
		editor.putString(KEY_USERNAME, u);
		editor.putString(KEY_EMAIL, email);
		editor.putString(KEY_NAME, name+" "+surname);
		editor.putString(KEY_PHONE1, phone1);
		editor.putString(KEY_PHONE2, phone2);
		editor.putString(KEY_JOB, job);
		editor.putString(KEY_CNAME, cname);
		editor.putString(KEY_CADRESS, cadress);
		editor.putString(KEY_CCITY, ccity);
		editor.putString(KEY_CCOUNTRY, ccountry);
		editor.putString(KEY_TEMPLATE, tt);
		editor.putString(KEY_CARD, idc);

		//editor.putString(KEY_EMAIL, email);
		
		editor.commit();
	}
	
	public void setTpl(String t) {
		System.out.println("change template to "+t);
		editor.putString(KEY_TEMPLATE, t);
		editor.commit();
	}
	
	public HashMap<String, String> getUserInfo() {
		HashMap<String, String> user = new HashMap<String, String>();

		user.put(KEY_ID, pref.getString(KEY_ID, null));
		user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));
		user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
		user.put(KEY_NAME, pref.getString(KEY_NAME, null));
		user.put(KEY_PHONE1, pref.getString(KEY_PHONE1, null));
		user.put(KEY_PHONE2, pref.getString(KEY_PHONE2, null));
		user.put(KEY_JOB, pref.getString(KEY_JOB, null));
		user.put(KEY_CNAME, pref.getString(KEY_CNAME, null));
		user.put(KEY_CCITY, pref.getString(KEY_CCITY, null));
		user.put(KEY_CADRESS, pref.getString(KEY_CADRESS, null));
		user.put(KEY_CCOUNTRY, pref.getString(KEY_CCOUNTRY, null));
		user.put(KEY_TEMPLATE, pref.getString(KEY_TEMPLATE,  null));
		user.put(KEY_CARD, pref.getString(KEY_CARD,  null));
		
		return user;
	}
	
	public String getId() {
		return pref.getString(KEY_ID, null);
	}
	
	 public void isLog(Activity activity){
        if(pref.getBoolean(IS_LOGIN, false)){
        	System.out.println("iS LOG");
            Intent i = new Intent(context, HomeActivity.class);
        	i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            activity.finish();
        }   
        System.out.println("NOT LOG");
    }
	 
	 public void logout(Activity activity) {
		 editor.clear();
		 editor.commit();
		 System.out.println("Logout");
		 Intent i = new Intent(context, StartActivity.class);
		 i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	     i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		 context.startActivity(i);
		 activity.finish();
	 }
}
