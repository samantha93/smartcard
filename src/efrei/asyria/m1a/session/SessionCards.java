package efrei.asyria.m1a.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionCards {
	SharedPreferences pref;

	Editor editor;
	Context context;
	
	private static final String CARDS_STORED = "cards_stored";

	private static final String PREF_NAME = "CardsPref";
	
	public SessionCards(Context c) {
		context = c;
		pref = context.getSharedPreferences(PREF_NAME, 0);
		editor = pref.edit();
	}
	
	public void createCardList() {
	}
	
	public boolean cardsStored() {
		
		
		return true;
	}
	
	
}
