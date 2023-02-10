package com.example.listycity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    // attributes
    ListView cityList;
    ArrayAdapter<City> cityAdapter;
    ArrayList<City> dataList;
    Button addingButton;
    Button deleteButton;
    EditText addCityInput;
    EditText addProvinceInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getting the view using IDs
        cityList = findViewById(R.id.city_list);
        addingButton = findViewById(R.id.adding_button);
        deleteButton = findViewById(R.id.delete_button);
        addCityInput = findViewById(R.id.add_city_field);
        addProvinceInput = findViewById(R.id.add_province_field);
        dataList = new ArrayList<>();
        cityAdapter = new CityArrAdapter(this, dataList);

        // Add button logic
        addingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get input from EditText fields
                final String cityName = addCityInput.getText().toString();
                final String provinceName = addProvinceInput.getText().toString();
                // HashMap<String, String> data = new HashMap<>();

                // making sure the input is not empty
                if (cityName.length() > 0 && provinceName.length() > 0) {
                    // data.put("province_name", provinceName);

                    // appending the city to the dataList
                    dataList.add(new City(cityName, provinceName)); // add the list
                    cityAdapter.notifyDataSetChanged(); // update the adapter
                }

                // reset input parameters
                addCityInput.setText("");
                addProvinceInput.setText("");
            }
        });

        // Making the delete button appear when a list item is picked
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                deleteButton.setVisibility(View.VISIBLE);
                deleteButton.setClickable(true);

                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dataList.remove(i);
                        adapterView.setSelection(-1);

                        deleteButton.setVisibility(View.INVISIBLE);
                        deleteButton.setClickable(false);
                        cityAdapter.notifyDataSetChanged(); // update the adapter
                    }
                });
            }
        });

        cityList.setAdapter(cityAdapter);
    }
}