package com.dzniox.travel_mate.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.dzniox.travel_mate.R;
import com.dzniox.travel_mate.sharedPref.PrefUtils;
import com.dzniox.travel_mate.ui.fragments.AddSpotFragment;
import com.dzniox.travel_mate.ui.fragments.BookingFragment;
import com.dzniox.travel_mate.ui.fragments.HomeFragment;
import com.dzniox.travel_mate.ui.fragments.ProfileFragment;
import com.dzniox.travel_mate.ui.fragments.ReviewFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView nav_view;
    PrefUtils prefrences;

    private ImageView add_spot;
    private HomeFragment homeFragment;
    private BookingFragment bookingFragment;
    private ProfileFragment profileFragment;
    private AddSpotFragment addSpotFragment;
    private ReviewFragment reviewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefrences = PrefUtils.getInstance(this);
        add_spot = findViewById(R.id.add_spot);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        homeFragment = new HomeFragment();
        bookingFragment = new BookingFragment();
        profileFragment = new ProfileFragment();
        addSpotFragment = new AddSpotFragment();
        reviewFragment = new ReviewFragment();


        nav_view = findViewById(R.id.nav_view);
        LoadFragment(homeFragment);

        if (prefrences.getStringValue("id","").equals("1")){
            add_spot.setVisibility(View.VISIBLE);
        }else {
            add_spot.setVisibility(View.GONE);
        }

        nav_view.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_home:
                    LoadFragment(homeFragment);
                    break;
                case R.id.navigation_booking:
                    LoadFragment(bookingFragment);
                    break;
                case R.id.navigation_review:
                    LoadFragment(reviewFragment);
                    break;
                case R.id.navigation_profile:
                    LoadFragment(profileFragment);
                    break;
            }
            return true;
        });

        add_spot.setOnClickListener(view -> {
            LoadFragment(addSpotFragment);
        });
    }

    public void GoToback(View view) {}

    public void LogOut(View view) {
        prefrences.clear();
        Intent intent = new Intent(this, SplashScreenActivity.class);
        startActivity(intent);
        finish();
    }
    public void LoadFragment(Fragment fragment) {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}