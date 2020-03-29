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
import com.example.buildpc.Model.Model_RAM;
import com.example.buildpc.R;
import com.example.buildpc.Adapter.RAM_Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BuildPCListRAMActivity extends AppCompatActivity {

    final ArrayList<Model_RAM> model_ramArrayList = new ArrayList<>();
    private static BuildPCListRAMActivity instance;
    int price, ram_id, memory_type_id;
    String name, size, image, brand, bus, description;
    RAM_Adapter ram_adapter;
    String urlRAM = "http://android-api.thaolx.com/api/get/rams";

    LoadingDialog loadingDialog = new LoadingDialog(BuildPCListRAMActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_pclist_ram);
        instance = this;

        ListView listView_ram = findViewById(R.id.listView_ram);
        loadingDialog.startLoadingDialog();
        GetData(urlRAM, MainBuild.cart);
        ram_adapter = new RAM_Adapter(this,R.layout.buildpc_layout_ram, model_ramArrayList);
        listView_ram.setAdapter(ram_adapter);

        ImageButton imagebutton_ramback = findViewById(R.id.imagebutton_ramback);
        imagebutton_ramback.setOnClickListener(new View.OnClickListener() {
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
            requestData.put("ram_id",null);
            requestData.put("main_id",cart.getMainID() > 0 ? cart.getMainID() : null);
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
                            ram_id = jsonObject.getInt("ram_id");
                            size = jsonObject.getString("size");
                            image = jsonObject.getString("image");
                            brand = jsonObject.getString("brand");
                            bus = jsonObject.getString("bus");
                            description = jsonObject.getString("description");
                            price = jsonObject.getInt("price");
                            memory_type_id = jsonObject.getInt("memory_type_id");
                            model_ramArrayList.add(new Model_RAM(image, size, brand, bus, name, softType(memory_type_id), price, description, ram_id));
                        }
                        ram_adapter.notifyDataSetChanged();
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
                        Toast.makeText(BuildPCListRAMActivity.this, "Lỗi!", Toast.LENGTH_SHORT).show();
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

    public static BuildPCListRAMActivity getInstance() {
        return instance;
    }
}
