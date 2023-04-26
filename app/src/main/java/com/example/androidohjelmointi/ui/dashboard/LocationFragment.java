package com.example.androidohjelmointi.ui.dashboard;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidohjelmointi.R;
import com.example.androidohjelmointi.databinding.FragmentLocationBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Locale;

public class LocationFragment extends Fragment {

    private FragmentLocationBinding binding;
    TextInputLayout latitude;
    Location lastlocation;
    TextInputEditText latitudeField;
    TextInputLayout Longitude;
    TextInputEditText longitudeField;
    LocationManager locationManager;
    LocationListener locationListener;
    public static final String TAG="Dashboard";
    TextInputEditText addressField;

    //Location location;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);




// permission has been granted, continue as usual


        binding = FragmentLocationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        latitudeField = root.findViewById(R.id.textFieldLatitudeField);

        longitudeField = root.findViewById(R.id.textFieldLongitudeField);

        addressField = root.findViewById(R.id.textFieldAddressField);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Button buttonLocation;
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {

                double latValue = location.getLatitude();
                String latConv = String.valueOf(latValue);
                double  lonValue = location.getLongitude();
                String lonConv = String.valueOf(lonValue);

                latitudeField.setText(latConv);
                longitudeField.setText(lonConv);
                addressField.setText(getAddress(location));
                lastlocation = location;

                //Log.e(TAG,"moro");

                //convert double to string java
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProvideEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }

        };
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
           // Log.e(TAG, "latitudeField")
            return root;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, locationListener);
        Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        //final TextView textView = binding.textDashboard;
        //dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        //LatitudeField = binding.textFieldLatitudeField;


        if(lastLocation !=null) {
            latitudeField.setText(Double.toString(lastLocation.getLatitude()));
            longitudeField.setText(Double.toString(lastLocation.getLongitude()));
            addressField.setText(getAddress(lastLocation));

        }

        return root;
    }
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private String getAddress(Location location){
        String currentAddress="";
        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());

            List<Address> addresses = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    1);
            Address address = addresses.get(0);
            currentAddress = address.getAddressLine(0);
        }catch (Exception e){
            Log.e(TAG, e.getMessage());
        }
        return currentAddress;
    }
}