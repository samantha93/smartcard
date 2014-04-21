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

		System.out.println(user.get(SessionLogin.KEY_USERNAME));
		System.out.println(user.get(SessionLogin.KEY_EMAIL));
		
		tvName.setText(user.get(SessionLogin.KEY_USERNAME));
		tvEmail.setText(user.get(SessionLogin.KEY_EMAIL));
		return rootView;
	}
}