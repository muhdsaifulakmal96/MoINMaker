package com.project.moinmaker;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //on click image method for expense class
       ImageView imageExpense = (ImageView) findViewById(R.id.expenseImage);
        imageExpense.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
               Toast.makeText(Second.this,"Enter your New Expense", Toast.LENGTH_LONG).show();
               Intent intent = new Intent(getApplicationContext(), Expense.class);
               startActivity(intent);
            }
        });

        //on click image method for history class
        ImageView history = (ImageView) findViewById(R.id.Imagerecord);
        history.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(Second.this,"You clicked on History", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(getApplicationContext(), History.class);
                startActivity(intent1);
            }
        });

        //on click image method for map class
        ImageView mapImage = (ImageView) findViewById(R.id.mapImage);
        mapImage.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(Second.this,"You clicked on Map", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent1);
            }
        });

        //on click image method for capture image class
        ImageView captureimage = (ImageView) findViewById(R.id.CaptureImage);
        captureimage.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(Second.this,"You clicked on Capture Image", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(getApplicationContext(), CaptureImage.class);
                startActivity(intent1);
            }
        });

        //on click image method for capture image class
        ImageView profile = (ImageView) findViewById(R.id.aboutImage);
        profile.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(Second.this,"You clicked on Tips & Tricks", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent1);
            }
        });

        //on click image method for PrintPDF
        ImageView PrintImage = (ImageView) findViewById(R.id.pdf);
        PrintImage.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(Second.this,"You clicked on PDF", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(getApplicationContext(), PrintPdf.class);
                startActivity(intent1);
            }
        });

    }

}


