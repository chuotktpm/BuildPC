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
import com.example.buildpc.Adapter.CPU_Adapter;
import com.example.buildpc.Dialog.LoadingDialog;
import com.example.buildpc.Main.MainBuild;
import com.example.buildpc.Model.Model_CPU;
import com.example.buildpc.Model.Model_Cart;
import com.example.buildpc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

public class BuildPCListCPUActivity extends AppCompatActivity {

    private ListView listView_cpu;
    private static BuildPCListCPUActivity instance;
    final ArrayList<Model_CPU> model_cpuArrayList = new ArrayList<>();
    CPU_Adapter cpu_adapter;
    String urlGetCPUs = "http://android-api.thaolx.com/api/get/cpus";

    LoadingDialog loadingDialog = new LoadingDialog(BuildPCListCPUActivity.this);

    int cpu_id, generation_id, price;
    String name, image, brand, socket, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_pclist_cpu);
        instance = this;

        listView_cpu = findViewById(R.id.listView_cpu);
        loadingDialog.startLoadingDialog();
        GetData(urlGetCPUs, MainBuild.cart);

        cpu_adapter = new CPU_Adapter(this,R.layout.buildpc_layout_cpu, model_cpuArrayList);
        listView_cpu.setAdapter(cpu_adapter);

        ImageButton imagebutton_cpuback = findViewById(R.id.imagebutton_cpuback);
        imagebutton_cpuback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void GetData(String url, Model_Cart cart){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        try {
        JSONObject requestData = new JSONObject();
            requestData.put("cpu_id", null);
            requestData.put("ram_id", cart.getRamID() > 0 ? cart.getRamID() : null);
            requestData.put("main_id",cart.getMainID() > 0 ? cart.getMainID() : null);
            requestData.put("vga_id",cart.getVgaID() > 0 ? cart.getVgaID() : null);
            requestData.put("psu_id",cart.getPsuID() > 0 ? cart.getPsuID() : null);
            requestData.put("ssd_id",cart.getSsdID() > 0 ? cart.getSsdID() : null);
            requestData.put("hdd_id",cart.getHddID() > 0 ? cart.getHddID() : null);
            requestData.put("case_id",cart.getCaseID() > 0 ? cart.getCaseID() : null);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, requestData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")){
                                JSONArray jsonArray = response.getJSONArray("data");
                                for (int position = 0; position < jsonArray.length(); position++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(position);
                                    cpu_id = jsonObject.getInt("cpu_id");
                                    name = jsonObject.getString("name");
                                    image = jsonObject.getString("image");
                                    brand = jsonObject.getString("brand");
                                    generation_id = jsonObject.getInt("generation_id");
                                    socket = jsonObject.getString("socket");
                                    price = jsonObject.getInt("price");
                                    description = jsonObject.getString("description");
                                    model_cpuArrayList.add(new Model_CPU(cpu_id, image, socket, name, brand, softGen(generation_id), description, price));
                                }
                                cpu_adapter.notifyDataSetChanged();
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
                        Toast.makeText(BuildPCListCPUActivity.this, "Xảy ra lỗi trong quá trình kết nối máy chủ!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String softGen(int genId){
        String textgen = "";
        switch (genId){
            case 1:
                textgen = "Gen 9th";
                break;
            case 2:
                textgen = "Gen 8th";
                break;
            case 3:
                textgen = "Gen 7th";
                break;
            case 4:
                textgen = "Gen 6th";
                break;
            case 5:
                textgen = "Gen 4th";
                break;
            case 6:
                textgen = "Gen 1st";
                break;
            case 7:
                textgen = "Gen 2nd";
                break;
            case 8:
                textgen = "Gen 3rd";
                break;
            default:
                break;
        }

        return textgen;
    }

    public static BuildPCListCPUActivity getInstance() {
        return instance;
    }
}
