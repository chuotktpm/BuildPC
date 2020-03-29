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
import com.example.buildpc.Adapter.HDD_Adapter;
import com.example.buildpc.Dialog.LoadingDialog;
import com.example.buildpc.Main.MainBuild;
import com.example.buildpc.Model.Model_Cart;
import com.example.buildpc.Model.Model_HDD;
import com.example.buildpc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BuildPCListHDDActivity extends AppCompatActivity {

    private ListView listView_hdd;
    final ArrayList<Model_HDD> model_hddArrayList = new ArrayList<>();
    HDD_Adapter hdd_adapter;
    String urlHDD = "http://android-api.thaolx.com/api/get/hdds";
    int hdd_id, price;
    String name, image, brand, size, description;
    private static BuildPCListHDDActivity instance;
    LoadingDialog loadingDialog = new LoadingDialog(BuildPCListHDDActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_pclist_hdd);
        instance = this;

        loadingDialog.startLoadingDialog();
        GetData(urlHDD, MainBuild.cart);

        listView_hdd = findViewById(R.id.listView_hdd);


        hdd_adapter = new HDD_Adapter(this, R.layout.buildpc_layout_hdd, model_hddArrayList);
        listView_hdd.setAdapter(hdd_adapter);
        ImageButton imagebutton_hddback = findViewById(R.id.imagebutton_hddback);
        imagebutton_hddback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private  void  GetData(String url, Model_Cart cart){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject requestData = new JSONObject();
        try {
            requestData.put("cpu_id", cart.getCpuID() > 0 ? cart.getCpuID(): null);
            requestData.put("ram_id", cart.getRamID() > 0 ? cart.getRamID(): null);
            requestData.put("main_id", cart.getMainID() > 0 ? cart.getMainID(): null);
            requestData.put("vga_id", cart.getVgaID() > 0 ? cart.getVgaID(): null);
            requestData.put("psu_id", cart.getPsuID() > 0 ? cart.getPsuID(): null);
            requestData.put("ssd_id", cart.getSsdID() > 0 ? cart.getSsdID(): null);
            requestData.put("case_id", cart.getCaseID() > 0 ? cart.getCaseID(): null);
            requestData.put("hdd_id",  null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequestHDD = new JsonObjectRequest(Request.Method.POST, url, requestData
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("success")) {
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            hdd_id = jsonObject.getInt("hdd_id");
                            name = jsonObject.getString("name");
                            image = jsonObject.getString("image");
                            brand = jsonObject.getString("brand");
                            size = jsonObject.getString("size");
                            price = jsonObject.getInt("price");
                            description = jsonObject.getString("description");
                            model_hddArrayList.add(new Model_HDD(image, name, size , brand, description, price, hdd_id));
                        }
                        hdd_adapter.notifyDataSetChanged();
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
                        Toast.makeText(BuildPCListHDDActivity.this, "Lỗi!", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonObjectRequestHDD);
    }

    public static BuildPCListHDDActivity getInstance() {
        return instance;
    }
}
