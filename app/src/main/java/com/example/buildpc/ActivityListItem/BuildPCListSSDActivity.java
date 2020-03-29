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
import com.example.buildpc.Model.Model_SSD;
import com.example.buildpc.R;
import com.example.buildpc.Adapter.SSD_Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BuildPCListSSDActivity extends AppCompatActivity {

    private ListView listView_ssd;
    final ArrayList<Model_SSD> model_ssdArrayList = new ArrayList<>();
    SSD_Adapter ssd_adapter;
    String urlSSD = "http://android-api.thaolx.com/api/get/ssds";
    int ssd_id, price;
    String name, image, brand, size, port, description;
    private static BuildPCListSSDActivity instance;
    LoadingDialog loadingDialog = new LoadingDialog(BuildPCListSSDActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildpc_list_ssd);
        instance = this;
        listView_ssd = findViewById(R.id.listView_ssd);

        loadingDialog.startLoadingDialog();
        GetData(urlSSD, MainBuild.cart);

        ssd_adapter = new SSD_Adapter(this, R.layout.buildpc_layout_ssd, model_ssdArrayList);
        listView_ssd.setAdapter(ssd_adapter);
        ImageButton imagebutton_ssdback = findViewById(R.id.imagebutton_ssdback);
        imagebutton_ssdback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private  void  GetData(String url, Model_Cart cart){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject requesData = new JSONObject();
        try {
            requesData.put("cpu_id", cart.getCpuID() > 0 ? cart.getCpuID(): null);
            requesData.put("ram_id", cart.getRamID() > 0 ? cart.getRamID() : null);
            requesData.put("main_id", cart.getMainID() > 0 ? cart.getMainID() : null);
            requesData.put("vga_id", cart.getVgaID() > 0 ? cart.getVgaID() : null);
            requesData.put("psu_id",  cart.getPsuID() > 0 ? cart.getPsuID(): null);
            requesData.put("ssd_id",  null);
            requesData.put("hdd_id", cart.getHddID() > 0 ? cart.getHddID() : null);
            requesData.put("case_id", cart.getCaseID() > 0 ? cart.getCaseID() : null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequestSSD = new JsonObjectRequest(Request.Method.POST, url, requesData
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("success")) {
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            ssd_id = jsonObject.getInt("ssd_id");
                            name = jsonObject.getString("name");
                            image = jsonObject.getString("image");
                            brand = jsonObject.getString("brand");
                            size = jsonObject.getString("size");
                            port = jsonObject.getString("port");
                            price = jsonObject.getInt("price");
                            description = jsonObject.getString("description");
                            model_ssdArrayList.add(new Model_SSD(image, name, size, port ,brand, description, price, ssd_id));
                        }
                        ssd_adapter.notifyDataSetChanged();
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
                        Toast.makeText(BuildPCListSSDActivity.this, "Lỗi!", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonObjectRequestSSD);
    }

    public static BuildPCListSSDActivity getInstance() {
        return instance;
    }
}
