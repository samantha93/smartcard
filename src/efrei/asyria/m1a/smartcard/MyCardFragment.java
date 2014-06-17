package efrei.asyria.m1a.smartcard;

import java.util.HashMap;

import efrei.asyria.m1a.session.SessionLogin;
import android.app.Fragment;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyCardFragment extends Fragment {
	private TextView tvName;
	private TextView tvEmail;

	private TextView tvPhone1;
	private TextView tvPhone2;

	private TextView tvJob;
	private TextView tvCname;

	private TextView tvCcity;
	private TextView tvCcountry;
	private TextView tvCadress;


    SessionLogin session;
	public MyCardFragment(){}
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	View rootView = inflater.inflate(R.layout.fragment_mycard, container, false);

    	session = new SessionLogin(container.getContext());
        HashMap<String, String> user = session.getUserInfo();
		tvName = (TextView) rootView.findViewById(R.id.name);
		tvEmail = (TextView) rootView.findViewById(R.id.email);
		tvPhone1 = (TextView) rootView.findViewById(R.id.phoneF);
		tvPhone2 = (TextView) rootView.findViewById(R.id.phoneM);
		//tvJob = (TextView) rootView.findViewById(R.id.tvJob);
		//tvCname = (TextView) rootView.findViewById(R.id.tvCname);
		tvCadress = (TextView) rootView.findViewById(R.id.adress);
		tvCcity = (TextView) rootView.findViewById(R.id.cpCity);

		System.out.println(user.get(SessionLogin.KEY_USERNAME));
		System.out.println(user.get(SessionLogin.KEY_EMAIL));

		tvName.setText(user.get(SessionLogin.KEY_NAME));
		tvEmail.setText(user.get(SessionLogin.KEY_EMAIL));
		tvPhone1.setText(user.get(SessionLogin.KEY_PHONE1));
		tvPhone2.setText(user.get(SessionLogin.KEY_PHONE2));
		//tvCname.setText(user.get(SessionLogin.KEY_CNAME));
		tvCadress.setText(user.get(SessionLogin.KEY_CADRESS));
		tvCcity.setText(user.get(SessionLogin.KEY_CCITY));
		ImageView ii = (ImageView) rootView.findViewById(R.id.imgCV);
		String nn = user.get(SessionLogin.KEY_TEMPLATE);
		System.out.println("deeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
		System.out.println(nn);
		switch ((Integer.parseInt(nn))) {
		case 1:
			ii.setImageResource(R.drawable.cartevisite1);
			break;
		case 2:
			ii.setImageResource(R.drawable.cartevisite2);
			break;
		case 3:
			ii.setImageResource(R.drawable.cartevisite3);
			break;
		case 4:
			ii.setImageResource(R.drawable.cartevisite4);
			break;
		case 5:
			ii.setImageResource(R.drawable.cartevisite2);
			break;
	}
		return rootView;
    }
}
