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

import com.example.buildpc.ActivityListItem.BuildPCListMainActivity;
import com.example.buildpc.Dialog.DialogInfo;
import com.example.buildpc.Main.MainBuild;
import com.example.buildpc.Model.Model_Main;
import com.example.buildpc.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Main_Adapter extends ArrayAdapter<Model_Main> {

    private Context context;
    private int resource;
    private ArrayList<Model_Main> model_mainsArrayList;
    ImageButton icon_info_main;
    DialogInfo dialogInfo;

    public Main_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<Model_Main> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.model_mainsArrayList = objects;
    }
    private String urlApi = "http://android-api.thaolx.com";

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.buildpc_layout_main,parent,false);


        ImageView imageView_main = convertView.findViewById(R.id.image_main);
        TextView textView_name_main = convertView.findViewById(R.id.textView_name_main);
        TextView textView_brand_main = convertView.findViewById(R.id.textView_brand_main);
        TextView textView_memoryType_main = convertView.findViewById(R.id.textView_memoryType_main);
        TextView textView_socket_main = convertView.findViewById(R.id.textView_socket_main);
        TextView textView_price_main = convertView.findViewById(R.id.textView_price_main);
        icon_info_main = convertView.findViewById(R.id.icon_info_main);

        final Model_Main model_main = model_mainsArrayList.get(position);

        Picasso.with(context).load(urlApi+model_main.getImageMain()).into(imageView_main);
        textView_name_main.setText(model_main.getModel());
        textView_brand_main.setText(model_main.getBrand());
        textView_memoryType_main.setText("Support " + model_main.getMemoryType());
        textView_socket_main.setText(model_main.getSocket());
        String s = (new DecimalFormat("#,###.##"+" VNĐ")).format(model_main.getPrice());
        textView_price_main.setText(s);

        icon_info_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInfo.showDialog(context, BuildPCListMainActivity.getInstance(), urlApi,
                        model_main.getImageMain(),
                        model_main.getModel(),
                        "Hỗ trợ Socket " + model_main.getSocket(),
                        "Hỗ trợ RAM " + model_main.getMemoryType(),
                        model_main.getBrand(),
                        model_main.getDescription(),
                        model_main.getPrice());
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainBuild.cart.setMainID(model_main.getID());
                BuildPCListMainActivity.getInstance().finish();
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dialogInfo.showDialog(context, BuildPCListMainActivity.getInstance(), urlApi,
                        model_main.getImageMain(),
                        model_main.getModel(),
                        "Hỗ trợ Socket " + model_main.getSocket(),
                        "Hỗ trợ RAM " + model_main.getMemoryType(),
                        model_main.getBrand(),
                        model_main.getDescription(),
                        model_main.getPrice());
                return false;
            }
        });

        return convertView;
    }
}
