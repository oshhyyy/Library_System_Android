package com.example.joshcortez.qr_scanner_v1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class qrcodes2 extends AppCompatActivity {

    private Firebase studentprof;
    private Firebase booklist;
    private Firebase logs;
    private Firebase logsToday;

    private Button confirm;

    private ImageView studIV;
    private TextView studnameTV;
    private TextView studnumTV;
    private TextView courseTV;
    private TextView titleTV;
    private TextView authorTV;
    private TextView sectionTV;

    Calendar cal;

    String studQR;
    String bookQR;
    String mobileNo;
    String logDate;

    String hourToday;
    int minToday;
    int secToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcodes2);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(getColor(R.color.orange));
            getWindow().setStatusBarColor(getColor(R.color.orange));
        }

        confirm = (Button)findViewById(R.id.confirm2);

        AlertDialog.Builder progress = new AlertDialog.Builder(qrcodes2.this);
        progress.setMessage("███▒▒▒▒▒▒▒  Fetching data...")
                .setCancelable(false);
        final AlertDialog load = progress.create();
        load.show();

        Bundle intent = getIntent().getExtras();
        studQR = intent.getString("studQR");
        bookQR = intent.getString("bookQR");
        studentprof = new Firebase("https://trial-e88f7.firebaseio.com/Student Profiles/" + studQR);
        booklist = new Firebase("https://trial-e88f7.firebaseio.com/BookList/" + bookQR);

        studIV = (ImageView)findViewById(R.id.studpic2);
        studnameTV = (TextView)findViewById(R.id.studname2);
        studnumTV = (TextView)findViewById(R.id.studnum2);
        courseTV = (TextView)findViewById(R.id.course2);
        titleTV = (TextView)findViewById(R.id.booktitle2);
        authorTV = (TextView)findViewById(R.id.author2);
        sectionTV = (TextView)findViewById(R.id.section2);

        Date today = new Date();
        cal = Calendar.getInstance();
        cal.setTime(today);

        int monthToday = cal.get(Calendar.MONTH) + 1;
        int dayToday = cal.get(Calendar.DAY_OF_MONTH);
        int yearToday = cal.get(Calendar.YEAR);

        logDate = monthToday + "-" + dayToday + " " + yearToday;
        logs = new Firebase("https://trial-e88f7.firebaseio.com/Logs");

        //Student Profile fetcher

        studentprof.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                DataSnapshot mobileFirebase = dataSnapshot.child("Number");
                DataSnapshot nameFirebase = dataSnapshot.child("Name");
                DataSnapshot studnumFirebase = dataSnapshot.child("Student No");
                DataSnapshot courseFirebase = dataSnapshot.child("Course");
                DataSnapshot picFirebase = dataSnapshot.child("Picture");

                mobileNo = mobileFirebase.getValue(String.class);
                String name = nameFirebase.getValue(String.class);
                String studnum = studnumFirebase.getValue(String.class);
                String course = courseFirebase.getValue(String.class);
                String picUrl = picFirebase.getValue(String.class);

                if (nameFirebase.exists()){
                }
                else {
                    AlertDialog.Builder exitApp = new AlertDialog.Builder(qrcodes2.this);
                    exitApp.setMessage("No student/book records found!")
                            .setCancelable(false)
                            .setPositiveButton("Return to scanner", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });

                    AlertDialog exit = exitApp.create();
                    exit.setTitle("Alert!!");
                    exit.show();
                }

                studnameTV.setText(name);
                studnumTV.setText(studnum);
                courseTV.setText(course);
                Glide.with(getApplicationContext()).load(picUrl).into(studIV);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        //Book fetcher

        booklist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                DataSnapshot titleFirebase = dataSnapshot.child("Title");
                DataSnapshot authorFirebase = dataSnapshot.child("Author");
                DataSnapshot sectionFirebase = dataSnapshot.child("Section");

                String title = titleFirebase.getValue(String.class);
                String author = authorFirebase.getValue(String.class);
                String section = sectionFirebase.getValue(String.class);

                titleTV.setText(title);
                authorTV.setText(author);
                sectionTV.setText(section);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (studIV.getDrawable() == null & sectionTV.getText() == "Section:"){

                        }else{
                            load.dismiss();
                        }
                    }
                }, 500);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date today = new Date();
                cal = Calendar.getInstance();
                cal.setTime(today);

                String month = new SimpleDateFormat("MMM").format(cal.getTime());
                int day = cal.get(Calendar.DAY_OF_MONTH) + 7;
                int year = cal.get(Calendar.YEAR);

                String textNotif = "****DO NOT REPLY****\nThank you for returning " + titleTV.getText()
                        + " by " + authorTV.getText() + ".";

                hourToday = new SimpleDateFormat("HH").format(cal.getTime());
                minToday = cal.get(Calendar.MINUTE);
                secToday = cal.get(Calendar.SECOND);

                logs.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        logsToday = new Firebase("https://trial-e88f7.firebaseio.com/Logs/" + logDate);

                        if(dataSnapshot.child(logDate).exists()){

                            Firebase addLogInstance = logsToday.child(hourToday + "H" + minToday + "M" + secToday + "S");
                            addLogInstance.setValue(studnameTV.getText() + " from " + courseTV.getText() + " returned " +
                                    titleTV.getText() + " by " + authorTV.getText() + ".");

                        }

                        else{

                            Firebase addDateLog = logs.child(logDate);

                            Firebase addLogInstance = logsToday.child(hourToday + "H" + minToday + "M" + secToday + "S");
                            addLogInstance.setValue(studnameTV.getText() + " from " + courseTV.getText() + " returned " +
                                    titleTV.getText() + " by " + authorTV.getText() + ".");

                        }

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(mobileNo, null, textNotif, null, null);
                    Toast.makeText(qrcodes2.this, "Success!", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent homeIntent = new Intent(qrcodes2.this, selection.class);
                            startActivity(homeIntent);
                            finish();
                        }
                    }, 500);

                }
                catch (Exception e){
                    Toast.makeText(qrcodes2.this, "Oops!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        //studnameTV.setText(intent.getString("studQR"));
        //studnumTV.setText(intent.getString("bookQR"));
    }
}
