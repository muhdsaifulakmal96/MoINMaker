package com.project.moinmaker;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
    TextView t,version, features, developer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //About application texts
        t = findViewById(R.id.textprofile);
        t.setText("Summary\n\n"+"MoINMaker application organizes your personal finance easily and effectively\n");
        version = findViewById(R.id.textVersion);
        version.setText("Version 1.1\n");
        features = findViewById(R.id.textFeatures);
        features.setText("Budgeting Tip for Inconsistent Income\n\n"+"\t\n"
                                    +"•\tGood news, guys. You can budget with an inconsistent or irregular income. A good rule of thumb is to budget based on what a low earning month would look like for you. This will be your budgeting income.\n"
                                    +"•\tAll you need to do is make a list of all the things you would put in a normal budget (giving, saving, four walls, etc).\n"
                                    +"•\tBe sure to take care of the necessities first. That way when you do get paid, you know exactly where your money is going.");
    }
}
