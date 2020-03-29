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
import com.example.buildpc.ActivityListItem.BuildPCListHDDActivity;
import com.example.buildpc.Dialog.DialogInfo;
import com.example.buildpc.Main.MainBuild;
import com.example.buildpc.Model.Model_HDD;
import com.example.buildpc.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class HDD_Adapter extends ArrayAdapter<Model_HDD> {

    private Context context;
    private int resource;
    private ArrayList<Model_HDD> model_hddArrayList;
    ImageButton icon_info_hdd;
    DialogInfo dialogInfo;


    public HDD_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<Model_HDD> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.model_hddArrayList = objects;
    }
    private String urlApi = "http://android-api.thaolx.com";
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.buildpc_layout_hdd,parent,false);

        ImageView imageView_hdd = convertView.findViewById(R.id.image_hdd);
        TextView textView_name_hdd = convertView.findViewById(R.id.textView_name_hdd);
        TextView textView_brand_hdd = convertView.findViewById(R.id.textView_brand_hdd);
        TextView textView_size_hdd = convertView.findViewById(R.id.textView_size_hdd);
        TextView textView_description_hdd = convertView.findViewById(R.id.textView_description_hdd);
        TextView textView_price_hdd = convertView.findViewById(R.id.textView_price_hdd);
        icon_info_hdd = convertView.findViewById(R.id.icon_info_hdd);

        final Model_HDD model_hdd = model_hddArrayList.get(position);

        Picasso.with(context).load(urlApi+model_hdd.getImageHDD()).into(imageView_hdd);
        textView_name_hdd.setText(model_hdd.getName());
        textView_brand_hdd.setText(model_hdd.getBrand());
        textView_size_hdd.setText(model_hdd.getSize());
        textView_description_hdd.setText(model_hdd.getDescription());
        String s = (new DecimalFormat("#,###.##"+" VNĐ")).format(model_hdd.getPrice());
        textView_price_hdd.setText(s);
        icon_info_hdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInfo.showDialog(context, BuildPCListHDDActivity.getInstance(), urlApi,
                        model_hdd.getImageHDD(),
                        model_hdd.getName(),
                        model_hdd.getSize(),
                        model_hdd.getBrand(),
                        model_hdd.description,
                        "",
                        model_hdd.getPrice());
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainBuild.cart.setHddID(model_hdd.getID());
                BuildPCListHDDActivity.getInstance().finish();
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dialogInfo.showDialog(context, BuildPCListHDDActivity.getInstance(), urlApi,
                        model_hdd.getImageHDD(),
                        model_hdd.getName(),
                        model_hdd.getSize(),
                        model_hdd.getBrand(),
                        model_hdd.description,
                        "",
                        model_hdd.getPrice());
                return false;
            }
        });

        return convertView;
    }

}



