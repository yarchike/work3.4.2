package com.example.work342;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);


        Button buttonOk = findViewById(R.id.ok);

        final Spinner spinner = findViewById(R.id.spinner);
        final Spinner spinnerMargin = findViewById(R.id.spinner_margin);

        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.language, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<?> adapterMargin =
                ArrayAdapter.createFromResource(this, R.array.margin, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinnerMargin.setAdapter(adapterMargin);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selected = (String) spinner.getSelectedItem();
                switch (selected) {
                    case ("Английский"):
                        createLocal("en");
                        break;
                    case ("Russian"):
                        createLocal("ru");
                        break;
                }
                String selectedMargin = (String) spinnerMargin.getSelectedItem();
                switch (selectedMargin) {
                    case ("По умолчанию"):
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_DEFAULT);
                        break;
                    case ("Крупная"):
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_LARGE);
                        break;
                    case ("Средняя"):
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_AVERAGE);
                        break;
                    case ("Мелкая"):
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_SHALLOW);
                        break;
                }
            }

        });

    }

    void createLocal(String local) {
        Configuration config = new Configuration();
        config.locale = new Locale(local);
        getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }
}
