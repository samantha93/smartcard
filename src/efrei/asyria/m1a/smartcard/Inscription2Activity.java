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

public class Inscription2Activity extends Activity {
	
	private Button buttonInscription;
	private EditText etS;
	private EditText etN;
	private EditText etP1;
	private EditText etP2;
	private String idU;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inscription2);
		
		Bundle extras = getIntent().getExtras();
    	idU = extras.getString("id");
    	System.out.println("id=>"+idU);
		etS = (EditText) findViewById(R.id.etSurname);
		etN = (EditText) findViewById(R.id.etName);
		etP1 = (EditText) findViewById(R.id.etP1);
		etP2 = (EditText) findViewById(R.id.etP2);
		buttonInscription = (Button) findViewById(R.id.bNext2);
		buttonInscription.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ProgressDialog progressDialog = new ProgressDialog(Inscription2Activity.this);
				progressDialog.setIndeterminate(false);
				progressDialog.setMessage("Suivant...");
				progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				String error = "Erreur";
				
				try {
					if (etS.getText().toString().equals("")) {
						error="nom vide";
						throw new Exception();
					}
					if (etN.getText().toString().equals("")) {
						error="prénom vide";
						throw new Exception();
					}
									
					if (etP1.getText().toString().equals("")) {
						error="fixe vide";
						throw new Exception();
					}
					
					if (etP2.getText().toString().equals("")) {
						error="mobile vide";
						throw new Exception();
					}
					

					String url = "http://dev.smart-card.fr/modifierUser";
					List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
					postParameters.add(new BasicNameValuePair("id", idU));
					postParameters.add(new BasicNameValuePair("name", etN.getText().toString()));
					postParameters.add(new BasicNameValuePair("surname", etS.getText().toString()));
					postParameters.add(new BasicNameValuePair("phone1", etP1.getText().toString()));
					postParameters.add(new BasicNameValuePair("phone2", etP2.getText().toString()));
					try {
						progressDialog.show();
						String result = new HttpPostRequest(url, postParameters).execute().get();
						JSONObject obj = new JSONObject(result);
						if (obj.getString("success").equals("true")) {
							progressDialog.dismiss();
							Intent i = new Intent (Inscription2Activity.this, Inscription3Activity.class);
							i.putExtra("id", idU);
							startActivity(i);
						}

						progressDialog.dismiss();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				
				} catch (Exception e) {
					System.out.println(e.toString());
					TextView t = (TextView) findViewById(R.id.tvErr);
					t.setText(error);
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
