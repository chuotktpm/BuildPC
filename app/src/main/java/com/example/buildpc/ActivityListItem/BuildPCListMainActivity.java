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
import com.example.buildpc.Adapter.Main_Adapter;
import com.example.buildpc.Dialog.LoadingDialog;
import com.example.buildpc.Main.MainBuild;
import com.example.buildpc.Model.Model_Cart;
import com.example.buildpc.Model.Model_Main;
import com.example.buildpc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BuildPCListMainActivity extends AppCompatActivity {

    private ListView listView_main;
    final ArrayList<Model_Main> model_mainArrayList = new ArrayList<>();
    private static BuildPCListMainActivity instance;
    int price, main_id, memory_type_id;
    String name, socket, image, brand, model, description;
    Main_Adapter main_adapter;
    private ImageButton imagebutton_mainback;
    String urlMain = "http://android-api.thaolx.com/api/get/mains";

    LoadingDialog loadingDialog = new LoadingDialog(BuildPCListMainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_pclist_main);
        instance = this;

        listView_main = findViewById(R.id.listView_main);

        loadingDialog.startLoadingDialog();
        GetData(urlMain, MainBuild.cart);

        main_adapter = new Main_Adapter(this,R.layout.buildpc_layout_main, model_mainArrayList);
        listView_main.setAdapter(main_adapter);

        imagebutton_mainback = findViewById(R.id.imagebutton_mainsback);
        imagebutton_mainback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private  void  GetData(String url, Model_Cart cart){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        try {
            JSONObject requestData = new JSONObject();
            requestData.put("cpu_id",cart.getCpuID() > 0 ? cart.getCpuID() : null);
            requestData.put("ram_id",cart.getRamID() > 0 ? cart.getRamID(): null);
            requestData.put("main_id", null);
            requestData.put("vga_id",cart.getVgaID() > 0 ? cart.getVgaID() : null);
            requestData.put("psu_id",cart.getPsuID() > 0 ? cart.getPsuID() : null);
            requestData.put("ssd_id",cart.getSsdID() > 0 ? cart.getSsdID() : null);
            requestData.put("hdd_id",cart.getHddID() > 0 ? cart.getHddID() : null);
            requestData.put("case_id",cart.getCaseID() > 0 ? cart.getCaseID() : null);
            JsonObjectRequest jsonObjectRequestRAM = new JsonObjectRequest(Request.Method.POST, url, requestData
                    , new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getBoolean("success")) {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                name = jsonObject.getString("name");
                                main_id = jsonObject.getInt("main_id");
                                image = jsonObject.getString("image");
                                socket = jsonObject.getString("socket");
                                brand = jsonObject.getString("brand");
                                model = jsonObject.getString("model");
                                description = jsonObject.getString("description");
                                price = jsonObject.getInt("price");
                                memory_type_id = jsonObject.getInt("memory_type_id");
                                model_mainArrayList.add(new Model_Main(image, socket, brand, name, description, price, main_id, softType(memory_type_id)));
                            }
                            main_adapter.notifyDataSetChanged();
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
                            Toast.makeText(BuildPCListMainActivity.this, "Lỗi!", Toast.LENGTH_SHORT).show();
                        }
                    });

            requestQueue.add(jsonObjectRequestRAM);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String softType(int memory_type_id){
        String memory_type = "";
        switch (memory_type_id){
            case 1:
                memory_type = "DDR4";
                break;
            case 2:
                memory_type = "DDR3";
                break;
            case 3:
                memory_type = "DDR3L";
                break;
        }
        return memory_type;
    }

    public static BuildPCListMainActivity getInstance() {
        return instance;
    }


}
