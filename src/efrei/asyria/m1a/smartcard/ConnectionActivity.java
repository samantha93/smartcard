package efrei.asyria.m1a.smartcard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import efrei.asyria.m1a.asynchronous.HttpGetRequest;
import efrei.asyria.m1a.asynchronous.HttpPostRequest;
import efrei.asyria.m1a.session.SessionLogin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConnectionActivity extends Activity {

	private EditText etLogin;
	private EditText etPwd;
	private Button validButton;
	private TextView tvError;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connexion);
		etLogin = (EditText) findViewById(R.id.etLogin);
		etPwd = (EditText) findViewById(R.id.etPwd);
		tvError = (TextView) findViewById(R.id.tvError);
		validButton = (Button) findViewById(R.id.buttonValidConnexion);
		validButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ProgressDialog progressDialog = new ProgressDialog(ConnectionActivity.this);
				progressDialog.setIndeterminate(false);
				progressDialog.setMessage("Connexion...");
				progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				
				String url = "http://dev.smart-card.fr/login";
				List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
				postParameters.add(new BasicNameValuePair("username", etLogin.getText().toString()));
				postParameters.add(new BasicNameValuePair("password", etPwd.getText().toString()));
				//String url2 = "http://dev.smart-card.fr/login?username="+etLogin.getText()+"&password="+etPwd.getText();
				/*try {
					String result = new HttpPostRequest(url, postParameters).execute().get();
					System.out.println(result);
					if (result.indexOf("1")== 0 ) {
						System.out.println("result ok");
						tvError.setText("");
						Intent i = new Intent(ConnectionActivity.this, HomeActivity.class);
						startActivity(i);
					} else {
						progressDialog.dismiss();
						tvError.setText(R.string.connectionError);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				try {
					progressDialog.show();
					String result = new HttpPostRequest(url, postParameters).execute().get();
					
					//String result = new HttpGetRequest(progressDialog).execute(url).get();
					System.out.println(result);
					try {
						JSONObject obj = new JSONObject(result);
						String id = obj.getString("idUser");
						String username = obj.getString("username");
						String email = obj.getString("email");
						SessionLogin sessionLogin = new SessionLogin(getApplicationContext());
						/*if (obj.getString("success").equals("false")) {
							Intent i = new Intent(ConnectionActivity.this, HomeActivity.class);
							startActivity(i);
						} else {
							progressDialog.dismiss();
							tvError.setText(R.string.connectionError);
						}*/
						sessionLogin.createLoginSession(id, username, email);
						Intent i = new Intent(ConnectionActivity.this, HomeActivity.class);
						startActivity(i);
					} catch (Throwable t) {
						tvError.setText(R.string.connectionError);
						Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				
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
