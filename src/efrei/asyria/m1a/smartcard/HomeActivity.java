package efrei.asyria.m1a.smartcard;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import efrei.asyria.m1a.adapter.NavDrawerListAdapter;
import efrei.asyria.m1a.asynchronous.HttpPostRequest;
import efrei.asyria.m1a.menu.NavDrawerItem;
import efrei.asyria.m1a.session.SessionLogin;
import efrei.asyria.m1a.utils.NFCUtils;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
 
public class HomeActivity extends Activity implements CreateNdefMessageCallback {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    
    // NFC
    NfcAdapter nfcAdapter;
 
    // nav drawer title
    private CharSequence mDrawerTitle;
 
    // used to store app title
    private CharSequence mTitle;
 
    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
 
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
	private SessionLogin sessionLogin;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
		if (nfcAdapter == null)
		{
			Toast.makeText(this, R.string.nfcUnavailable, Toast.LENGTH_LONG).show();
			finish();
			return;
		}

        nfcAdapter.setNdefPushMessageCallback(this, this);
 
        sessionLogin = new SessionLogin(getApplicationContext());        
        
        mTitle = mDrawerTitle = getTitle();
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
 
        navDrawerItems = new ArrayList<NavDrawerItem>();
        System.out.println(navMenuIcons);
        // adding nav drawer items to array
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
 
        navMenuIcons.recycle();
 
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
        
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);
 
        // enabling action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
 
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.app_name,
                R.string.app_name
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }
 
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
 
        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);
        }
    }
 
    /**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }
 /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
//        switch (item.getItemId()) {
//        case R.id.action_settings:
//            return true;
//        default:
//            return super.onOptionsItemSelected(item);
//        }
        return true;
    }
 
    /***
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
 
    /**
     * Diplaying fragment view for selected nav drawer list item
     * */
    private void displayView(int position) {
        Fragment fragment = null;
        switch (position) {
	        case 0:
	            fragment = new CardListFragment();
	            break;
	        case 1:
	            fragment = new MyCardFragment();
	            break;
	        case 2:
	            fragment = new MyAccountFragment();
	            break;
	        case 3:
	            fragment = new ContactFragment();
	            break;
	        case 4:
	            fragment = new AboutFragment();
	            break;
	        case 5:
	        	ProgressDialog progressDialog = new ProgressDialog(HomeActivity.this);
	    		progressDialog.setMessage("Deconnexion...");
	        	String url = "http://dev.smart-card.fr/logout";
	        	sessionLogin.logout();
				/*try {
					String result = new HttpGetRequest(null, null).execute(url).get();
					sessionLogin.logout();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
	            break;
	        case 6:
	        	Intent i = new Intent(HomeActivity.this, SimulateurActivity.class);
	        	startActivity(i);
	        	break;
	        default:
	            break;
        }
 
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();
 
            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } /*else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }*/
    }
 
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }
 
    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
 
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

	@Override
	public NdefMessage createNdefMessage(NfcEvent event) {
        HashMap<String, String> user = sessionLogin.getUserInfo();
        
        return NFCUtils.sendCard(user.get(SessionLogin.KEY_CARD));
	}
	
    @Override
    public void onResume() {
        super.onResume();
        
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction()))
        {
        	addCardToUser(getIntent());
        }
    }
    
	public void addCardToUser(Intent intent)
	{
        HashMap<String, String> user = sessionLogin.getUserInfo();
        
		// Recupere les donnees
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        NdefRecord[] records = msg.getRecords();
        String idCard = new String(records[0].getPayload());

		String url = "http://dev.smart-card.fr/ajoutUserCards";
		List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("userId", user.get(SessionLogin.KEY_ID)));
		postParameters.add(new BasicNameValuePair("cardId", idCard));
		ProgressDialog progressDialog = new ProgressDialog(HomeActivity.this);
		progressDialog.setIndeterminate(false);
		progressDialog.setMessage("Ajout de la carte...");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.show();
		String result;
		try {
			result = new HttpPostRequest(url, postParameters).execute().get();
			JSONObject obj = new JSONObject(result);
			String success = obj.getString("success");
			if (success.equals("true"))
			{
				Toast.makeText(this, "ajout de carte succes !", Toast.LENGTH_LONG).show();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		progressDialog.dismiss();
    }
}