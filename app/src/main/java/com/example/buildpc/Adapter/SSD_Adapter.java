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
import com.example.buildpc.ActivityListItem.BuildPCListSSDActivity;
import com.example.buildpc.Dialog.DialogInfo;
import com.example.buildpc.Main.MainBuild;
import com.example.buildpc.Model.Model_SSD;
import com.example.buildpc.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SSD_Adapter extends ArrayAdapter<Model_SSD> {
    private Context context;
    private int resource;
    private ArrayList<Model_SSD> model_ssdArrayList;
    ImageButton icon_info_ssd;
    DialogInfo dialogInfo;


    public SSD_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<Model_SSD> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.model_ssdArrayList = objects;
    }
    private String urlApi = "http://android-api.thaolx.com";

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.buildpc_layout_ssd,parent,false);

        ImageView imageView_ssd = convertView.findViewById(R.id.image_ssd);
        TextView textView_name_ssd = convertView.findViewById(R.id.textView_name_ssd);
        TextView textView_brand_ssd = convertView.findViewById(R.id.textView_brand_ssd);
        TextView textView_size_ssd = convertView.findViewById(R.id.textView_size_ssd);
        TextView textView_port_ssd = convertView.findViewById(R.id.textView_port_ssd);
        TextView textView_price_ssd = convertView.findViewById(R.id.textView_price_sdd);
        icon_info_ssd = convertView.findViewById(R.id.icon_info_sdd);

        final Model_SSD model_ssd = model_ssdArrayList.get(position);

        Picasso.with(context).load(urlApi+model_ssd.getImageSSD()).into(imageView_ssd);
        textView_name_ssd.setText(model_ssd.getName());
        textView_brand_ssd.setText(model_ssd.getBrand());
        textView_size_ssd.setText(model_ssd.getSize());
        textView_port_ssd.setText(model_ssd.getPort());
        String s = (new DecimalFormat("#,###.##"+" VNĐ")).format(model_ssd.getPrice());
        textView_price_ssd.setText(s);
        icon_info_ssd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInfo.showDialog(context, BuildPCListSSDActivity.getInstance(), urlApi,
                        model_ssd.getImageSSD(),
                        model_ssd.getName(),
                        "Port " + model_ssd.getPort(),
                        model_ssd.getSize(),
                        model_ssd.getBrand(),
                        model_ssd.getDescription(),
                        model_ssd.getPrice());
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainBuild.cart.setSsdID(model_ssd.getID());
                BuildPCListSSDActivity.getInstance().finish();
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dialogInfo.showDialog(context, BuildPCListSSDActivity.getInstance(), urlApi,
                        model_ssd.getImageSSD(),
                        model_ssd.getName(),
                        "Port " + model_ssd.getPort(),
                        model_ssd.getSize(),
                        model_ssd.getBrand(),
                        model_ssd.getDescription(),
                        model_ssd.getPrice());
                return false;
            }
        });

        return convertView;
    }
}
