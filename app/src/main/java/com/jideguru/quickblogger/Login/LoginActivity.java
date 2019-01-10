package com.jideguru.quickblogger.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;

import com.google.api.services.blogger.BloggerScopes;
import com.google.firebase.auth.FirebaseAuth;


import com.google.firebase.auth.FirebaseUser;
import com.jideguru.quickblogger.Blogs.BlogActivity;
import com.jideguru.quickblogger.R;
import com.jideguru.quickblogger.Util.Method;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import es.dmoral.toasty.Toasty;


public class LoginActivity extends AppCompatActivity {

    private final static int RC_SIGN_IN = 2;
    SignInButton signInButton;
    FirebaseAuth mAuth;
    GoogleApiClient mGoogleSignInClient;
    FirebaseAuth mAuthListener;
    String TAG = "LoginActivity";
    ProgressDialog mDialog;

    public static final String mypreference = "mypref";
    private Method method;
    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;


    @Override
    protected void onStart() {
        super.onStart();
        //mAuth.addAuthStateListener(mAuthListener);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        method = new Method(LoginActivity.this);
        pref = getSharedPreferences(mypreference, 0); // 0 - for private mode
        editor = pref.edit();

        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        mAuth = FirebaseAuth.getInstance();

//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                if (FirebaseAuth.getCurrentUser() != null){
//                    startActivity(new Intent(LoginActivity.this, BlogActivity.class));
//                }
//            }
//        };


        String serverClientId = getString(R.string.server_client_id);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(BloggerScopes.BLOGGER))
                .requestServerAuthCode(serverClientId)
//                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        //mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mGoogleSignInClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                        Toasty.error(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT)
                                .show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        Button sign_up = (Button) findViewById(R.id.sign_up_btn);
        sign_up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://blogger.com"));
                startActivity(browserIntent);
            }
        });

}


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleSignInClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        showProgressDialog();
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
//                firebaseAuthWithGoogle(account);
//                String idToken = account.getIdToken();
                String authCode = account.getServerAuthCode();
                final String userName = account.getDisplayName();
                final String userEmail = account.getEmail();
                final String userPhoto = String.valueOf(account.getPhotoUrl());

//                Log.i("THETOKEN", idToken);
//                Log.i("THETOKEN1", authCode);


                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new FormEncodingBuilder()
                        .add("grant_type", "authorization_code")
                        .add("client_id", getString(R.string.server_client_id))
                        .add("client_secret", "M2hAPXdk9u7BBufJaCv9fGTq")
                        .add("redirect_uri","")
                        .add("code", authCode)
                        .build();
                final Request request = new Request.Builder()
                        .url("https://www.googleapis.com/oauth2/v4/token")
                        .post(requestBody)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(final Request request, final IOException e) {
                        Log.e("THETOKEN", e.toString());
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            final String message = jsonObject.toString(5);
//                            Log.i("THETOKEN", message);
                            String access_token = jsonObject.getString("access_token");
                            method.editor.putBoolean(method.pref_login, true);
                            method.editor.putString(method.userName, userName);
                            method.editor.putString(method.userEmail, userEmail);
                            method.editor.putString(method.userPhoto, userPhoto);
                            method.editor.putString(method.accessToken, access_token);
                            method.editor.commit();

                            Log.i("THETOKEN",access_token);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


                updateUI(true);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                Toasty.error(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT)
                        .show();
                updateUI(false);
            }
            hideProgressDialog();
        }
    }

//    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
//
//        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
//        showProgressDialog();
//
//
//        AuthCredential credential = GoogleAuthProvider.getCredential(account.getServerAuthCode(), null);
//
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Toasty.success(LoginActivity.this, "Welcome to Quick Blogger", Toast.LENGTH_LONG)
//                                    .show();
//
//                            Log.d(TAG, "signInWithCredential:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//
//                            updateUI(true);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
//                            Toasty.error(LoginActivity.this, "Auth Failed", Toast.LENGTH_SHORT)
//                                    .show();
//                            updateUI(false);
//                        }
//
//                        hideProgressDialog();
//                    }
//                });
//
//    }

    private void updateUI(boolean isLogin) {
        hideProgressDialog();
        if(isLogin){

            Intent intent = new Intent (LoginActivity.this, BlogActivity.class);
            startActivity(intent);

        } else {


            Intent intent = new Intent (LoginActivity.this, LoginActivity.class);
            startActivity(intent);
        }

    }

    private void showProgressDialog(){

        mDialog= new ProgressDialog(LoginActivity.this);
        mDialog.setMessage("Login in progress, Please Wait...");
        mDialog.show();
    }
    private void hideProgressDialog(){
        mDialog.dismiss();
    }


}
