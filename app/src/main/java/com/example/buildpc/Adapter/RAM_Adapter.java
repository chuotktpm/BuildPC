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

import com.example.buildpc.ActivityListItem.BuildPCListRAMActivity;
import com.example.buildpc.Dialog.DialogInfo;
import com.example.buildpc.Main.MainBuild;
import com.example.buildpc.Model.Model_RAM;
import com.example.buildpc.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class RAM_Adapter extends ArrayAdapter<Model_RAM> {

    private Context context;
    private int resource;
    private ArrayList<Model_RAM> model_ramsArrayList;
    ImageButton icon_info_ram;
    DialogInfo dialogInfo;

    public RAM_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<Model_RAM> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.model_ramsArrayList = objects;
    }
    private String urlApi = "http://android-api.thaolx.com";


    @NonNull
@Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.buildpc_layout_ram,parent,false);

        ImageView imageView_ram = convertView.findViewById(R.id.image_ram);
        TextView textView_name_ram = convertView.findViewById(R.id.textView_name_ram);
        TextView textView_brand_ram = convertView.findViewById(R.id.textView_brand_ram);
        TextView textView_type_ram = convertView.findViewById(R.id.textView_type_ram);
        TextView textView_bus_ram = convertView.findViewById(R.id.textView_bus_ram);
        TextView textView_price_ram = convertView.findViewById(R.id.textView_price_ram);
        icon_info_ram = convertView.findViewById(R.id.icon_info_ram);

        final Model_RAM model_ram = model_ramsArrayList.get(position);

        Picasso.with(context).load(urlApi+model_ram.getImageRAM()).into(imageView_ram);
        textView_name_ram.setText(model_ram.getName());
        textView_brand_ram.setText(model_ram.getBrand());
        textView_type_ram.setText(model_ram.getType());
        textView_bus_ram.setText(model_ram.getBus());
        String s = (new DecimalFormat("#,###.##"+" VNĐ")).format(model_ram.getPrice());
        textView_price_ram.setText(s);

        icon_info_ram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInfo.showDialog(context, BuildPCListRAMActivity.getInstance(), urlApi,
                        model_ram.getImageRAM(),
                        model_ram.getName(),
                        "Bus " + model_ram.getBus(),
                        model_ram.getType(),
                        model_ram.getBrand(),
                        model_ram.getDescription(),
                        model_ram.getPrice());
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainBuild.cart.setRamID(model_ram.getID());
                BuildPCListRAMActivity.getInstance().finish();
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dialogInfo.showDialog(context, BuildPCListRAMActivity.getInstance(), urlApi,
                        model_ram.getImageRAM(),
                        model_ram.getName(),
                        "Bus " + model_ram.getBus(),
                        model_ram.getType(),
                        model_ram.getBrand(),
                        model_ram.getDescription(),
                        model_ram.getPrice());
                return false;
            }
        });

        return convertView;
        }
}
