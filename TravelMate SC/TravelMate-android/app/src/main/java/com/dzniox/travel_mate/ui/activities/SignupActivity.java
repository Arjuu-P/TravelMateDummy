package com.dzniox.travel_mate.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dzniox.travel_mate.R;
import com.dzniox.travel_mate.network.APIClient;
import com.dzniox.travel_mate.network.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private Button SignUp;
    private EditText Name,Email,Password,Phone;
    private TextView Login;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        SignUp = findViewById(R.id.signup);
        Name = findViewById(R.id.name);
        Email = findViewById(R.id.email);
        Phone = findViewById(R.id.phone);
        Password = findViewById(R.id.password);
        Login = findViewById(R.id.login);

        Login.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        SignUp.setOnClickListener(view -> {
            DoSignUp(Name.getText().toString().trim(),
                    Email.getText().toString().trim(),
                    Password.getText().toString().trim(),
                    Phone.getText().toString().trim()
                    );
        });
    }

    private void DoSignUp(String name, String email, String password, String phone) {
        if (name.equals("") || email.equals("") || password.equals("") || phone.equals("")){
            Toast.makeText(this,"Check Fields",Toast.LENGTH_LONG).show();
        }else {
            Call<String> call = apiInterface.signUpUser(email,password,name,phone);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.code() == 200){
                        GoToLoginActivity();
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                }
            });
        }
    }

    private void GoToLoginActivity() {
        Toast.makeText(this,"SignUp Completed",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}