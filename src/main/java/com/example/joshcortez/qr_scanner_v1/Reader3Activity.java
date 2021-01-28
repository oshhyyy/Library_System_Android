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


public class Reader3Activity extends AppCompatActivity {

    private Button studqr3;
    private Button bookqr3;
    private Button ok3;
    private TextView haha3;
    private TextView hahah3;
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
        setContentView(R.layout.activity_reader3);

        final Intent intent = new Intent(Reader3Activity.this, qrcodes3.class);
        haha3 = (TextView)findViewById(R.id.eeeew);
        hahah3 = (TextView)findViewById(R.id.eeeaw);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(getColor(R.color.bblue));
            getWindow().setStatusBarColor(getColor(R.color.bblue));
        }

        studqr3 = (Button)findViewById(R.id.studentqr3);
        final Activity activity5 = this;
        studqr3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                IntentIntegrator integrator = new IntentIntegrator(activity5);
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


        ok3 = (Button)findViewById(R.id.ok3);
        ok3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                intent.putExtra("studQR",haha3.getText());
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
            haha3.setText(qrString);
        }
        else if(x==2){
            hahah3.setText(qrString);
        }

    }

    public void studString(){

        haha3.setText(qrString);


    }

    public void bookString(){

        bookqrString = qrString;
    }
}
