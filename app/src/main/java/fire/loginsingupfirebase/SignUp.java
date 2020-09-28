package fire.loginsingupfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    EditText fullName, email, age, mobileNumber, password, conformPassword;
    String fname, uemail, uage, unumber, upassword, uconformpassword;
    Button signUpClick;
    TextView signUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        fullName = findViewById(R.id.fullName);
        email = findViewById(R.id.userEmailId);
        age = findViewById(R.id.age);
        mobileNumber = findViewById(R.id.mobileNumber);
        password = findViewById(R.id.password);
        conformPassword = findViewById(R.id.confirmPassword);
        signUp = findViewById(R.id.signupTxt);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fname = fullName.getText().toString();
                uemail = email.getText().toString();
                uage = age.getText().toString();
                unumber = mobileNumber.getText().toString();
                upassword = password.getText().toString();
                uconformpassword = conformPassword.getText().toString();
                if (fname.matches("")) {
                    Toast.makeText(SignUp.this, "You did not enter a Name", Toast.LENGTH_SHORT).show();
                    return;
                }if (uemail.matches("")) {
                    Toast.makeText(SignUp.this, "You did not enter a Email", Toast.LENGTH_SHORT).show();
                    return;
                }if (uage.matches("")) {
                    Toast.makeText(SignUp.this, "You did not enter a Age", Toast.LENGTH_SHORT).show();
                    return;
                }if (unumber.matches("")) {
                    Toast.makeText(SignUp.this, "You did not enter a Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }if (upassword.matches("")) {
                    Toast.makeText(SignUp.this, "You did not enter a Password", Toast.LENGTH_SHORT).show();
                    return;
                }if (uconformpassword.matches("")) {
                    Toast.makeText(SignUp.this, "You did not enter a Conform Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(uemail, upassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference myRef = database.getReference("Users");
                                    User obj = new User(fname, uemail, uage, unumber, upassword);
                                    myRef.setValue(obj);
                                    FirebaseUser user = mAuth.getCurrentUser();
                                } else {
                                    Toast.makeText(SignUp.this, "Enter Valid Email & Password.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}