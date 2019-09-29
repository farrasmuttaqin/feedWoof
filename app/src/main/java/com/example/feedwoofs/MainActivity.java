package com.example.feedwoofs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ProgressBar loading;
    private TextView register;
    private Button tanpaDaftar,login;
    private EditText userName,password;

    private static String URL_LOGIN = "https://mylostphone.000webhostapp.com/server/JSON_FEED_WOOF/api_login_peternak.php";
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        sessionManager = new SessionManager(this);

        register = (TextView) findViewById(R.id.daftar);
        tanpaDaftar = (Button) findViewById(R.id.tanpaDaftar);
        login = findViewById(R.id.login);

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openRegister();
            }
        });

        tanpaDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTanpaDaftar();
            }
        });

        loading = findViewById(R.id.loading);
        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m_userName = userName.getText().toString().trim();
                String m_password = password.getText().toString().trim();

                int a =0 ;

                if (m_userName.isEmpty()){
                    userName.setError("Insert your username");
                    a =1;
                }

                if (m_password.isEmpty()){
                    password.setError("Insert your password");
                    a =1;
                }

                if (a==0){
                    Login(m_userName,m_password);
                }
            }
        });
    }

    protected void openRegister(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    protected void openTanpaDaftar(){
        Intent intent = new Intent(this,HomeActivity2.class);
        startActivity(intent);
    }

    protected void Login(final String username, final String password){
        loading.setVisibility(View.VISIBLE);
        login.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String id_user = object.getString("id_user").trim();
                                    String namaRegister = object.getString("namaRegister").trim();
                                    String emailRegister = object.getString("emailRegister").trim();
                                    String alamatRegister = object.getString("alamatRegister").trim();
                                    String phoneRegister = object.getString("phoneRegister").trim();
                                    String avatar = object.getString("avatar").trim();

                                    sessionManager.createSession(id_user,namaRegister,emailRegister,alamatRegister,phoneRegister,avatar);

                                    Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
//                                    Toast.makeText(MainActivity.this, "Login Success = "+id_user+" | "+namaRegister+" | "+emailRegister+" | "+alamatRegister+" | "+phoneRegister+" | "+avatar, Toast.LENGTH_SHORT).show();
                                }
                                Intent intents = new Intent(MainActivity.this,Splash.class);
                                intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intents.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intents);
                                finish();
                            }
                            if (success.equals("0")){
                                Toast.makeText(MainActivity.this, "Login Failed, username / password wrong",Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                login.setVisibility(View.VISIBLE);
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this,"Error "+e.toString(),Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            login.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Connection error, failed to login",Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        login.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("userName",username);
                params.put("password",password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
