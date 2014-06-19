package efrei.asyria.m1a.smartcard;

import efrei.asyria.m1a.utils.NFCUtils;
import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SimulateurActivity extends Activity {
    NfcAdapter nfcAdapter;
    Button buttonSimulation;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulateur);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
		if (nfcAdapter == null)
		{
			Toast.makeText(this, R.string.nfcUnavailable, Toast.LENGTH_LONG).show();
			finish();
			return;
		}
		
        buttonSimulation = (Button) findViewById(R.id.buttonSimulation);
	    buttonSimulation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sendTag();
			}
		    
		    public void sendTag() {
		    	Intent i = new Intent(NfcAdapter.ACTION_NDEF_DISCOVERED);
		    	NdefMessage[] messages = new NdefMessage[1];
		    	messages[0] = NFCUtils.sendCard("9");
		    	i.putExtra(NfcAdapter.EXTRA_NDEF_MESSAGES, messages);
		    	startActivity(i);
		    }
	    });
    }
}
