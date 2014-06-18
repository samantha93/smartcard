package efrei.asyria.m1a.smartcard;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import efrei.asyria.m1a.asynchronous.HttpPostRequest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Inscription3Activity extends Activity {
	
	private Button buttonInscription;
	private EditText etJ;
	private EditText etA;
	private EditText etC;
	private EditText etP;
	private EditText etN;
	private String idU;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inscription3);
		
		Bundle extras = getIntent().getExtras();
    	idU = extras.getString("id");
		etJ = (EditText) findViewById(R.id.etJob);
		etN = (EditText) findViewById(R.id.etCName);
		etC = (EditText) findViewById(R.id.etCCity);
		etA = (EditText) findViewById(R.id.etCAdress);
		etP = (EditText) findViewById(R.id.etCCountry);
		buttonInscription = (Button) findViewById(R.id.buttonValidInscr);
		buttonInscription.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ProgressDialog progressDialog = new ProgressDialog(Inscription3Activity.this);
				progressDialog.setIndeterminate(false);
				progressDialog.setMessage("Inscription...");
				progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				String error = "Erreur";
				
				try {
					if (etJ.getText().toString().equals("")) {
						error="Job vide";
						throw new Exception();
					}
					if (etN.getText().toString().equals("")) {
						error="Nom entreprise vide";
						throw new Exception();
					}
					
					if (etA.getText().toString().equals("")) {
						error="Adresse vide";
						throw new Exception();
					}
					
					if (etC.getText().toString().equals("")) {
						error="Ville vide";
						throw new Exception();
					}
					
					if (etP.getText().toString().equals("")) {
						error="Pays vide";
						throw new Exception();
					}
					

					String url = "http://dev.smart-card.fr/creerCompany";
					List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
					postParameters.add(new BasicNameValuePair("name", etN.getText().toString()));
					postParameters.add(new BasicNameValuePair("adress", etA.getText().toString()));
					postParameters.add(new BasicNameValuePair("city", etC.getText().toString()));
					postParameters.add(new BasicNameValuePair("country", etP.getText().toString()));
					try {
						progressDialog.show();
						String result = new HttpPostRequest(url, postParameters).execute().get();
						JSONObject obj = new JSONObject(result);
						if (obj.getString("success").equals("true")) {
							System.out.println(obj.getString("id"));
							
							String url2 = "http://dev.smart-card.fr/creerCard";
							List<NameValuePair> postParameters2 = new ArrayList<NameValuePair>();
							postParameters2.add(new BasicNameValuePair("idUser", idU));
							postParameters2.add(new BasicNameValuePair("job", etJ.getText().toString()));
							postParameters2.add(new BasicNameValuePair("idTemplate", "1"));
							postParameters2.add(new BasicNameValuePair("companys_id", obj.getString("id")));
							
							String rrr = new HttpPostRequest(url2, postParameters2).execute().get();
							JSONObject obj2 = new JSONObject(rrr);
							if (obj2.getString("success").equals("true")) {
								obj2.getString("id");
								progressDialog.dismiss();
								Intent i = new Intent (Inscription3Activity.this, ConnectionActivity.class);
								startActivity(i);
							}
							/*Intent i = new Intent (Inscription3Activity.this, Inscription3Activity.class);
							i.putExtra("id", idU);
							startActivity(i);*/
						}

						progressDialog.dismiss();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				
				} catch (Exception e) {
					System.out.println(e.toString());
					TextView t = (TextView) findViewById(R.id.tvErr3);
					t.setText(error);
				}
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
