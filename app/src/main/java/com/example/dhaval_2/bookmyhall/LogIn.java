package com.example.dhaval_2.bookmyhall;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LogIn extends AppCompatActivity {

    EditText etEmail,etPass;
    Button btLogIn,btSignUp;
    TextView tvSignUp,tvLogIn;

    FirebaseAuth myAuth;
    FirebaseAuth.AuthStateListener myAuthListener;

    ProgressDialog pd;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //////////////////LINKING//////////////////////////////////
        etEmail=(EditText)findViewById(R.id.etEmail);
        etPass =(EditText)findViewById((R.id.etPassword));
        btLogIn=(Button)findViewById(R.id.btLogIn);
        tvSignUp=(TextView)findViewById(R.id.tvSignUp);
        btSignUp =(Button)findViewById(R.id.btSignUp);
        tvLogIn=(TextView)findViewById(R.id.tvLogIn);
////////////////////////////////////////////////////////////////////////////

        pd=new ProgressDialog(this);


        btSignUp.setVisibility(View.INVISIBLE);
        tvLogIn.setVisibility(View.INVISIBLE);

        myAuth=FirebaseAuth.getInstance();

        myAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=myAuth.getCurrentUser();

                if(user!=null &&user.isEmailVerified())
                {
                    //startActivity(new Intent(LogIn.this,MainActivity.class));
                    finish();
                }

            }
        };

        btLogIn.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    pd.setMessage("Logging In......");
                    pd.show();

                    String email = etEmail.getText().toString();
                    final String password = etPass.getText().toString();

                    if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                        Toast.makeText(getApplicationContext(), "Enter Valid Username or Password", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                        return;
                    }





                    myAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>() {

                                public void onComplete(@NonNull Task<AuthResult> task) {

                                        pd.dismiss();

                                    if (!task.isSuccessful()) {
                                        // there was an error
                                        Toast.makeText(LogIn.this,"LogIn Problem",Toast.LENGTH_SHORT).show();
                                    } else {

                                        FirebaseUser user = myAuth.getCurrentUser();


                                        if (!user.isEmailVerified()) {
                                            Toast.makeText(LogIn.this, "Emai is not Verified", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                }
                            });

                }






        });

        btSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    pd.show();
                String email = etEmail.getText().toString().trim();
                String password = etPass.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter valid Email_Id or Password", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                    return;
                }



                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                    return;
                }



                myAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful())
                                {
                                    pd.dismiss();
                                    FirebaseUser user=myAuth.getCurrentUser();

                                    Toast.makeText(LogIn.this,"Verification has been sent to "+user.getEmail(),Toast.LENGTH_SHORT).show();

                                    myAuth.getCurrentUser().sendEmailVerification();
                                    myAuth.signOut();


                                    etPass.setText("");
                                    etEmail.setText("");

                                }
                                if(!task.isSuccessful())
                                {
                                    pd.dismiss();
                                    Toast.makeText(LogIn.this,"Enter valid UserId",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });


        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btSignUp.setVisibility(v.VISIBLE);
                tvLogIn.setVisibility(v.VISIBLE);
                btLogIn.setVisibility(View.INVISIBLE);
                tvSignUp.setVisibility(View.INVISIBLE);
            }
        });

        tvLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvLogIn.setVisibility(View.INVISIBLE);
                btLogIn.setVisibility(View.VISIBLE);
                tvSignUp.setVisibility(View.VISIBLE);
                btSignUp.setVisibility(View.INVISIBLE);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        myAuth.addAuthStateListener(myAuthListener);
    }







}
