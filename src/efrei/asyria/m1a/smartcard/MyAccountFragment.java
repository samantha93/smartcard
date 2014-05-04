package efrei.asyria.m1a.smartcard;

import java.util.HashMap;

import efrei.asyria.m1a.session.SessionLogin;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAccountFragment extends Fragment {

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
    
	public MyAccountFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_myaccount, container, false);

        session = new SessionLogin(container.getContext());
        HashMap<String, String> user = session.getUserInfo();
		tvName = (TextView) rootView.findViewById(R.id.tvNameUser);
		tvEmail = (TextView) rootView.findViewById(R.id.tvEmail);
		tvPhone1 = (TextView) rootView.findViewById(R.id.tvPhone1);
		tvPhone2 = (TextView) rootView.findViewById(R.id.tvPhone2);
		tvJob = (TextView) rootView.findViewById(R.id.tvJob);
		tvCname = (TextView) rootView.findViewById(R.id.tvCname);
		tvCcity = (TextView) rootView.findViewById(R.id.tvCcity);
		tvCadress = (TextView) rootView.findViewById(R.id.tvCadress);
		tvCcountry = (TextView) rootView.findViewById(R.id.tvCcountry);

		System.out.println(user.get(SessionLogin.KEY_USERNAME));
		System.out.println(user.get(SessionLogin.KEY_EMAIL));

		tvName.setText(user.get(SessionLogin.KEY_NAME));
		tvEmail.setText(user.get(SessionLogin.KEY_EMAIL));
		tvPhone1.setText(user.get(SessionLogin.KEY_PHONE1));
		tvPhone2.setText(user.get(SessionLogin.KEY_PHONE2));
		tvJob.setText(user.get(SessionLogin.KEY_JOB));
		tvCname.setText(user.get(SessionLogin.KEY_CNAME));
		tvCcity.setText(user.get(SessionLogin.KEY_CCITY));
		tvCcountry.setText(user.get(SessionLogin.KEY_CADRESS));
		tvCadress.setText(user.get(SessionLogin.KEY_CCOUNTRY));
		
		return rootView;
	}
}