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
import com.example.buildpc.Model.Model_VGA;
import com.example.buildpc.R;
import com.example.buildpc.Adapter.VGA_Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BuildPCListVGAActivity extends AppCompatActivity {

    private static BuildPCListVGAActivity instance;
    private ListView listView_vga;
    final ArrayList<Model_VGA> model_vgaArrayList = new ArrayList<>();
    VGA_Adapter vga_adapter;
    String urlVGA = "http://android-api.thaolx.com/api/get/vgas";
    int vga_id, min_power, price;
    String name, image, brand, size, model, gpu, description;
    LoadingDialog loadingDialog = new LoadingDialog(BuildPCListVGAActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildpc_listview_vga);

        instance = this;

        listView_vga = findViewById(R.id.listView_vga);

        loadingDialog.startLoadingDialog();
        GetData(urlVGA, MainBuild.cart);

        vga_adapter = new VGA_Adapter(this,R.layout.buildpc_layout_vga, model_vgaArrayList);
        listView_vga.setAdapter(vga_adapter);

        ImageButton imagebutton_vgaback = findViewById(R.id.imagebutton_vgaback);
        imagebutton_vgaback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private  void  GetData(String url, Model_Cart cart){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequestVGA = new JsonObjectRequest(Request.Method.POST, url, null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("success")) {
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            vga_id = jsonObject.getInt("vga_id");
                            name = jsonObject.getString("name");
                            image = jsonObject.getString("image");
                            brand = jsonObject.getString("brand");
                            size = jsonObject.getString("size");
                            model = jsonObject.getString("model");
                            gpu = jsonObject.getString("gpu_brand");
                            min_power = jsonObject.getInt("min_power");
                            price = jsonObject.getInt("price");
                            description = jsonObject.getString("description");
                            model_vgaArrayList.add(new Model_VGA(image, name, size, model, brand, gpu, description, price, vga_id));
                        }
                        vga_adapter.notifyDataSetChanged();
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
                        Toast.makeText(BuildPCListVGAActivity.this, "Lỗi!", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonObjectRequestVGA);
    }

    public static BuildPCListVGAActivity getInstance() {
        return instance;
    }
}
