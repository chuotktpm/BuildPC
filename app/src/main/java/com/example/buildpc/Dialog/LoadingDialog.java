package com.example.buildpc.Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.example.buildpc.R;

public class LoadingDialog {

    private Activity activity;
    private AlertDialog dialog;

    //Khởi tạo Dialog
    public LoadingDialog(Activity myActivity){
        activity = myActivity;
    }

    //Bắt đầu Loading...
    public void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_dialog,  null ));
        builder.setCancelable(true);

        dialog = builder.create();
        dialog.show();
    }

    //Tắt Dialog khi loading xong...
    public void dismissDialog(){
        dialog.cancel();
    }

}
