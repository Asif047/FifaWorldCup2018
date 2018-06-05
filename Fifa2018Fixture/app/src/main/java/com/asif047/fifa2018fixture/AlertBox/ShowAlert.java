package com.asif047.fifa2018fixture.AlertBox;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.asif047.fifa2018fixture.Home.HomeActivity;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

public class ShowAlert {

    private SweetAlertDialog sweetAlertDialog;
    private Context context;

    public ShowAlert(Context context) {
        this.context = context;
    }

    public void showWarningNetHomeActivity()
    {
        sweetAlertDialog=new SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setTitleText("No Internet Connection")
                .setContentText("Please turn on the internet connection and then press OK")
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        ((Activity)context).finish();
                        Intent intent = new Intent(context, HomeActivity.class);
                        context.startActivity(intent);
                    }
                })
                .show();
    }


}
