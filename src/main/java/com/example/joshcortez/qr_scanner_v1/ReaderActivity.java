package com.example.joshcortez.qr_scanner_v1;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

public class ReaderActivity extends AppCompatActivity {
    private Button studqr;
    private Button bookqr;
    private Button ok;
    private TextView haha;
    private TextView hahah;
    private static int SPLASH_TIME_OUT = 1000;

    int x = 0;
    String hahaha;
    String qrString;
    String studqrString;
    String bookqrString;

    @Override
    public void onBackPressed() {
        finish();
        // do your back button logic here
        // you can have conditions or whatever you want to do.
        // change to different fragment, go to different activity, etc.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        final Intent intent = new Intent(ReaderActivity.this, qrcodes.class);
        haha = (TextView)findViewById(R.id.eew);
        hahah = (TextView)findViewById(R.id.eaw);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(getColor(R.color.green));
            getWindow().setStatusBarColor(getColor(R.color.green));
        }

        studqr = (Button)findViewById(R.id.studentqr);
        final Activity activity1 = this;
        studqr.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                IntentIntegrator integrator = new IntentIntegrator(activity1);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setCameraId(0);
                integrator.setPrompt("");
                integrator.setOrientationLocked(false);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
                x = 1;
            }
        });

        bookqr = (Button)findViewById(R.id.bookqr);
        final Activity activity2 = this;
        bookqr.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                IntentIntegrator integrator = new IntentIntegrator(activity2);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setCameraId(0);
                integrator.setPrompt("");
                integrator.setOrientationLocked(false);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
                x = 2;
            }
        });

        ok = (Button)findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                intent.putExtra("studQR",haha.getText());
                intent.putExtra("bookQR",hahah.getText());
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
        qrString = result.getContents();
        if(x==1){
            haha.setText(qrString);
        }
        else if(x==2){
            hahah.setText(qrString);
        }

    }

    public void studString(){

        haha.setText(qrString);


    }

    public void bookString(){

        bookqrString = qrString;
    }


}
