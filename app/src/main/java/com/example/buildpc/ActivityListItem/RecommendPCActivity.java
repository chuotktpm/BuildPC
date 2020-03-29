package com.example.buildpc.ActivityListItem;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.buildpc.Adapter.PC_Adapter;
import com.example.buildpc.Dialog.LoadingDialog;
import com.example.buildpc.Main.MainBuild;
import com.example.buildpc.Model.Model_Cart;
import com.example.buildpc.Model.Model_PC;
import com.example.buildpc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecommendPCActivity extends AppCompatActivity {

    private ListView listView_recommendPC;
    final ArrayList<Model_PC> model_buildArrayList = new ArrayList<>();
    PC_Adapter build_adapter;
    ImageButton imagebutton_pcbacktomain;
    LoadingDialog loadingDialog = new LoadingDialog(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildpc);
        listView_recommendPC = findViewById(R.id.listView_recommendPC);
        imagebutton_pcbacktomain = findViewById(R.id.imagebutton_pcbacktomain);
        String urlPC = "http://android-api.thaolx.com/api/get/pc";
        loadingDialog.startLoadingDialog();
        GetDataPC(urlPC, MainBuild.cart);

        build_adapter = new PC_Adapter(this,R.layout.line_pc_layout, model_buildArrayList);
        listView_recommendPC.setAdapter(build_adapter);

        imagebutton_pcbacktomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetDataPC(String url, Model_Cart cart){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")){
                                JSONArray jsonArray = response.getJSONArray("data");
                                for (int position = 0; position < jsonArray.length(); position++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(position);
                                    //name, image, type, desciption, pc_id, cpu_id, ram_id, main_id, vga_id, psu_id, hdd_id, ssd_id, case_id;
                                    String name = jsonObject.getString("name");
                                    String image = jsonObject.getString("image");
                                    String type = jsonObject.getString("type");
                                    String desciption = jsonObject.getString("description");
                                    int pc_id = jsonObject.getInt("pc_id");
                                    int cpu_id = jsonObject.getInt("cpu_id");
                                    int ram_id = jsonObject.getInt("ram_id");
                                    int main_id = jsonObject.getInt("main_id");
                                    int vga_id = jsonObject.getInt("vga_id");
                                    int psu_id = jsonObject.getInt("psu_id");
                                    int hdd_id = jsonObject.getInt("hdd_id");
                                    int ssd_id = jsonObject.getInt("ssd_id");
                                    int case_id = jsonObject.getInt("case_id");
                                    int price = jsonObject.getInt("price");
                                    model_buildArrayList.add(new Model_PC(image, desciption, name, type, pc_id, cpu_id, ram_id, main_id, psu_id, vga_id, hdd_id, ssd_id, case_id, price));
                                }
                                build_adapter.notifyDataSetChanged();
                                loadingDialog.dismissDialog();
                            }
                            else {
                                loadingDialog.dismissDialog();
                                Toast.makeText(RecommendPCActivity.this, "Ôi không, có vẻ như đã xảy ra lỗi!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RecommendPCActivity.this, "Xảy ra lỗi trong quá trình kết nối máy chủ!", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonObjectRequest);

    }

}
