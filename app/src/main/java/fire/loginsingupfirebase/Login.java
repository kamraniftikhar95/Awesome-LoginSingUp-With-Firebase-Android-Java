package fire.loginsingupfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    Button signUpClick, loginClick;
    EditText email, password;
    String em, pass;
    ProgressDialog progress;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signUpClick = findViewById(R.id.signupBtn);
        email = findViewById(R.id.login_emailid);
        password = findViewById(R.id.login_password);
        loginClick = findViewById(R.id.loginBtn);
        mAuth = FirebaseAuth.getInstance();
        signUpClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
        loginClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                em = email.getText().toString().trim();
                pass = password.getText().toString().trim();
                if (em.matches("")) {
                    Toast.makeText(Login.this, "You did not enter a Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pass.matches("")) {
                    Toast.makeText(Login.this, "You did not enter a Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                progress = ProgressDialog.show(Login.this, "Authentication",
                        "Waiting for Authentication", true);
                mAuth.signInWithEmailAndPassword(em, pass)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progress.dismiss();
                                    Intent intent = new Intent(Login.this, CongratulationActivity.class);
                                    startActivity(intent);
                                    FirebaseUser user = mAuth.getCurrentUser();
                                } else {
                                    progress.dismiss();
                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}