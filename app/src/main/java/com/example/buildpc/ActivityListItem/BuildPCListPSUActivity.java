package com.example.buildpc.ActivityListItem;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.buildpc.Dialog.LoadingDialog;
import com.example.buildpc.Main.MainBuild;
import com.example.buildpc.Model.Model_Cart;
import com.example.buildpc.Model.Model_PSU;
import com.example.buildpc.Adapter.PSU_Adapter;
import com.example.buildpc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BuildPCListPSUActivity extends AppCompatActivity {

    private ListView listView_psu;
    final ArrayList<Model_PSU> model_psuArrayList = new ArrayList<>();
    PSU_Adapter psu_adapter;
    String urlPSU = "http://android-api.thaolx.com/api/get/psus";
    int psu_id, price, size;
    String name, image, brand, efficiency, description;
    private static BuildPCListPSUActivity instance;
    LoadingDialog loadingDialog = new LoadingDialog(BuildPCListPSUActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildpc_list_psu);
        instance = this;

        loadingDialog.startLoadingDialog();
        GetData(urlPSU, MainBuild.cart);

        listView_psu = findViewById(R.id.listView_psu);
        psu_adapter = new PSU_Adapter(this, R.layout.buildpc_layout_psu, model_psuArrayList);
        listView_psu.setAdapter(psu_adapter);
        ImageButton imagebutton_psuback = findViewById(R.id.imagebutton_psuback);
        imagebutton_psuback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private  void  GetData(String url, Model_Cart cart) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        try {
            JSONObject requestData = new JSONObject();
            requestData.put("cpu_id", cart.getCpuID() > 0 ? cart.getCpuID() : null);
            requestData.put("ram_id", cart.getRamID() > 0 ? cart.getRamID() : null);
            requestData.put("main_id", cart.getMainID() > 0 ? cart.getMainID() : null);
            requestData.put("vga_id", cart.getVgaID() > 0 ? cart.getVgaID() : null);
            requestData.put("psu_id",  null);
            requestData.put("ssd_id", cart.getSsdID() > 0 ? cart.getSsdID() : null);
            requestData.put("hdd_id", cart.getHddID() > 0 ? cart.getHddID() : null);
            requestData.put("case_id", cart.getCaseID() > 0 ? cart.getCaseID() : null);

            JsonObjectRequest jsonObjectRequestHDD = new JsonObjectRequest(Request.Method.POST, url, requestData
                    , new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getBoolean("success")) {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                psu_id = jsonObject.getInt("psu_id");
                                name = jsonObject.getString("name");
                                image = jsonObject.getString("image");
                                brand = jsonObject.getString("brand");
                                size = jsonObject.getInt("size");
                                price = jsonObject.getInt("price");
                                efficiency = jsonObject.getString("efficiency_rating");
                                description = jsonObject.getString("description");
                                model_psuArrayList.add(new Model_PSU(image, name, size, brand, efficiency, description, price, psu_id));
                            }
                            psu_adapter.notifyDataSetChanged();
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
                            Toast.makeText(BuildPCListPSUActivity.this, "Lỗi!", Toast.LENGTH_SHORT).show();
                        }
                    });

            requestQueue.add(jsonObjectRequestHDD);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static BuildPCListPSUActivity getInstance() {
        return instance;
    }
}
