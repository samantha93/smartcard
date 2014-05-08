package efrei.asyria.m1a.smartcard;

import efrei.asyria.m1a.session.SessionLogin;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartActivity extends Activity {

	private SessionLogin sessionLogin;
	private Button buttonInscription;
	private Button buttonConnection;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        sessionLogin = new SessionLogin(getApplicationContext());
        sessionLogin.isLog();
        
        buttonConnection = (Button) findViewById(R.id.ButtonConnection);
        buttonConnection.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SessionLogin sessionLogin = new SessionLogin(getApplicationContext());
		        sessionLogin.isLog();
				Intent i = new Intent(StartActivity.this, ConnectionActivity.class);
				startActivity(i);
			}
		});
        buttonInscription = (Button) findViewById(R.id.ButtonInscription);
        buttonInscription.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(StartActivity.this, InscriptionActivity.class);
				startActivity(i);
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
