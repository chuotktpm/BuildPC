package com.example.buildpc.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.buildpc.Main.MainBuild;
import com.example.buildpc.Model.Model_Cart;
import com.example.buildpc.Model.Model_PC;
import com.example.buildpc.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class PC_Adapter extends ArrayAdapter<Model_PC> {

    private Context context;
    private int resource;
    private ArrayList<Model_PC> model_buildArrayList;
    private String urlApi = "http://android-api.thaolx.com";

    public PC_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<Model_PC> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.model_buildArrayList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.line_pc_layout, parent, false);

        ImageView imageView_pc = convertView.findViewById(R.id.image_pc);
        TextView textView_name_pc = convertView.findViewById(R.id.textView_name_pc);
        TextView textView_type_pc = convertView.findViewById(R.id.textView_type_pc);
        TextView textView_description_pc = convertView.findViewById(R.id.textView_description_pc);
        TextView textView_price_pc = convertView.findViewById(R.id.textView_price_pc);

        final Model_PC model_build = model_buildArrayList.get(position);

        Picasso.with(context).load(urlApi + model_build.getImageBuild()).into(imageView_pc);
        textView_name_pc.setText(model_build.getName());
        textView_type_pc.setText(model_build.getType());
        textView_description_pc.setText(model_build.getDescription());
        String s = (new DecimalFormat("#,###.##"+" VNĐ")).format(model_build.getPrice());
        textView_price_pc.setText(s);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainBuild.cart.setCpuID(model_build.getCpu_ID());
                MainBuild.cart.setRamID(model_build.getRam_ID());
                MainBuild.cart.setMainID(model_build.getMain_ID());
                MainBuild.cart.setVgaID(model_build.getVga_ID());
                MainBuild.cart.setPsuID(model_build.getPsu_ID());
                MainBuild.cart.setHddID(model_build.getHdd_ID());
                MainBuild.cart.setSsdID(model_build.getSsd_ID());
                MainBuild.cart.setCaseID(model_build.getCase_ID());
                Intent intent = new Intent(getContext(), MainBuild.class);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
