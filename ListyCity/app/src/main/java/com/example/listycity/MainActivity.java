package com.example.listycity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    // attributes
    ListView cityList;
    ArrayAdapter<City> cityAdapter;
    ArrayList<City> dataList;
    Button addingButton;
    EditText addCityInput;
    EditText addProvinceInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getting the view using IDs
        cityList = findViewById(R.id.city_list);
        addingButton = findViewById(R.id.adding_button);
        addCityInput = findViewById(R.id.add_city_field);
        addProvinceInput = findViewById(R.id.add_province_field);

        String[] cities = {"Edmonton", "Vancouver", "Toronto"};
        String[] provinces = {"AB", "BC", "ON"};

        // Button logic
        addingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String cityName = addCityInput.getText().toString();
                final String provinceName = addProvinceInput.getText().toString();
                HashMap<String, String> data = new HashMap<>();

                if (cityName.length() > 0 && provinceName.length() > 0) {
                    data.put("province_name", provinceName);
                }

                addCityInput.setText("");
                addProvinceInput.setText("");
            }
        });

        dataList = new ArrayList<>();
        for (int i = 0; i < cities.length; i++) {
            dataList.add(new City(cities[i], provinces[i]));
        }

        cityAdapter = new CityArrAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);
    }
}