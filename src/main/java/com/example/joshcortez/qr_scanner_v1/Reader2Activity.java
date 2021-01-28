package com.example.joshcortez.qr_scanner_v1;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Reader2Activity extends AppCompatActivity {

    private Button studqr2;
    private Button bookqr2;
    private Button ok2;
    private TextView haha2;
    private TextView hahah2;
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
        setContentView(R.layout.activity_reader2);

        final Intent intent = new Intent(Reader2Activity.this, qrcodes2.class);
        haha2 = (TextView)findViewById(R.id.eeew);
        hahah2 = (TextView)findViewById(R.id.eeaw);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(getColor(R.color.green));
            getWindow().setStatusBarColor(getColor(R.color.green));
        }

        studqr2 = (Button)findViewById(R.id.studentqr2);
        final Activity activity3 = this;
        studqr2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                IntentIntegrator integrator = new IntentIntegrator(activity3);
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

        bookqr2 = (Button)findViewById(R.id.bookqr2);
        final Activity activity4 = this;
        bookqr2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                IntentIntegrator integrator = new IntentIntegrator(activity4);
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

        ok2 = (Button)findViewById(R.id.ok2);
        ok2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                intent.putExtra("studQR",haha2.getText());
                intent.putExtra("bookQR",hahah2.getText());
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
            haha2.setText(qrString);
        }
        else if(x==2){
            hahah2.setText(qrString);
        }

    }

    public void studString(){

        haha2.setText(qrString);


    }

    public void bookString(){

        bookqrString = qrString;
    }

}