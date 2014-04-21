package efrei.asyria.m1a.session;

import java.util.HashMap;

import android.content.Context;
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
	public void createLoginSession(String id, String u, String email) {
		//editor.putInt(KEY_ID, id);
		editor.putString(KEY_ID, id);
		editor.putString(KEY_USERNAME, u);
		editor.putString(KEY_EMAIL, email);
		
		editor.commit();
	}
	
	public HashMap<String, String> getUserInfo() {
		HashMap<String, String> user = new HashMap<String, String>();

		user.put(KEY_ID, pref.getString(KEY_ID, null));
		user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));

		user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
		
		return user;
	}
}
