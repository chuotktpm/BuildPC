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

import com.example.buildpc.ActivityListItem.BuildPCListVGAActivity;
import com.example.buildpc.Dialog.DialogInfo;
import com.example.buildpc.Main.MainBuild;
import com.example.buildpc.Model.Model_VGA;
import com.example.buildpc.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class VGA_Adapter extends ArrayAdapter<Model_VGA> {
    private Context context;
    private int resource;
    private ArrayList<Model_VGA> model_vgaArrayList;
    ImageButton icon_info_vga;
    DialogInfo dialogInfo;


    public VGA_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<Model_VGA> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.model_vgaArrayList = objects;
    }
    private String urlApi = "http://android-api.thaolx.com";
    ImageView imageView_vga;


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.buildpc_layout_vga,parent,false);

        imageView_vga = convertView.findViewById(R.id.image_vga);
        TextView textView_name_vga = convertView.findViewById(R.id.textView_name_vga);
        TextView textView_brand_vga = convertView.findViewById(R.id.textView_brand_vga);
        TextView textView_size_vga = convertView.findViewById(R.id.textView_size_vga);
        TextView textView_gpu_vga = convertView.findViewById(R.id.textView_description_vga);
        TextView textView_price_vga = convertView.findViewById(R.id.textView_price_vga);
        icon_info_vga = convertView.findViewById(R.id.icon_info_vga);

        final Model_VGA model_vga = model_vgaArrayList.get(position);

        Picasso.with(context).load(urlApi+model_vga.getImageVGA()).into(imageView_vga);
        textView_name_vga.setText(model_vga.getName());
        textView_brand_vga.setText(model_vga.getBrand());
        textView_size_vga.setText(model_vga.getSize());
        textView_gpu_vga.setText(model_vga.getGPU());
        String s = (new DecimalFormat("#,###.##"+" VNĐ")).format(model_vga.getPrice());
        textView_price_vga.setText(s);

        icon_info_vga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInfo.showDialog(context, BuildPCListVGAActivity.getInstance(), urlApi,
                        model_vga.getImageVGA(),
                        model_vga.getName(),
                        model_vga.getGPU(),
                        model_vga.getModel() + " " + model_vga.getSize(),
                        model_vga.getBrand(),
                        model_vga.getDescription(),
                        model_vga.getPrice());
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainBuild.cart.setVgaID(model_vga.getVga_id());
                BuildPCListVGAActivity.getInstance().finish();
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dialogInfo.showDialog(context, BuildPCListVGAActivity.getInstance(), urlApi,
                        model_vga.getImageVGA(),
                        model_vga.getName(),
                        model_vga.getGPU(),
                        model_vga.getModel() + " " + model_vga.getSize(),
                        model_vga.getBrand(),
                        model_vga.getDescription(),
                        model_vga.getPrice());
                return false;
            }
        });

        return convertView;
    }
}

