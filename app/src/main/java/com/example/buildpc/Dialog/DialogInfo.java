package com.example.buildpc.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buildpc.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class DialogInfo {
    public static void showDialog(Context context, Activity activity , String urlApi, String image, String Name, String Info1, String Info2, String Brand, String Description, int Price){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_info);

        ImageView imageView = dialog.findViewById(R.id.image_info);
        TextView name = dialog.findViewById(R.id.textView_name_info);
        TextView info1 = dialog.findViewById(R.id.textView_1_info);
        TextView info2 = dialog.findViewById(R.id.textView_2_info);
        TextView brand = dialog.findViewById(R.id.textView_brand_info);
        TextView description = dialog.findViewById(R.id.textView_description_info);
        TextView price = dialog.findViewById(R.id.textView_price_info);
        ImageButton close = dialog.findViewById(R.id.icon_close_dialog);

        Picasso.with(context).load(urlApi+image).into(imageView);
        name.setText(Name);
        info1.setText(Info1);
        info2.setText(Info2);
        brand.setText(Brand);
        description.setText(Description);
        String s = (new DecimalFormat("#,###.##"+" VNĐ")).format(Price);
        price.setText(s);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }
}
