package com.example.buildpc.Adapter;

import android.annotation.SuppressLint;
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

import com.example.buildpc.ActivityListItem.BuildPCListCPUActivity;
import com.example.buildpc.Dialog.DialogInfo;
import com.example.buildpc.Main.MainBuild;
import com.example.buildpc.Model.Model_CPU;
import com.example.buildpc.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CPU_Adapter extends ArrayAdapter<Model_CPU> {

    private Context context;
    private int resource;
    private ArrayList<Model_CPU> model_cpuArrayList;
    ImageButton icon_info_cpu;
    ImageView imageView_cpu;
    String urlApi = "http://android-api.thaolx.com";
    DialogInfo dialogInfo;

    public CPU_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<Model_CPU> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.model_cpuArrayList = objects;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.buildpc_layout_cpu,parent,false);

        imageView_cpu = convertView.findViewById(R.id.image_cpu);
        TextView textView_name_cpu = convertView.findViewById(R.id.textView_name_cpu);
        TextView textView_brand_cpu = convertView.findViewById(R.id.textView_brand_cpu);
        TextView textView_gen_cpu = convertView.findViewById(R.id.textView_gen_cpu);
        TextView textView_socket_cpu = convertView.findViewById(R.id.textView_socket_cpu);
        TextView textView_price_cpu = convertView.findViewById(R.id.textView_price_cpu);
        icon_info_cpu = convertView.findViewById(R.id.icon_info_cpu);

        final Model_CPU model_cpu = model_cpuArrayList.get(position);

        Picasso.with(context).load(urlApi+model_cpu.getImageCPU()).into(imageView_cpu);
        textView_name_cpu.setText(model_cpu.getModel());
        textView_brand_cpu.setText(model_cpu.getBrand());
        textView_gen_cpu.setText(model_cpu.getGenaration());
        textView_socket_cpu.setText(model_cpu.getSocket());
        String s = (new DecimalFormat("#,###.##"+" VNĐ")).format(model_cpu.getPrice());
        textView_price_cpu.setText(s);

        icon_info_cpu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInfo.showDialog(context, BuildPCListCPUActivity.getInstance(), urlApi,
                        model_cpu.getImageCPU(),
                        model_cpu.getModel(),
                        model_cpu.getGenaration(),
                        "Socket " + model_cpu.getSocket(),
                        model_cpu.getBrand(),
                        model_cpu.getDescription(),
                        model_cpu.getPrice());
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainBuild.cart.setCpuID(model_cpu.getId());
                BuildPCListCPUActivity.getInstance().finish();
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                dialogInfo.showDialog(context, BuildPCListCPUActivity.getInstance(), urlApi,
                        model_cpu.getImageCPU(),
                        model_cpu.getModel(),
                        model_cpu.getGenaration(),
                        "Socket " + model_cpu.getSocket(),
                        model_cpu.getBrand(),
                        model_cpu.getDescription(),
                        model_cpu.getPrice());
                return false;
            }
        });

        return convertView;
    }
}





