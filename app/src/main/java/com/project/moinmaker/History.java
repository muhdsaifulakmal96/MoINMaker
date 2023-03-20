package com.project.moinmaker;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import com.project.moinmaker.Database.DataModel;
import com.project.moinmaker.Database.DatabaseClass;
import com.project.moinmaker.Database.list_adapter;

public class History extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase database;
    ArrayList<DataModel> list;
    list_adapter listAdapter;
    DatabaseClass databaseClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        listView=(ListView) findViewById(R.id.list_view);

        list=new ArrayList<>();
        databaseClass=new DatabaseClass(History.this);
        listAdapter=new list_adapter(this,R.layout.list_row,list);
        listView.setAdapter(listAdapter);

        database=databaseClass.getReadableDatabase();

        Cursor cr=database.rawQuery("Select * from expense ",null);

        StringBuilder stringBuilder=new StringBuilder();
        while (cr.moveToNext())
        {
            int id=cr.getInt(0);
            String amount=cr.getString(1);
            String category=cr.getString(2);
            String description=cr.getString(3);
            String date=cr.getString(4);
            String image=cr.getString(5);
            String spinner=cr.getString(6);
            list.add(new DataModel(id,amount,category,description,date,image,spinner));
        }
    }
}