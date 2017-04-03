package com.example.assignment.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.assignment.R;
import com.example.assignment.Utils.CacheDataOnDisk;
import com.example.assignment.Utils.Constants;
import com.example.assignment.Utils.GPSTracker;
import com.example.assignment.Utils.SharedPreferencesData;
import com.example.assignment.Utils.TimerSingleton;
import com.example.assignment.fragments.ListFragment_;
import com.example.assignment.interfaces.LocationCallBack;
import com.example.assignment.restclient.Applib;
import com.example.assignment.restclient.ErrorCodes;
import com.example.assignment.restclient.INetworkInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView list_items_recyclerView;
    private FrameLayout fragment_container;
    private Toolbar toolbar;
    public SearchView searchView;
    private ProgressDialog progressDialog;
    private Location location_;
    private boolean isActivityFinished;
    private String latitude;
    private String longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // If the screen is now in landscape mode, we can show the
            // dialog in-line with the list so we don't need this activity.

        }else {
            getSupportFragmentManager().beginTransaction().add(R.id.details, new ListFragment_())
                    .addToBackStack(null).commit();
        }


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.payment_history);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.list_of_items_title));

        IntentFilter intentFilterShowAds = new IntentFilter(
                Constants.BROADCAST_SHOW_LOGIN_SCREEN);
        registerReceiver(showAdActivity, intentFilterShowAds);

      checkMarshmallowPermission();
//        initListener();
    }

    private void checkMarshmallowPermission() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (!checkIfAlreadyhavePermission()) {
                requestForSpecificPermission();
            } else {
                fetchLocation();
            }

        } else {
            fetchLocation();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void requestForSpecificPermission() {
        ActivityCompat.requestPermissions((MainActivity.this), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
    }

    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLocation();
                } else {
                    Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_LONG).show();
                    return;
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onRestart() {
        isActivityFinished = false;
        if (Constants.LOCATION_SETTINGS_CALLED) {
            Constants.LOCATION_SETTINGS_CALLED = false;
            checkMarshmallowPermission();
        }
        super.onRestart();

    }


    BroadcastReceiver showAdActivity = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            Toast.makeText(HeaderActivity.this,"ACTIVE CALLED",Toast.LENGTH_LONG).show();

            if(!LoginScreen.active) {
                Intent intent1 = new Intent(getApplicationContext(), LoginScreen.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
            }
        }
    };




    public void showProgressDialog(String message) {
        progressDialog = ProgressDialog.show(this, "", message);
//        progressDialog.setCanceledOnTouchOutside(false);
//        progressDialog.setCancelable(false);
    }

    public void dismissProgressDialog(){

        if(progressDialog != null){
            if(progressDialog.isShowing())
            progressDialog.dismiss();
        }

    }
    private void fetchLocation() {
        showProgressDialog("Getting Location");
        GPSTracker gps = new GPSTracker(MainActivity.this, new LocationCallBack() {
            @Override
            public void getLocation(String latitude, String longitude) {

//                location_ = location;
                Log.d("Latidue", latitude);
                Log.d("longitude", longitude);
                MainActivity.this.latitude = latitude;
                MainActivity.this.longitude = longitude;
                SharedPreferencesData sharedPreferencesData = new SharedPreferencesData();
                sharedPreferencesData.setLongitude(longitude);
                sharedPreferencesData.setLatitude(latitude);

                dismissProgressDialog();

            }

            @Override
            public void onError(String message, ErrorCodes code) {
                Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                dismissProgressDialog();
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        // Retrieve the SearchView and plug it into SearchManager
         searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            hideKeyboard();

            popBackStack(true);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void popBackStack(boolean popBackStack) {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        }else{
            finish();
        }
    }

    public void hideKeyboard() {
        try {
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void resetDisconnectTimer()
    {
        TimerSingleton.getInstance().stopTimer(this);
        TimerSingleton.getInstance().startTimer(this);

    }
    public void stopDisconnectTimer()
    {
        TimerSingleton.getInstance().stopTimer(this);
    }

    @Override
    public void onUserInteraction()
    {
        resetDisconnectTimer();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        resetDisconnectTimer();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(showAdActivity != null){
            unregisterReceiver(showAdActivity);
        }
    }
}
