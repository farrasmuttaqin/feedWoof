package com.example.feedwoofs;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity  {

    private ProgressBar loading;
    private EditText namaRegister,emailRegister,usernameRegister,passwordRegister,passwordConfirmRegister,alamatRegister,phoneRegister;
    private ImageView imageRegister;
    private Button register;
    Bitmap bitmap;

    final int CODE_GALLERY_REQUEST= 999;
    private static String URL_REGIST = "https://mylostphone.000webhostapp.com/server/JSON_FEED_WOOF/api_register_peternak.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml("<font color='#555424'>Daftar</font>"));

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        imageRegister = (ImageView) findViewById(R.id.imageRegister);
        imageRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        RegisterActivity.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        CODE_GALLERY_REQUEST
                );
            }
        });

        loading=findViewById(R.id.loading);
        namaRegister = findViewById(R.id.namaRegister);
        emailRegister = findViewById(R.id.emailRegister);
        usernameRegister = findViewById(R.id.usernameRegister);
        passwordRegister = findViewById(R.id.passwordRegister);
        passwordConfirmRegister = findViewById(R.id.passwordConfirmRegister);
        alamatRegister = findViewById(R.id.alamatRegister);
        phoneRegister = findViewById(R.id.phoneRegister);

        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int a = 0;
                String m_namaRegister = namaRegister.getText().toString().trim();
                String m_emailRegister = emailRegister.getText().toString().trim();
                String m_usernameRegister = usernameRegister.getText().toString().trim();
                String m_passwordRegister = passwordRegister.getText().toString().trim();
                String m_passwordConfirmRegister = passwordConfirmRegister.getText().toString().trim();
                String m_alamatRegister = alamatRegister.getText().toString().trim();
                String m_phoneRegister = phoneRegister.getText().toString().trim();

                if (m_namaRegister.isEmpty()||m_namaRegister.length()<4){
                    namaRegister.setError("Please Input Valid Full Name");
                    a =1;
                }

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (m_emailRegister.isEmpty()||!m_emailRegister.matches(emailPattern)){
                    emailRegister.setError("Please Input Valid Email");
                    a =1;
                }

                if (m_usernameRegister.isEmpty()||m_usernameRegister.length()<6){
                    usernameRegister.setError("Username should be at least 6 characters length");
                    a =1;
                }
                if (m_passwordRegister.isEmpty()||m_passwordRegister.length()<6){
                    passwordRegister.setError("Passwords should be at least 6 characters length");
                    a =1;
                }
                if (m_passwordConfirmRegister.isEmpty()){
                    passwordConfirmRegister.setError("Please Input Confirm Password");
                    a =1;
                }else{
                    if (!m_passwordRegister.equals(m_passwordConfirmRegister)){
                        passwordConfirmRegister.setError("Password and confirm password don't match");
                        a =1;
                    }
                }

                if (m_alamatRegister.isEmpty()){
                    alamatRegister.setError("Please Input your Address");
                    a =1;
                }

                String phonePattern = "^(^\\+62\\s?|^0)(\\d{3,4}-?){2}\\d{3,5}$";
                if (!m_phoneRegister.matches(phonePattern)){
                    phoneRegister.setError("Phone number must be correct");
                    a =1;
                }


                if (a==0){
                    Regist();
                }
            }
        });
    }

    @Override
    public boolean onNavigateUp(){
        finish();
        return true;
    }

    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] imageBytes = outputStream.toByteArray();

        String encodedImage = Base64.encodeToString(imageBytes,Base64.DEFAULT);
        return encodedImage;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == CODE_GALLERY_REQUEST){
            if (grantResults.length> 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Select Image"),CODE_GALLERY_REQUEST);
            }else{
                Toast.makeText(getApplicationContext(),"You dont have permission",Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == CODE_GALLERY_REQUEST && resultCode == RESULT_OK && data!= null){
            Uri filePath = data.getData();
            try{
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageRegister.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    private void Regist(){
        loading.setVisibility(View.VISIBLE);
        register.setVisibility(View.GONE);

        final String namaRegister = this.namaRegister.getText().toString().trim();
        final String emailRegister = this.emailRegister.getText().toString().trim();
        final String usernameRegister = this.usernameRegister.getText().toString().trim();
        final String passwordRegister = this.passwordRegister.getText().toString().trim();
        final String passwordConfirmRegister = this.passwordConfirmRegister.getText().toString().trim();
        final String alamatRegister = this.alamatRegister.getText().toString().trim();
        final String phoneRegister = this.phoneRegister.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success =  jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(RegisterActivity.this,"Register Success!!",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                            if (success.equals("0")){
                                Toast.makeText(RegisterActivity.this,"Register failed, Username already taken",Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                register.setVisibility(View.VISIBLE);
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this,"Register Error!! "+e.toString(),Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            register.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this,"You must choose your Profile Picture by clicking green circle above!",Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        register.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                String imageData = imageToString(bitmap);

                params.put("namaRegister",namaRegister);
                params.put("emailRegister",emailRegister);
                params.put("usernameRegister",usernameRegister);
                params.put("passwordConfirmRegister",passwordConfirmRegister);
                params.put("alamatRegister",alamatRegister);
                params.put("phoneRegister",phoneRegister);
                params.put("avatar",imageData);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
