package com.example.joshcortez.qr_scanner_v1;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class selection extends AppCompatActivity {

    private Button bborrow;
    private Button attend;
    private ProgressBar progressBar;


    @Override
    public void onBackPressed() {

        AlertDialog.Builder exitApp = new AlertDialog.Builder(selection.this);
        exitApp.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog exit = exitApp.create();
        exit.setTitle("Alert!!");
        exit.show();
        // do your back button logic here
        // you can have conditions or whatever you want to do.
        // change to different fragment, go to different activity, etc.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(getColor(R.color.fbase));
            getWindow().setStatusBarColor(getColor(R.color.fbase));
        }

        bborrow = (Button)findViewById(R.id.borrowButton);
        attend = (Button)findViewById(R.id.attendButton);

        bborrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(selection.this, bookOption.class));
            }
        });

        attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(selection.this, Reader3Activity.class));
            }
        });


    }

}
