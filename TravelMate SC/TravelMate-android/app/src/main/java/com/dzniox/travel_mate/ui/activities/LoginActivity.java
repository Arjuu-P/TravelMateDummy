package com.dzniox.travel_mate.ui.activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dzniox.travel_mate.R;
import com.dzniox.travel_mate.network.APIClient;
import com.dzniox.travel_mate.network.APIInterface;
import com.dzniox.travel_mate.sharedPref.PrefKeys;
import com.dzniox.travel_mate.sharedPref.PrefUtils;


import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button Login,SignUp;
    private EditText Email,Password;
    APIInterface apiInterface;
    PrefUtils prefrences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        prefrences = PrefUtils.getInstance(this);


        Login = findViewById(R.id.login);
        SignUp = findViewById(R.id.signup);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);

        if (prefrences.getBooleanValue(PrefKeys.IS_LOGGED_IN,false)){
            GoToMainActivity();
        }else {}

        SignUp.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
            finish();
        });

        Login.setOnClickListener(view -> {
            if (Email.getText().toString().trim().equals("") ||
                    Password.getText().toString().trim().equals("")){
                Toast.makeText(this,"Check Credentials",Toast.LENGTH_LONG).show();
            }else {
                DoLogin();
            }
        });

    }

    private void DoLogin() {
        try {
            Call<String> call = apiInterface.loginUser(
                    Email.getText().toString().trim(),
                    Password.getText().toString()
            );
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.code() == 200){
                        try {
                            JSONObject data = new JSONObject(response.body());
                            if (data.getJSONArray("data").length() > 0){
                                prefrences.setValue(PrefKeys.IS_LOGGED_IN, true);
                                prefrences.setValue("name",data.getJSONArray("data").getJSONObject(0)
                                        .getString("name"));
                                prefrences.setValue("email",data.getJSONArray("data").getJSONObject(0)
                                        .getString("email"));
                                prefrences.setValue("phone",data.getJSONArray("data").getJSONObject(0)
                                        .getString("phone"));
                                prefrences.setValue("id",data.getJSONArray("data").getJSONObject(0)
                                        .getString("id"));

                                GoToMainActivity();
                            }
                        }catch (Exception e){
                            Toast.makeText(getBaseContext(),"Check Credentials",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    String a = t.toString();
                }
            });
        }catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    private void GoToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}