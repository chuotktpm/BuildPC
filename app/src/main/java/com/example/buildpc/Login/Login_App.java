package com.example.buildpc.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.buildpc.Dialog.LoadingDialog;
import com.example.buildpc.Main.MainActivity;
import com.example.buildpc.R;

import org.json.JSONException;
import org.json.JSONObject;
    //Khai báo lớp thư viện
public class Login_App extends AppCompatActivity {

    EditText edt_username, edt_password;
    TextView btn_signup;
    Button btn_login;
    CheckBox cb_login;
    LoadingDialog loadingDialog = new LoadingDialog(Login_App.this);

    //Share dữ liệu để nhớ mật khẩu
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String USERNAME = "userNameKey";
    public static final String PASS = "passKey";
    public static final String REMEMBER = "remember";

    String url = "http://android-api.thaolx.com/api/user/login";

    @Override
    public void onBackPressed() {
        AlertDialog.Builder logoutDialog = new AlertDialog.Builder(Login_App.this);
        logoutDialog.setMessage("Thoát ứng dụng?");
        logoutDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        logoutDialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        logoutDialog.setCancelable(false);
        logoutDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__app);
        //Hàm ánh xạ
        Anhxa();
        //Load dữ liệu khi mở màn hình Login
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        loadData();

        //Bắt sự kiện khi bấm nút Login
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Nếu người dùng tích vào "Nhớ mật khẩu" thì saveData
                if (cb_login.isChecked()){
                    saveData(edt_username.getText().toString(), edt_password.getText().toString());
                }
                //Nếu không thì clearData
                else {
                    clearData();
                }
                //Nếu EditText trống thì hiện thông báo
                if (edt_username.getText().toString().equals("")||edt_password.getText().toString().equals("")){
                    Toast.makeText(Login_App.this, "Tài khoản hoặc mật khẩu không được để trống!", Toast.LENGTH_SHORT).show();
                }
                //Nếu không thì bật DialogLoading và kết nối API Login
                else {
                    loadingDialog.startLoadingDialog();
                    Login(url);
                }
            }
        });

        //Click Regist
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_App.this, Registration.class));
            }
        });
    }

    //Xóa dữ liệu
    private void clearData() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
    }

    //Lưu dữ liệu
    private void saveData(String username, String Pass) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(USERNAME, username);
        editor.putString(PASS, Pass);
        editor.putBoolean(REMEMBER,cb_login.isChecked());
        editor.commit();
    }

    //Load data khi vào màn hình Login, có dữ liệu đã lưu sẽ load lên, không thì thôi
    private void loadData() {
        if(sharedpreferences.getBoolean(REMEMBER,false)) {
            edt_username.setText(sharedpreferences.getString(USERNAME, ""));
            edt_password.setText(sharedpreferences.getString(PASS, ""));
            cb_login.setChecked(true);
        }
        else
            cb_login.setChecked(false);

    }

    //Kết nối API để đăng nhập
    private void Login(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);
        try {
            JSONObject requestData = new JSONObject();

            requestData.put("email", edt_username.getText().toString());
            requestData.put("password", edt_password.getText().toString());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, requestData,
                    new Response.Listener<JSONObject>() {
                        //Bắt sự kiện nếu thành công
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                //Check response
                                if (response.getBoolean("success")){
                                    JSONObject object = response.getJSONObject("user");
                                    String name = object.getString("name");

                                    Intent intent_main = new Intent(Login_App.this, MainActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("UserName",name);
                                    intent_main.putExtras(bundle);

                                    Toast.makeText(Login_App.this, "Welcome " + name + "!", Toast.LENGTH_SHORT).show();
                                    startActivity(intent_main);
                                    loadingDialog.dismissDialog();
                                }
                                else{
                                    Toast.makeText(Login_App.this, "Thông tin tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                                    loadingDialog.dismissDialog();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    //Bắt sự kiện nếu lỗi (Mất mạng, không kết nối được sever,...)
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Login_App.this, "Vui lòng kiểm tra đường truyền hoặc kết nối mạng!", Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                        }
                    });

            // Add the request to the RequestQueue.
            queue.add(jsonObjectRequest);

        } catch (JSONException error) {
            error.printStackTrace();
        }
    }

    private void Anhxa() {
        btn_login = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.btn_signup);
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        cb_login = findViewById(R.id.cb_login);
    }
}
