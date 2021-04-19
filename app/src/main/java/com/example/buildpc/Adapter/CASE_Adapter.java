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

import com.example.buildpc.ActivityListItem.BuildPCListCASEActivity;
import com.example.buildpc.Dialog.DialogInfo;
import com.example.buildpc.Main.MainBuild;
import com.example.buildpc.Model.Model_CASE;
import com.example.buildpc.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
    //Can them gi do de nhin
public class CASE_Adapter extends ArrayAdapter<Model_CASE> {

    private Context context;
    private int resource;
    private ArrayList<Model_CASE> model_caseArrayList;
    ImageButton icon_info_case;
    DialogInfo dialogInfo;


    public CASE_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<Model_CASE> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.model_caseArrayList = objects;
    }
    private String urlApi = "http://android-api.thaolx.com";
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.buildpc_layout_case,parent,false);

        ImageView imageView_case = convertView.findViewById(R.id.image_case);
        TextView textView_name_case = convertView.findViewById(R.id.textView_name_case);
        TextView textView_brand_case = convertView.findViewById(R.id.textView_brand_case);
        TextView textView_type_case = convertView.findViewById(R.id.textView_type_case);
        TextView textView_description_case = convertView.findViewById(R.id.textView_description_case);
        TextView textView_price_case = convertView.findViewById(R.id.textView_price_case);
        icon_info_case = convertView.findViewById(R.id.icon_info_case);

        final Model_CASE model_case = model_caseArrayList.get(position);

        Picasso.with(context).load(urlApi+model_case.getImageCASE()).into(imageView_case);
        textView_name_case.setText("Case " + model_case.getName());
        textView_brand_case.setText(model_case.getBrand());
        textView_type_case.setText(model_case.getType());
        textView_description_case.setText(model_case.getDescription());
        String s = (new DecimalFormat("#,###.##"+" VNĐ")).format(model_case.getPrice());
        textView_price_case.setText(s);

        icon_info_case.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInfo.showDialog(context, BuildPCListCASEActivity.getInstance(), urlApi,
                        model_case.getImageCASE(),
                        model_case.getName(),
                        "Type " + model_case.getType(),
                        model_case.getBrand(),
                        model_case.getDescription(),
                        "",
                        model_case.getPrice());
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainBuild.cart.setCaseID(model_case.getID());
                BuildPCListCASEActivity.getInstance().finish();
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dialogInfo.showDialog(context, BuildPCListCASEActivity.getInstance(), urlApi,
                        model_case.getImageCASE(),
                        model_case.getName(),
                        "Type " + model_case.getType(),
                        model_case.getBrand(),
                        model_case.getDescription(),
                        "",
                        model_case.getPrice());
                return false;
            }
        });

        return convertView;
    }
}




