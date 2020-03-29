package com.example.buildpc.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buildpc.ActivityListItem.BuildPCListCASEActivity;
import com.example.buildpc.ActivityListItem.BuildPCListCPUActivity;
import com.example.buildpc.ActivityListItem.BuildPCListHDDActivity;
import com.example.buildpc.ActivityListItem.BuildPCListMainActivity;
import com.example.buildpc.ActivityListItem.BuildPCListPSUActivity;
import com.example.buildpc.ActivityListItem.BuildPCListRAMActivity;
import com.example.buildpc.ActivityListItem.BuildPCListSSDActivity;
import com.example.buildpc.ActivityListItem.BuildPCListVGAActivity;
import com.example.buildpc.Model.Model_Item;
import com.example.buildpc.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<Model_Item> {

    private Context context;
    private int resource;
    private ArrayList<Model_Item> model_itemArrayList;
    ImageView image_item;
    String urlApi = "http://android-api.thaolx.com";

    final Intent intentcpu = new Intent(getContext(), BuildPCListCPUActivity.class);
    final Intent intentram = new Intent(getContext(), BuildPCListRAMActivity.class);
    final Intent intentmain = new Intent(getContext(), BuildPCListMainActivity.class);
    final Intent intentvga = new Intent(getContext(), BuildPCListVGAActivity.class);
    final Intent intentpsu= new Intent(getContext(), BuildPCListPSUActivity.class);
    final Intent intentssd = new Intent(getContext(), BuildPCListSSDActivity.class);
    final Intent intenthhd = new Intent(getContext(), BuildPCListHDDActivity.class);
    final Intent intentcase = new Intent(getContext(), BuildPCListCASEActivity.class);

    public ItemAdapter(Context context, int resource, ArrayList<Model_Item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.model_itemArrayList = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);

        image_item = convertView.findViewById(R.id.image_item);
        TextView textView_nameItem = convertView.findViewById(R.id.textView_nameItem);
        TextView textView_brand = convertView.findViewById(R.id.textView_brand);
        TextView textView_price = convertView.findViewById(R.id.textView_price);

        Model_Item model_item = model_itemArrayList.get(position);

        Picasso.with(context).load(urlApi+model_item.getImageItem()).into(image_item);
        textView_nameItem.setText(model_item.getNameItem());
        textView_brand.setText(model_item.getBrand());
        String s = (new DecimalFormat("#,###.##"+" VNĐ")).format(model_item.getPrice());
        if (model_item.getPrice() != 0){
            textView_price.setText(s);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        context.startActivity(intentcpu);
                        break;
                    case 1:
                        context.startActivity(intentram);
                        break;
                    case 2:
                        context.startActivity(intentmain);
                        break;
                    case 3:
                        context.startActivity(intentvga);
                        break;
                    case 4:
                        context.startActivity(intentpsu);
                        break;
                    case 5:
                        context.startActivity(intenthhd);
                        break;
                    case 6:
                        context.startActivity(intentssd);
                        break;
                    case 7:
                        context.startActivity(intentcase);
                        break;
                }
            }
        });
        return convertView;
    }
}
