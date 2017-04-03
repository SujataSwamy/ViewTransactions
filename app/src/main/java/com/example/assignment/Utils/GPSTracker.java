package com.example.assignment.Utils;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.example.assignment.R;
import com.example.assignment.activity.MainActivity;
import com.example.assignment.interfaces.LocationCallBack;
import com.example.assignment.restclient.Applib;
import com.example.assignment.restclient.Callback;
import com.example.assignment.restclient.ErrorCodes;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by sujata on 03/04/17.
 */
public class GPSTracker extends Service implements LocationListener {


    // Get Class Name
    private static String TAG = GPSTracker.class.getName();
    private LocationCallBack mLocationListener;

    private Context mContext;

    // flag for GPS Status
    static boolean isGPSEnabled = false;

    // flag for network status
    boolean isNetworkEnabled = false;

    // flag for GPS Tracking is enabled
    boolean isGPSTrackingEnabled = false;
    boolean canGetLocation = false;
    static Location gpsLocation;
    static double latitude;
    static double longitude;
    List<Address> addressesList;
    // How many Geocoder should return our GPSTracker
    int geocoderMaxResults = 1;
    GoogleAPILocation apiLocation;
    // The minimum distance to change updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1000; // 1 K meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
    //private static final long MIN_TIME_BW_UPDATES = 1800000; //30 minutes
    // Declaring a Location Manager
    protected LocationManager locationManager;
    public AlertDialog.Builder builder;
    public AlertDialog alertDialog;
    // Store LocationManager.GPS_PROVIDER or LocationManager.NETWORK_PROVIDER information
    private String provider_info;
    LocationListener locationListener;

    public GPSTracker(Context context, LocationCallBack locationListener) {
        this.mContext = context;
        addressesList = new ArrayList<>();
        this.mLocationListener = locationListener;
        apiLocation = new GoogleAPILocation();
        getLocation();
    }



    public static boolean getIsGPSEnabled() {
        return isGPSEnabled;

    }

