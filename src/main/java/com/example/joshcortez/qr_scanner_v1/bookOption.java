package com.example.joshcortez.qr_scanner_v1;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class bookOption extends AppCompatActivity {

    private Button borrow;
    private Button breturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_option);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(getColor(R.color.colorAccent));
            getWindow().setStatusBarColor(getColor(R.color.colorAccent));
        }

        borrow = (Button)findViewById(R.id.preButton);
        breturn = (Button)findViewById(R.id.postButton);

        borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(bookOption.this, ReaderActivity.class));
            }
        });

        breturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(bookOption.this, Reader2Activity.class));
            }
        });

    }
}
