package com.example.buildpc.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.example.buildpc.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Registration extends AppCompatActivity {

    EditText editText_user_reg, editText_pass_reg, editText_pass_confirm, editText_name_reg;
    Button button_reg;
    TextView button_backtologin;
    String url = "http://android-api.thaolx.com/api/user/create";
    LoadingDialog loadingDialog = new LoadingDialog(Registration.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Anhxa();

        //Bắt sự kiện khi bấm nút đăng ký
        button_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Nếu 1 EditText bất kỳ bỏ trông thì hiện thông báo
                if(
                        editText_user_reg.getText().toString().equals("")
                        || editText_name_reg.getText().toString().equals("")
                        || editText_pass_reg.getText().toString().equals("")
                        || editText_pass_confirm.getText().toString().equals("")
                ){
                    Toast.makeText(Registration.this, "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                }
                //Nếu không thì check Pass và Confirm Password rồi thực hiện đăng ký
                else {
                    if (editText_pass_reg.getText().toString().equals(editText_pass_confirm.getText().toString())){
                        loadingDialog.startLoadingDialog();
                        Regist(url);
                    }
                    //Thông báo khi Confirm Password không trùng với Password
                    else {
                        Toast.makeText(Registration.this, "Mật khẩu phải trùng khớp với mật khẩu xác minh!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //Quay lại màn hình đăng nhập
        button_backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    //Kết nối API để đăng ký tài khoản mới
    private void Regist(String url){
        RequestQueue queue = Volley.newRequestQueue(this);
        try {

            JSONObject requestData = new JSONObject();
            requestData.put("email", editText_user_reg.getText().toString());
            requestData.put("password", editText_pass_reg.getText().toString());
            requestData.put("name", editText_name_reg.getText().toString().trim());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, requestData,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getBoolean("success")) {
                                    Toast.makeText(Registration.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                    loadingDialog.dismissDialog();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Registration.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                            loadingDialog.dismissDialog();
                            error.printStackTrace();
                        }
                    }
            );
            // Add the request to the RequestQueue.
            queue.add(jsonObjectRequest);
        } catch (JSONException error){
            error.printStackTrace();
        }


    }

    //Ánh xạ
    private void Anhxa() {
        editText_user_reg = findViewById(R.id.edt_username_reg);
        editText_pass_reg = findViewById(R.id.edt_password_reg);
        editText_pass_confirm = findViewById(R.id.edt_password_reg_again);
        editText_name_reg = findViewById(R.id.edt_name_reg);
        button_reg = findViewById(R.id.btn_regist);
        button_backtologin = findViewById(R.id.btn_backtologin);
    }
}
