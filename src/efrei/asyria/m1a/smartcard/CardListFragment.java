package efrei.asyria.m1a.smartcard;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import efrei.asyria.m1a.adapter.ListCardAdapter;
import efrei.asyria.m1a.asynchronous.HttpGetRequest;
import efrei.asyria.m1a.asynchronous.HttpPostRequest;
import efrei.asyria.m1a.model.Card;
import efrei.asyria.m1a.model.User;
import efrei.asyria.m1a.session.SessionLogin;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ListAdapter;
import android.widget.ListView;

public class CardListFragment extends Fragment {

	private SessionLogin session;
	private ListView listViewCards;

	public CardListFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.list_cards, container, false);
		session = new SessionLogin(inflater.getContext());
		System.out.println(Integer.parseInt(session.getId()));
		String id = session.getId();

		String url = "http://dev.smart-card.fr/userCards?userId="+id;
		//url = "/SmartCard/cards.json";
		List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("userId", id));
		
		try {
			String result = new HttpGetRequest(url).execute().get();
			System.out.println("back to cardlist");
			System.out.println(result);
			/*
			[{
				"id":"1",
				"users_id":"1",
				"cards_id":"2",
				"created_at":"2014-04-13 21:13:21",
				"updated_at":"2014-04-13 21:13:21"},
			{
				"id":"2",
				"users_id":"1",
				"cards_id":"3",
				"created_at":"2014-04-13 21:22:11",
				"updated_at":"2014-04-13 21:22:11"
			}]

			 */
			
			try {
				JSONArray objList = new JSONArray(result);
				List<JSONObject> listCards = new ArrayList<JSONObject>();
				List<Card> mList = new ArrayList<Card>();
				
				for (int i=0; i<objList.length(); i++) {
					listCards.add(objList.getJSONObject(i));
				}
				
				User u1 = new User(1, "Mage", "David", "8 rue de la paix", 92033, "Nanterre", "0102030508", "0625585897", "coco@gmail.com");
				User u2 = new User(2, "Le Collonnier", "Vincent", "50 rue de l'etoile", 75003, "Paris", "0206568743", "0698347525", "vincentlecollonnier@gmail.com");
				
				for (int i=0; i<listCards.size(); i++) {
					mList.add(new Card(Integer.parseInt(listCards.get(i).getString("id")), u1, listCards.get(i).getString("cards_id")));
				}
				
				//mList.add(new Card(1, u1, "cartevisite"));
				//mList.add(new Card(2, u2, "cartevisite"));

				listViewCards = (ListView) rootView.findViewById(R.id.listCard); 
				ListCardAdapter adapter = new ListCardAdapter(this.getActivity(), mList);
				listViewCards.setAdapter(adapter);
				
			} catch (Throwable t) {
				Log.e("My App", "Could not parse malformed JSON: \"" + t + "\"");
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rootView;
	}
}
