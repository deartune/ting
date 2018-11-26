package com.womenproiot.www.ting;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener {

  private DbHelper dbHelper;
  private SQLiteDatabase mdb;
   Cursor cursor;
    EditText editTextNameInput;
    Spinner spinnerAge;
    Spinner spinnerGender;
    Button buttonInput;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.buttonInput)).setOnClickListener(this);
        dbHelper = new DbHelper(this);
        mdb = dbHelper.getWritableDatabase();

        Spinner spinner=(Spinner)findViewById(R.id.spinnerGender);///성별 spinner
        List<String> listGender = new ArrayList<String>();
        listGender.add("모임원 성별 선택");
        listGender.add("여성");
        listGender.add("남성");
        listGender.add("여성과 남성 포함");
        ArrayAdapter<String> dataAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listGender);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner1=(Spinner)findViewById(R.id.spinnerAge);//연령대 spinner
        List<String> listAge = new ArrayList<String>();
        listAge.add("모임원 연령대 입력");
        listAge.add("10대");
        listAge.add("20대");
        listAge.add("30대");
        listAge.add("40대");
        listAge.add("50대");
        listAge.add("60대 이상");
        listAge.add("모든 연령대 포함");
        ArrayAdapter<String> dataAdapter1 =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listAge);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter1);
        spinner1.setOnItemSelectedListener(this);
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view,
                               int position, long id) {
switch(view.getId()) {
    case R.id.spinnerAge:
    if (position >= 1) {
        String seleted = ((TextView) view).getText().toString();

        Toast.makeText(getApplicationContext(), seleted + "을(를) 선택했습니다.", LENGTH_SHORT).show();}
break;
        case R.id.spinnerGender:
            if (position >= 1) {
                String seleted = ((TextView) view).getText().toString();

                Toast.makeText(getApplicationContext(), seleted + "을(를) 선택했습니다.", LENGTH_SHORT).show();
                break;
    }


}

    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }

    @Override
    public void onClick(View v) {
        String title= editTextNameInput.getText().toString();
        String query = "INSERT INTO MEETUP VALUES (" + title.replace("pkid : ","") + ")";
        mdb.execSQL(query);
    }
}
