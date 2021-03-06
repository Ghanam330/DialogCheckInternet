package com.example.dialogcheckinternetconnection.ui.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dialogcheckinternetconnection.R;
import com.example.dialogcheckinternetconnection.ui.MainActivity2;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    private EditText edt_name, edt_email, edt_password;
    private Button btn_signup;
    private TextView txt_signin;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    SharedPreferences sharedPreferences;
    String name,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edt_name = findViewById(R.id.edt_name_signup);
        edt_email = findViewById(R.id.edt_email_signup);
        edt_password = findViewById(R.id.edt_password_signup);
        btn_signup = findViewById(R.id.btn_signUp);
        txt_signin = findViewById(R.id.txt_signin);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        txt_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }


    private void signUp() {
        name = edt_name.getText().toString();
        email = edt_email.getText().toString();
        password = edt_password.getText().toString();
        if (email.isEmpty()) {
            edt_email.setError("please write email");
            return;
        } else {
            edt_email.setError(null);
        }
        if (name.isEmpty()) {
            edt_name.setError("Please write Name");
            return;
        } else {
            edt_name.setError(null);
        }
        if (password.isEmpty()) {
            edt_password.setError("Please write password");
            return;
        } else {
            edt_password.setError(null);
        }
        if (password.length() < 6) {
            edt_password.setError("Please write Long password");
            return;
        } else {
            edt_password.setError(null);
        }
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "User Created Successfully", Toast.LENGTH_SHORT).show();
                sendData();
                startActivity(new Intent(SignUp.this, MainActivity2.class));
                finish();
            } else {
                Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

   public void sendData() {
        sharedPreferences = getSharedPreferences("SHARD_PRE", MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("Name",name);
        editor.putString("Email",email);
        editor.apply();

    }
}



