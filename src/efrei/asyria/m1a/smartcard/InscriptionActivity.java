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
				try {
					if (etLogin.getText().toString().equals("")) {

						System.out.println("login vide");
					}
					if (etMail.getText().toString().equals("")) {

						System.out.println("mail vide");
					}
									
					if (etPwd1.getText().toString().equals("")) {

						System.out.println("pwd1 vide");
					}
					
					if (etPwd2.getText().toString().equals("")) {
						System.out.println("pwd2 vide");
					}
					
					if (!etPwd1.equals(etPwd2)) {

						System.out.println("pwd1 et pwd2 different");
					}

					String url = "http://dev.smart-card.fr/inscription";
					List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
					postParameters.add(new BasicNameValuePair("username", etLogin.getText().toString()));
					postParameters.add(new BasicNameValuePair("email", etMail.getText().toString()));
					postParameters.add(new BasicNameValuePair("password", etPwd1.getText().toString()));
					try {
						progressDialog.show();
						String result = new HttpPostRequest(url, postParameters).execute().get();
						
						//String result = new HttpGetRequest(progressDialog).execute(url).get();
						System.out.println(result);
						/*try {
							JSONObject obj = new JSONObject(result);
							String id = obj.getString("idUser");
							String username = obj.getString("username");
							String email = obj.getString("email");
							SessionLogin sessionLogin = new SessionLogin(getApplicationContext());

	//						public void createLoginSession(String id, String u, String email, String name, String surname, String phone1, String phone2, String job, String cname, String ccity, String ccountry) {
							
							sessionLogin.createLoginSession(id, username, email, obj.getString("name"), obj.getString("surname"), obj.getString("phone1"), obj.getString("phone2"), obj.getString("job"), obj.getString("cName"), obj.getString("cCity"), obj.getString("cAdress"), obj.getString("cCountry"));
							Intent i = new Intent(InscriptionActivity.this, ConnectionActivity.class);
							startActivity(i);
						} catch (Throwable t) {
							tvError.setText(R.string.connectionError);
							Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
						}*/
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				
				} catch (Exception e) {
					
				}
				//Intent i = new Intent(InscriptionActivity.this, HomeActivity.class);
				//startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
