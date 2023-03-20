package com.project.moinmaker.Database;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import com.project.moinmaker.R;


public class list_adapter extends ArrayAdapter{

    private Context context;
    private int layout;
    private ArrayList<DataModel> arrayList;

    public list_adapter(Context context, int layout, ArrayList<DataModel> arrayList) {
        super(context, R.layout.list_row,arrayList);
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }


    public int getCount() {
        return arrayList.size();
    }


    public Object getItem(int position) {
        return arrayList.get(position);
    }


    public long getItemId(int position) {
        return position;
    }


    private  class ViewHolder
    {
        TextView date;
        ImageView imageView;
        TextView amount,category,description;
    }

    public View getView(int position, View view, ViewGroup viewGroup) {

        View  row=view;
        ViewHolder holder=new ViewHolder();
        if (row == null)
        {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(layout,null);
            holder.date =(TextView)row.findViewById(R.id.list_date);
            holder.amount=(TextView)row.findViewById(R.id.list_amount);
            holder.category=(TextView)row.findViewById(R.id.list_category);
            holder.description=(TextView)row.findViewById(R.id.list_description);
            holder.imageView=(ImageView) row.findViewById(R.id.list_image);
            row.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)row.getTag();
        }

        //retrieve data from data model
        DataModel retrieve_db=arrayList.get(position);
        holder.amount.setText(retrieve_db.getAmount());
        holder.category.setText(retrieve_db.getCategory());
        holder.description.setText(retrieve_db.getDescription());
        holder.date.setText(retrieve_db.getDate());
        String image=retrieve_db.getImage();
        Bitmap bitmap= BitmapFactory.decodeFile(image);
        holder.imageView.setImageBitmap(bitmap);
        return row;
    }

    @Nullable

    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}


