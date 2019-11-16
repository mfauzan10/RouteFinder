package com.example.routefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BasicActivity extends AppCompatActivity {

    private Spinner spinner;
    private Button startButton;
    private Button allButton;
    private Button detailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        addItemsToSpinner();
        addListenerToAllButton();
        addListenerToDetailButton();
        addListenerToStartButton();
    }

    public void addItemsToSpinner(){
        spinner = (Spinner) findViewById(R.id.ListLocation);
        List<String> list = new ArrayList<String>();
        list.add("Location 1");
        list.add("Location 2");
        list.add("Location 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.addAll(list);
        spinner.setAdapter(dataAdapter);
    }

    public void addListenerToDetailButton() {
        detailButton = (Button) findViewById(R.id.DetailButton);

        detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String detailName = "Name";
                String detailAddress = "Address";
                String detail = "Name :\n" + detailName + "Address :\n" + detailAddress;
                Toast.makeText(BasicActivity.this, detail, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addListenerToStartButton(){
        startButton = (Button) findViewById(R.id.StartButton);

        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getBaseContext(),RouteActivity.class);
                startActivity(startIntent);
            }
        });
    }

    public void addListenerToAllButton(){
        allButton = (Button) findViewById(R.id.AllButton);

        allButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent allIntent = new Intent(getBaseContext(),TagActivity.class);
                startActivity(allIntent);
            }
        });
    }
}