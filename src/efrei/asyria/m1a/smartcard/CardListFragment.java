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
import efrei.asyria.m1a.model.Card;
import efrei.asyria.m1a.model.User;
import efrei.asyria.m1a.session.SessionLogin;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class CardListFragment extends Fragment {

	private SessionLogin session;
	private ListView listViewCards;
	private HomeActivity homeActivity;
	
	private TextView etE;
	
	public CardListFragment() {}

	public void setHomeActivity(HomeActivity homeActivity) {
		this.homeActivity = homeActivity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.list_cards, container, false);
		
		etE = (TextView) rootView.findViewById(R.id.etError);
		
		ProgressDialog progressDialog = new ProgressDialog(inflater.getContext());
		progressDialog.setIndeterminate(false);
		progressDialog.setMessage("Chargement...");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.show();
		
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
				if (objList.length() == 0) {
					System.out.println("nothing");
					etE.setText("Aucune carte de visite n'a été ajoutée !");

					/*mList.add(new Card(1, new User(1, "surname", "name", "address", 94200, "city", "phoneF", "phoneM", "email"), "cartevisite1"));
					mList.add(new Card(2, new User(2, "surname", "name", "address", 94200, "city", "phoneF", "phoneM", "email"), "cartevisite2"));
					mList.add(new Card(3, new User(3, "surname", "name", "address", 94200, "city", "phoneF", "phoneM", "email"), "cartevisite3"));
					listViewCards = (ListView) rootView.findViewById(R.id.listCard); 
					ListCardAdapter adapter = new ListCardAdapter(this.getActivity(), mList);
					listViewCards.setAdapter(adapter);*/
				} else {
					for (int i=0; i<objList.length(); i++) {
						listCards.add(objList.getJSONObject(i));
					}
					
					for (int i=0; i<listCards.size(); i++) {
//						String idd = listCards.get(i).getString("users_id");
						String idd = listCards.get(i).getString("cards_id");
//						String url2 = "http://dev.smart-card.fr/user?id="+idd;
						String url2 = "http://dev.smart-card.fr/card?id="+idd;
						String r = new HttpGetRequest(url2).execute().get();
//						JSONObject userr = new JSONObject(r);
						JSONObject card = new JSONObject(r);
	
						User u = null;
						if (card.getString("success").equals("true")) {
//							u = new User(Integer.parseInt(userr.getString("idUser")), userr.getString("surname"), userr.getString("name"),
//									userr.getString("cAdress"), 78965, userr.getString("cCity"), userr.getString("phone1"),
//									userr.getString("phone2"), userr.getString("email"), userr.getString("cName"), userr.getString("job"));
							u = new User(Integer.parseInt(card.getString("idUser")), card.getString("surname"), card.getString("name"), card.getString("adress"), 78965, card.getString("city"), card.getString("phone1"),
									card.getString("phone2"), card.getString("email"), card.getString("nameC"), card.getString("job"));
							System.out.println(u);
						} else {
							System.out.println("not good");
							continue;
						}
						mList.add(new Card(Integer.parseInt(listCards.get(i).getString("id")), u, card.getString("idTemplate")));
					}

					//mList.add(new Card(1, u1, "cartevisite"));
					//mList.add(new Card(2, u2, "cartevisite"));
					listViewCards = (ListView) rootView.findViewById(R.id.listCard);
					ListCardAdapter adapter = new ListCardAdapter(this.getActivity(), mList, homeActivity);
					listViewCards.setAdapter(adapter);
				}
				progressDialog.dismiss();
				
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
