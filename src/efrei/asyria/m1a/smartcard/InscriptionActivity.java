package efrei.asyria.m1a.smartcard;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import efrei.asyria.m1a.asynchronous.HttpPostRequest;
import efrei.asyria.m1a.session.SessionLogin;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InscriptionActivity extends Activity {
	
	private Button buttonInscription;
	private EditText etLogin;
	private EditText etMail;
	private EditText etPwd1;
	private EditText etPwd2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inscription);
		etLogin = (EditText) findViewById(R.id.etLogin);
		etMail = (EditText) findViewById(R.id.etMail);
		etPwd1 = (EditText) findViewById(R.id.etPwd1);
		etPwd2 = (EditText) findViewById(R.id.etPwd2);
		buttonInscription = (Button) findViewById(R.id.buttonValidInscription);
		buttonInscription.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ProgressDialog progressDialog = new ProgressDialog(InscriptionActivity.this);
				progressDialog.setIndeterminate(false);
				progressDialog.setMessage("Inscription...");
				progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

				String error="";
				try {
					if (etLogin.getText().toString().equals("")) {
						error="Lelogin est vide";
						throw new Exception();
					}
					if (etMail.getText().toString().equals("")) {

						error="Le mail est vide";
						throw new Exception();
					}
									
					if (etPwd1.getText().toString().equals("")) {
						error="Le mot de passe est vide";
						throw new Exception();
					}
					
					if (etPwd2.getText().toString().equals("")) {
						error="Le mot de passe est vide";
						throw new Exception();
					}
					
					if (!etPwd1.getText().toString().equals(etPwd2.getText().toString())) {
						error="Les mots de passe sont differents";
						throw new Exception();
					}

					String url = "http://dev.smart-card.fr/inscription";
					List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
					postParameters.add(new BasicNameValuePair("username", etLogin.getText().toString()));
					postParameters.add(new BasicNameValuePair("email", etMail.getText().toString()));
					postParameters.add(new BasicNameValuePair("password", etPwd1.getText().toString()));
					try {
						progressDialog.show();
						String result = new HttpPostRequest(url, postParameters).execute().get();
						JSONObject obj = new JSONObject(result);
						String id = obj.getString("idUser");
						//String result = new HttpGetRequest(progressDialog).execute(url).get();
						System.out.println("isuser=>"+id);

						progressDialog.dismiss();
						Intent i = new Intent (InscriptionActivity.this, Inscription2Activity.class);
						i.putExtra("id", id);
						startActivity(i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				
				} catch (Exception e) {					
					System.out.println(e.toString());
					TextView t = (TextView) findViewById(R.id.tvError);
					t.setText(error);
				}
				/*
				 progressDialog.dismiss();
				Intent i = new Intent (InscriptionActivity.this, StartActivity.class);
				startActivity(i);
				 */
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
