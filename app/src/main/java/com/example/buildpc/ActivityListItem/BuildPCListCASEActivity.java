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
import com.example.buildpc.Adapter.CASE_Adapter;
import com.example.buildpc.Dialog.LoadingDialog;
import com.example.buildpc.Main.MainBuild;
import com.example.buildpc.Model.Model_CASE;
import com.example.buildpc.Model.Model_Cart;
import com.example.buildpc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BuildPCListCASEActivity extends AppCompatActivity {

    private ListView listView_case;
    final ArrayList<Model_CASE> model_caseArrayList = new ArrayList<>();
    CASE_Adapter case_adapter;
    String urlCASE = "http://android-api.thaolx.com/api/get/cases";
    int case_id, price;
    String name, image, brand, type, description;
    private static BuildPCListCASEActivity instance;
    LoadingDialog loadingDialog = new LoadingDialog(BuildPCListCASEActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_pclist_case);
        instance = this;

        loadingDialog.startLoadingDialog();
        GetData(urlCASE, MainBuild.cart);

        listView_case = findViewById(R.id.listView_case);

        case_adapter = new CASE_Adapter(this,R.layout.buildpc_layout_case, model_caseArrayList);
        listView_case.setAdapter(case_adapter);

        ImageButton imagebutton_caseback = findViewById(R.id.imagebutton_caseback);
        imagebutton_caseback.setOnClickListener(new View.OnClickListener() {
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
            requestData.put("ram_id", cart.getRamID() > 0 ? cart.getRamID() : null);
            requestData.put("main_id", cart.getMainID() > 0 ? cart.getMainID() : null);
            requestData.put("vga_id", cart.getVgaID() > 0 ? cart.getVgaID() : null);
            requestData.put("psu_id",  cart.getPsuID() > 0 ? cart.getPsuID(): null);
            requestData.put("ssd_id",  cart.getSsdID() > 0 ? cart.getSsdID(): null);
            requestData.put("hdd_id", cart.getHddID() > 0 ? cart.getHddID() : null);
            requestData.put("case_id",  null);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequestCASE = new JsonObjectRequest(Request.Method.POST, url, requestData
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("success")) {
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            case_id = jsonObject.getInt("case_id");
                            name = jsonObject.getString("name");
                            image = jsonObject.getString("image");
                            brand = jsonObject.getString("brand");
                            type = jsonObject.getString("type");
                            price = jsonObject.getInt("price");
                            description = jsonObject.getString("description");
                            model_caseArrayList.add(new Model_CASE(image, name, type, brand, description, price, case_id));
                        }
                        case_adapter.notifyDataSetChanged();
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
                        Toast.makeText(BuildPCListCASEActivity.this, "Lỗi!", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonObjectRequestCASE);
    }

    public static BuildPCListCASEActivity getInstance() {
        return instance;
    }
}
