package com.project.moinmaker;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import com.project.moinmaker.Database.DatabaseClass;

public class Expense extends AppCompatActivity {

    //declare values
    Spinner addregular, currency_sp;
    String[] regular = {"None", "Daily", "Weekly", "Monthly"};
    String[] unit = {"MYR", "EUR", "INR", "JPY", "MMK", "SGD", "USD"};
    TextView date, upload_image;
    Calendar myCalender;
    private static int RESULT_LOAD_Image = 1;
    private static int RESULT_LOAD_B = 200;
    ImageView image;
    String picturePath;
    EditText amount, category, note;

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    public static DatabaseClass databaseClass;
    Button saveTodatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        // find ids from activity expense
        amount = (EditText) findViewById(R.id.amount);
        category = (EditText) findViewById(R.id.category);
        note = (EditText) findViewById(R.id.description);
        image = (ImageView) findViewById(R.id.images);
        saveTodatabase = (Button) findViewById(R.id.save);
        upload_image = (TextView) findViewById(R.id.imageview);
        addregular = (Spinner) findViewById(R.id.spinner);
        date = (TextView) findViewById(R.id.date);
        currency_sp = (Spinner) findViewById(R.id.currency_unit);

        // provide drop down items spinner
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Expense.this, R.layout.support_simple_spinner_dropdown_item, regular);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        addregular.setAdapter(arrayAdapter);
        myCalender = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthofyear, int dayofmonth) {
                myCalender.set(Calendar.YEAR, year);
                myCalender.set(Calendar.MONTH, monthofyear);
                myCalender.set(Calendar.DATE, dayofmonth);
                updateLabel();
            }
        };
        ArrayAdapter<String> arrayCurrency = new ArrayAdapter<String>(Expense.this, R.layout.support_simple_spinner_dropdown_item, unit);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        currency_sp.setAdapter(arrayCurrency);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Expense.this, dateSetListener, myCalender.get(Calendar.YEAR), myCalender.get(Calendar.MONTH), myCalender.get(Calendar.DATE)).show();
            }
        });

        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageChooser();
            }
        });
        final Getpaths pp = new Getpaths();
        databaseClass = new DatabaseClass(Expense.this);
        saveTodatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    databaseClass.insertDataToExpense(amount.getText().toString() + currency_sp.getSelectedItem().toString(),
                            category.getText().toString(),
                            note.getText().toString(),
                            date.getText().toString(),
                            pp.getPath(),
                            addregular.getSelectedItem().toString());
                    Toast.makeText(Expense.this, "Saved New Expense Record", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date.setText(sdf.format(myCalender.getTime()));

    }

    private void openImageChooser() {
        verifyStoragePermissions(Expense.this);
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULT_LOAD_Image);

    }
    public static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_Image && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            image.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            Expense.Getpaths getpath = new Expense.Getpaths();
            getpath.setPath(picturePath);
        }
    }

    public static class Getpaths {
        static String path;

        public String getPath() {
            return path;
        }

        public void setPath(String picturepPath) {
            this.path = picturepPath;
        }
    }
}