    /**
     * Try to get my current location by GPS or Network Provider
     */
    public Location getLocation() {

        try {
            locationManager = (LocationManager) mContext
                    .getSystemService(LOCATION_SERVICE);

            // getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!isGPSEnabled) {
                showSettingsAlert();
                Toast.makeText(mContext, "Please enable Location Services", Toast.LENGTH_LONG).show();
                /*Intent callGPSSettingIntent = new Intent(
                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                if(mContext instanceof DashboardActivity)
                    ((DashboardActivity)mContext).startActivity(callGPSSettingIntent);
                else if(mContext instanceof LoginActivity)
                    ((LoginActivity)mContext).startActivity(callGPSSettingIntent);
                else if(mContext instanceof SearchGarageActivity)
                    ((SearchGarageActivity)mContext).startActivity(callGPSSettingIntent);
                else if(mContext instanceof SearchLocalityActivity)
                    ((SearchLocalityActivity)mContext).startActivity(callGPSSettingIntent);
*/
            }

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else if (!isNetworkEnabled) {
                mLocationListener.onError("No Network Connection", ErrorCodes.NO_NETWORK);
            } else {
                this.canGetLocation = true;
                // First get location from Network Provider
                if (isNetworkEnabled) {
                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.

                    }
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener = new LocationListener() {
                                @Override
                                public void onLocationChanged(Location location) {
//                                    initLocation(location);
                                    mLocationListener.getLocation(latitude+"",longitude+"");
                                }

                                @Override
                                public void onStatusChanged(String provider, int status, Bundle extras) {

                                }

                                @Override
                                public void onProviderEnabled(String provider) {

                                }

                                @Override
                                public void onProviderDisabled(String provider) {

                                }
                            });
                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        gpsLocation = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (gpsLocation != null) {
                            latitude = gpsLocation.getLatitude();
                            longitude = gpsLocation.getLongitude();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (gpsLocation == null) {
                        locationListener = new LocationListener() {
                            @Override
                            public void onLocationChanged(Location location) {
                                initLocation(location);
                            }


                            @Override
                            public void onStatusChanged(String provider, int status, Bundle extras) {

                            }

                            @Override
                            public void onProviderEnabled(String provider) {

                            }

                            @Override
                            public void onProviderDisabled(String provider) {

                            }
                        };
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            gpsLocation = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (gpsLocation != null) {
                                latitude = gpsLocation.getLatitude();
                                longitude = gpsLocation.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return gpsLocation;
    }

    private void initLocation(Location location) {
        gpsLocation = location;
        getGeocoderAddress(mContext);
        if (locationListener != null) {
//            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//
//                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    return;
//                }
//
//            }else{
            locationManager.removeUpdates(locationListener);
//            }
        }

    }


    public boolean canGetLocation() {
        return this.canGetLocation;
    }

    /**
     * Update GPSTracker latitude and longitude
     */
    public void updateGPSCoordinates() {
        if (gpsLocation != null) {
            latitude = gpsLocation.getLatitude();
            longitude = gpsLocation.getLongitude();
        }
    }

    /**
     * GPSTracker latitude getter and setter
     *
     * @return latitude
     */
    public double getLatitude() {
        if (gpsLocation != null) {
            latitude = gpsLocation.getLatitude();
        }

        return latitude;
    }

    /**
     * GPSTracker longitude getter and setter
     *
     * @return
     */
    public double getLongitude() {
        if (gpsLocation != null) {
            longitude = gpsLocation.getLongitude();
        }

        return longitude;
    }

    /**
     * GPSTracker isGPSTrackingEnabled getter.
     * Check GPS/wifi is enabled
     */
    public boolean getIsGPSTrackingEnabled() {

        return this.isGPSTrackingEnabled;
    }

    /**
     * Stop using GPS listener
     * Calling this method will stop using GPS in your app
     */
    public void stopUsingGPS() {
        if (locationManager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.removeUpdates(GPSTracker.this);
        }
    }

    /**
     * Function to show settings alert dialog
     */
    public void showSettingsAlert() {
        builder = new AlertDialog.Builder(mContext);

        //Setting Dialog Title
        builder.setTitle(R.string.GPSAlertDialogTitle);

        //Setting Dialog Message
        builder.setMessage(R.string.GPSAlertDialogMessage);
        builder.setCancelable(false);
        //On Pressing Setting button
        builder.setPositiveButton(R.string.action_settings, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Constants.LOCATION_SETTINGS_CALLED=true;
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);

            }
        });

        //On pressing cancel button
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

              if(mContext instanceof MainActivity)
                    ((MainActivity) mContext).dismissProgressDialog();
            }
        });

        builder.show();
        //alertDialog = builder.show();
    }

    /**
     * Get list of address by latitude and longitude
     *
     * @return null or List<Address>
     */
    public void getGeocoderAddress(Context context) {

        if (gpsLocation != null) {

            Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);

            try {
                /**
                 * Geocoder.getFromLocation - Returns an array of Addresses
                 * that are known to describe the area immediately surrounding the given latitude and longitude.
                 */
                List<Address> addresses = geocoder.getFromLocation(gpsLocation.getLatitude(), gpsLocation.getLongitude(), this.geocoderMaxResults);
                mLocationListener.getLocation(latitude+"",longitude+"");
                if (addresses == null || addresses.size() == 0) {

                    Applib.getApplib().getNetworkClient().getLocationFromApi(latitude + "," + longitude, "true", new Callback<GoogleAPILocation>() {
                        @Override
                        public void onSuccess(GoogleAPILocation googleAPILocation) {
                            apiLocation = googleAPILocation;
//                            returnGeoCOde(apiLocation);

                            if (googleAPILocation != null) {
//                                com.example.assignment.restclient.model.Location loc = getCity(gpsLocation, googleApiLocation);
                                if (googleAPILocation != null) {
                                    double latitude = googleAPILocation.getGeoAddress().get(0).getLatitude();
                                    double longitude = googleAPILocation.getGeoAddress().get(0).getLongitude();
                                    mLocationListener.getLocation(latitude+"",longitude+"");
                                }
                                else mLocationListener.onError("Could not get Location", ErrorCodes.BAD_INPUT);
                            } else {
                                mLocationListener.onError("Could not get Location", ErrorCodes.BAD_INPUT);
                            }
                        }

                        @Override
                        public void onError(String error, ErrorCodes code) {
                            super.onError(error, code);
                        }
                    });
                } else {
                    addressesList.clear();
                    addressesList.addAll(addresses);
                    if (apiLocation != null)
                        apiLocation.setGeoAddress(addresses);
                    returnGeoCOde(apiLocation);

                }

            } catch (IOException e) {
                //e.printStackTrace();
                returnGeoCOde(null);
                Log.e(TAG, "Impossible to connect to Geocoder", e);
            }
        }


    }

    private void returnGeoCOde(GoogleAPILocation googleApiLocation) {
//        if (googleApiLocation != null) {
//            com.example.assignment.restclient.model.Location loc = getCity(gpsLocation, googleApiLocation);
//            if (loc != null)
//                mLocationListener.getLocation(loc);
//            else mLocationListener.onError("Could not get Location", ErrorCodes.BAD_INPUT);
//        } else {
//            mLocationListener.onError("Could not get Location", ErrorCodes.BAD_INPUT);
//        }

    }


    /**
     * Try to get AddressLine
     *
     * @return null or addressLine
     */
    public String getAddressLine(Context context, GoogleAPILocation googleAPILocation) {
        String addressLine = null;
        if (isGPSEnabled) {

//            googleAPILocation=getGeocoderAddress(context);
            if (addressesList != null && addressesList.size() > 0) {
                Address address = addressesList.get(0);
                addressLine = address.getAddressLine(1);

                return addressLine;
            } else {
                //GoogleAPILocation googleAPILocation=new GoogleAPILocation();
                List<GoogleAPILocation.ResultsBean> results = googleAPILocation.getResults();
                addressLine = results.get(0).getAddress_components().get(1).getLong_name();
            }
        }
        return addressLine;
    }

    public String getLocality(Context context, GoogleAPILocation googleApi) {
        String locality;
        if (isGPSEnabled) {
            String address = getAddressLine(context, googleApi);
            if (address != null) {
                if (address.contains(",")) {
                    String[] location = address.split(",");
                    locality = location[location.length - 1];
                } else locality = address;
                return locality;
            } else return null;
        }

        return null;

    }

    /**
     * Try to get Locality
     *
     * @return null or locality
     */
    public com.example.assignment.restclient.model.Location getCity(com.example.assignment.restclient.model.Location gpsLocation, GoogleAPILocation apiLocation) {
        String city;
        com.example.assignment.restclient.model.Location location = new com.example.assignment.restclient.model.Location();
        location.setLongitude(String.valueOf(gpsLocation.getLongitude()));
        location.setLatitude(String.valueOf(gpsLocation.getLatitude()));
        String locality = getLocality(mContext, apiLocation);
        String address_str = null;
        if(addressesList != null && addressesList.size() > 0) {
            for(int c=0; c < addressesList.size() ; c++){
                Address address = addressesList.get(c);
                address_str = address_str + address.getAddressLine(c);
            }
        }

        if (isGPSEnabled) {
//            GoogleAPILomExtras = nullcation addresses = getGeocoderAddress(context);

            if (apiLocation.getGeoAddress() == null || apiLocation.getGeoAddress().size() == 0) {

                List<GoogleAPILocation.ResultsBean> results = apiLocation.getResults();
                if (results != null)
                    city = results.get(0).getAddress_components().get(2).getLong_name();
                else city = "";
            } else {

                city = apiLocation.getGeoAddress().get(0).getLocality();
                locality = apiLocation.getGeoAddress().get(0).getSubLocality();


            }

            if (locality == null)
                return null;

            location.setName(locality);
            location.setCity(city);
            location.setCitySlug(makeSlug(city.toLowerCase()));
            location.setSlug(locality.toLowerCase());
            location.setPincode(apiLocation.getGeoAddress().get(0).getPostalCode());
            location.setAddress(address_str);
        }
        return location;
    }

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public String makeSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    /**
     * Try to get Postal Code
     * @return null or postalCode
     */
    /*public String getPostalCode(Context context) {
        List<Address> addresses = getGeocoderAddress(context);

        if (addresses != null && addresses.size() > 0) {
            Address address = addresses.get(0);
            String postalCode = address.getPostalCode();

            return postalCode;
        } else {
            return null;
        }
    }*/

    /**
     * Try to get CountryName
     *
     * @return null or postalCode
     */
  /*  public String getCountryName(Context context) {
        List<Address> addresses = getGeocoderAddress(context);
        if (addresses != null && addresses.size() > 0) {
            Address address = addresses.get(0);
            String countryName = address.getCountryName();

            return countryName;
        } else {
            return null;
        }
    }*/
    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
