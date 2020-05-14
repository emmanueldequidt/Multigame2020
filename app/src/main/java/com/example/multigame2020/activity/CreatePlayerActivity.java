package com.example.multigame2020.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.multigame2020.R;
import com.example.multigame2020.manager.ProfileManager;
import com.example.multigame2020.model.Player;
import com.example.multigame2020.utils.ActivityUtils;
import com.squareup.picasso.Picasso;

public class CreatePlayerActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 1000;
    private static final int REQUEST_LOCATION = 2000;

    private ImageView imageView;
    private EditText name;
    private EditText firstName;
    private EditText age;
    private EditText localisation;
    private ImageView localisationBt;
    private Button player;
    private Button validate;

    private String imageUrl;
    private String userLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_player_activity);

        imageView = findViewById(R.id.create_player_image);
        name = findViewById(R.id.create_player_name);
        firstName = findViewById(R.id.create_player_firstname);
        age = findViewById(R.id.create_player_age);
        localisation = findViewById(R.id.create_player_localisation);
        localisationBt = findViewById(R.id.create_player_localisation_bt);
        player = findViewById(R.id.create_player_player_bt);
        validate = findViewById(R.id.create_player_validate);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(Intent.createChooser(intent, "Choix de la photo"), PICK_IMAGE);
            }
        });

        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtils.launchActivity(CreatePlayerActivity.this, DisplayPlayersActivity.class, ActivityUtils.TYPE_SLIDE, false);
            }
        });

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!name.getText().toString().isEmpty() && !firstName.getText().toString().isEmpty()
                        && !age.getText().toString().isEmpty() && imageUrl != null &&
                        (!localisation.getText().toString().isEmpty() || userLocation != null)) {
                    Player player = new Player(name.getText().toString(), firstName.getText().toString(), age.getText().toString(),
                            localisation.getText().toString().isEmpty() ? userLocation : localisation.getText().toString(), imageUrl);
                    ProfileManager.getInstance().setPlayer(player);
                    ActivityUtils.launchActivity(CreatePlayerActivity.this, MainActivity.class, ActivityUtils.TYPE_SLIDE);
                } else {
                    Toast.makeText(CreatePlayerActivity.this, "Informations manquantes !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        localisationBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(CreatePlayerActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(CreatePlayerActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CreatePlayerActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                } else {
                    getUserLocation();
                }
            }
        });
    }

    @SuppressLint("MissingPermission")
    private void getUserLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        locationManager.requestSingleUpdate(criteria, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                userLocation = "La location de l’utilisateur est :" + location.getLatitude() + " : " + location.getLongitude();
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
        }, null);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION) {
            if (ActivityCompat.checkSelfPermission(CreatePlayerActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(CreatePlayerActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                getUserLocation();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && data != null) {
            Picasso.get().load(data.getData()).into(imageView);
            imageUrl = data.getDataString();
        }
    }
}