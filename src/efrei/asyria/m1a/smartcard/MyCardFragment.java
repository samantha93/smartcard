package efrei.asyria.m1a.smartcard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import efrei.asyria.m1a.asynchronous.HttpPostRequest;
import efrei.asyria.m1a.session.SessionLogin;
import android.app.Fragment;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
	
	private Button bRadio;
	private RadioGroup rGroup;

	ImageView ii;

    SessionLogin session;
	public MyCardFragment(){}
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	final View rootView = inflater.inflate(R.layout.fragment_mycard, container, false);

		bRadio = (Button) rootView.findViewById(R.id.bChangeTpl);
    	rGroup = (RadioGroup) rootView.findViewById(R.id.rTemplate);
    	

    	session = new SessionLogin(container.getContext());
    	final HashMap<String, String> user = session.getUserInfo();
		tvName = (TextView) rootView.findViewById(R.id.tvName);
		tvEmail = (TextView) rootView.findViewById(R.id.email);
		tvPhone1 = (TextView) rootView.findViewById(R.id.phoneF);
		tvPhone2 = (TextView) rootView.findViewById(R.id.phoneM);
		//tvJob = (TextView) rootView.findViewById(R.id.tvJob);
		tvCname = (TextView) rootView.findViewById(R.id.cname);
		tvCadress = (TextView) rootView.findViewById(R.id.adress);
		tvCcity = (TextView) rootView.findViewById(R.id.cpCity);

		System.out.println(user.get(SessionLogin.KEY_USERNAME));
		System.out.println(user.get(SessionLogin.KEY_EMAIL));

		tvName.setText(user.get(SessionLogin.KEY_NAME));
		tvEmail.setText(user.get(SessionLogin.KEY_EMAIL));
		tvPhone1.setText(user.get(SessionLogin.KEY_PHONE1));
		tvPhone2.setText(user.get(SessionLogin.KEY_PHONE2));
		tvCname.setText(user.get(SessionLogin.KEY_CNAME));
		tvCadress.setText(user.get(SessionLogin.KEY_CADRESS));
		tvCcity.setText(user.get(SessionLogin.KEY_CCITY));
		TextView tvj = (TextView) rootView.findViewById(R.id.tvJob);
		tvj.setText(user.get(SessionLogin.KEY_JOB));
		
		ii = (ImageView) rootView.findViewById(R.id.imgCV);
		String nn = user.get(SessionLogin.KEY_TEMPLATE);
		changeTpl(nn);
		
		bRadio.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int id = rGroup.getCheckedRadioButtonId();
				RadioButton rb = (RadioButton) rootView.findViewById(id);
				String tpl = (String) rb.getText().toString();
				String idC = user.get(SessionLogin.KEY_CARD);
				System.out.println("idC:"+idC);
				System.out.println("tpl:"+tpl);
				String url2 = "http://dev.smart-card.fr/modifierCardTemplate";
				List<NameValuePair> postParameters2 = new ArrayList<NameValuePair>();
				postParameters2.add(new BasicNameValuePair("id", idC));
				postParameters2.add(new BasicNameValuePair("idTemplate", tpl));
				
				String rrr;
				try {
					rrr = new HttpPostRequest(url2, postParameters2).execute().get();
					JSONObject obj2 = new JSONObject(rrr);
					session.setTpl(tpl);

					changeTpl(tpl);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		return rootView;
    }
    
    public void changeTpl(String nn) {
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
			ii.setImageResource(R.drawable.cartevisite5);
			break;
		}
    }
}
