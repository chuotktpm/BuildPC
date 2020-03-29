package com.example.buildpc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.buildpc.ActivityListItem.BuildPCListPSUActivity;
import com.example.buildpc.Dialog.DialogInfo;
import com.example.buildpc.Main.MainBuild;
import com.example.buildpc.Model.Model_PSU;
import com.example.buildpc.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PSU_Adapter extends ArrayAdapter<Model_PSU> {

    private Context context;
    private int resource;
    private ArrayList<Model_PSU> model_psuArrayList;
    ImageButton icon_info_psu;
    DialogInfo dialogInfo;


    public PSU_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<Model_PSU> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.model_psuArrayList = objects;
    }
    private String urlApi = "http://android-api.thaolx.com";
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.buildpc_layout_psu,parent,false);

        ImageView imageView_psu = convertView.findViewById(R.id.image_psu);
        TextView textView_name_psu = convertView.findViewById(R.id.textView_name_psu);
        TextView textView_brand_psu = convertView.findViewById(R.id.textView_brand_psu);
        TextView textView_efficiency_rating = convertView.findViewById(R.id.textView_EfficiencyRating_psu);
        TextView textView_size_psu = convertView.findViewById(R.id.textView_size_psu);
        TextView textView_price_psu = convertView.findViewById(R.id.textView_price_psu);
        icon_info_psu = convertView.findViewById(R.id.icon_info_psu);

        final Model_PSU model_psu = model_psuArrayList.get(position);

        Picasso.with(context).load(urlApi+model_psu.getImagePSU()).into(imageView_psu);
        textView_name_psu.setText("Nguồn " + model_psu.getName());
        textView_size_psu.setText(String.valueOf(model_psu.getSize())+ "W");
        textView_brand_psu.setText(model_psu.getBrand());
        textView_efficiency_rating.setText(model_psu.getEfficiency_rating());

        String s = (new DecimalFormat("#,###.##"+" VNĐ")).format(model_psu.getPrice());
        textView_price_psu.setText(s);

        icon_info_psu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInfo.showDialog(context, BuildPCListPSUActivity.getInstance(), urlApi,
                        "Công suất " + model_psu.getImagePSU(),
                        model_psu.getName(),
                        String.valueOf(model_psu.getSize()) + "W",
                        "Chuẩn " + model_psu.getEfficiency_rating(),
                        model_psu.getBrand(),
                        model_psu.getDescription(),
                        model_psu.getPrice()
                );
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainBuild.cart.setPsuID(model_psu.getID());
                BuildPCListPSUActivity.getInstance().finish();
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dialogInfo.showDialog(context, BuildPCListPSUActivity.getInstance(), urlApi,
                        "Công suất " + model_psu.getImagePSU(),
                        model_psu.getName(),
                        String.valueOf(model_psu.getSize()) + "W",
                        "Chuẩn " + model_psu.getEfficiency_rating(),
                        model_psu.getBrand(),
                        model_psu.getDescription(),
                        model_psu.getPrice()
                );
                return false;
            }
        });

        return convertView;
    }
}