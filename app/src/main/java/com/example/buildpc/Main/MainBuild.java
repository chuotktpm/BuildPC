package com.example.buildpc.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.buildpc.Adapter.ItemAdapter;
import com.example.buildpc.Dialog.LoadingDialog;
import com.example.buildpc.Model.Model_Cart;
import com.example.buildpc.Model.Model_Item;
import com.example.buildpc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainBuild extends AppCompatActivity {

    private ListView listView_item;
    final ArrayList<Model_Item> model_itemArrayList = new ArrayList<>();
    String urlGetCart = "http://android-api.thaolx.com/api/get/cart";
    LoadingDialog loadingDialog = new LoadingDialog(MainBuild.this);

    TextView textView_sum_price;
    TextView textView_sum;
    ImageButton imagebutton_backtomain;
    ItemAdapter itemAdapter;
    String s;
    public static Model_Cart cart = new Model_Cart();

    @Override
    public void onBackPressed() {
        finishDialog();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_build);

        listView_item = findViewById(R.id.list_item);
        textView_sum_price = findViewById(R.id.textView_sum_price);
        textView_sum = findViewById(R.id.textView_sum);
        imagebutton_backtomain = findViewById(R.id.imagebutton_backtomain);

        imagebutton_backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishDialog();
            }
        });

        model_itemArrayList.add(new Model_Item("/images/cpu/intel_i5_7500.png", "CPU","Vi xử lý",0));
        model_itemArrayList.add(new Model_Item("/images/ram/kingston_hyperx_predator_rgb_32_ddr4.jpg", "RAM","Bộ nhớ ngẫu nhiên",0));
        model_itemArrayList.add(new Model_Item("/images/main/msi_x470_gaming_pro.jpg", "Main","Bo mạch chủ",0));
        model_itemArrayList.add(new Model_Item("/images/vga/zotac_gtx1660_amp_ed_6gb.jpg", "VGA","Card màn hình",0));
        model_itemArrayList.add(new Model_Item("/images/psu/corsair_series_vs_500w.png", "PSU","Nguồn",0));
        model_itemArrayList.add(new Model_Item("/images/hdd/seagate_barracuda_500.jpg", "HDD","Ổ cứng cơ",0));
        model_itemArrayList.add(new Model_Item("/images/ssd/avexir_e100_120.png", "SSD","Ổ cứng thể rắn",0));
        model_itemArrayList.add(new Model_Item("/images/case/edra_shadow_pheonix.jpg", "Case","Vỏ case",0));

        itemAdapter = new ItemAdapter(this, R.layout.item_layout,model_itemArrayList);
        listView_item.setAdapter(itemAdapter);
    }

    private void Sum(){
        int Sum = 0;
        for (int i = 0; i<model_itemArrayList.size(); i++){
            Model_Item model_item = model_itemArrayList.get(i);
            Sum += model_item.getPrice();
        }
        s = (new DecimalFormat("#,###.##"+" VNĐ")).format(Sum);
        if (Sum != 0){
            textView_sum_price.setText(s);
            textView_sum.setText("Tổng: ");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadingDialog.startLoadingDialog();
        getCartInfo(urlGetCart, cart);
    }

    private void getCartInfo(String url, Model_Cart cart){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        try {
            JSONObject requestData = new JSONObject();
            requestData.put("cpu_id",cart.getCpuID());
            requestData.put("ram_id",cart.getRamID());
            requestData.put("main_id",cart.getMainID());
            requestData.put("vga_id",cart.getVgaID());
            requestData.put("psu_id",cart.getPsuID());
            requestData.put("ssd_id",cart.getSsdID());
            requestData.put("hdd_id",cart.getHddID());
            requestData.put("case_id",cart.getCaseID());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, requestData,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getBoolean("success")){
                                    JSONArray jsonData = response.getJSONArray("data");
                                    for (int position = 0; position < jsonData.length(); position++){
                                        try {
                                            JSONObject jsonObject = jsonData.getJSONObject(position);
                                            model_itemArrayList.set(position, new Model_Item(
                                                    jsonObject.getString("image"),
                                                    jsonObject.getString("name"),
                                                    jsonObject.getString("brand"),
                                                    jsonObject.getInt("price")
                                            ));
                                        } catch (JSONException error) {
                                            error.printStackTrace();
                                        }
                                        Sum();
                                    }
                                    itemAdapter.notifyDataSetChanged();
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
                            Toast.makeText(MainBuild.this, "Lỗi", Toast.LENGTH_SHORT).show();
                        }
                    }
            );
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static Model_Cart getCart() {
        return cart;
    }

    private void finishDialog(){
        if (cart.getCpuID() != 0 ||
                cart.getRamID() != 0 ||
                cart.getMainID() != 0 ||
                cart.getVgaID() != 0 ||
                cart.getPsuID() != 0 ||
                cart.getHddID() != 0 ||
                cart.getSsdID() != 0 ||
                cart.getCaseID() != 0
        ){
            AlertDialog.Builder finishDialog = new AlertDialog.Builder(MainBuild.this);
            finishDialog.setMessage("Thoát sẽ mất dữ liệu đã chọn!");
            finishDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    cart.clear();
                    finish();
                }
            });
            finishDialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            finishDialog.setCancelable(false);
            finishDialog.show();
        }
        else {
            finish();
        }
    }

}
