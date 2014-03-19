package efrei.asyria.m1a.smartcard;

import java.util.ArrayList;
import java.util.List;

import efrei.asyria.m1a.adapter.ListCardAdapter;
import efrei.asyria.m1a.model.Card;
import efrei.asyria.m1a.model.User;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ListAdapter;
import android.widget.ListView;

public class CardListFragment extends Fragment {

	private ListView listViewCards;

	public CardListFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.list_cards, container, false);

		List<Card> mList = new ArrayList<Card>();
		User u1 = new User(1, "Nom", "Pierre", "8 rue de la paix", 92033, "Nanterre", "0102030508", "0625585897", "coco@gmail.com");
		User u2 = new User(2, "Le Collonnier", "Vincent", "50 rue de l'etoile", 75003, "Paris", "0206568743", "0698347525", "vincentlecollonnier@gmail.com");
		mList.add(new Card(1, u1, "cartevisite2"));
		mList.add(new Card(2, u2, "cartevisite1"));
		mList.add(new Card(3, u1, "cartevisite3"));
		
		listViewCards = (ListView) rootView.findViewById(R.id.listCard); 
		ListCardAdapter adapter = new ListCardAdapter(this.getActivity(), mList);
		listViewCards.setAdapter(adapter);
		
		return rootView;
	}
}
