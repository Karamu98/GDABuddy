package com.callumpertoldi.gdabuddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Welcome extends AppCompatActivity
{
    User Operator = new User();
    String FileName = "UserData";

    EditText nameField;
    RadioButton maleButton;
    RadioButton femaleButton;

    public void SaveFile()
    {
        try
        {
            FileOutputStream fos = openFileOutput(FileName, Context.MODE_PRIVATE);
            fos.write(Operator.getBytes());
            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            fos.close();
        } catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Save Error", Toast.LENGTH_LONG).show();
            Log.d("ERROR: ", "Unable to save file!");
        }
    }

    public void LoadFile() {
        try {
            FileInputStream fis = openFileInput(FileName);

            Log.d("Loader: ", "Created fis");

            BufferedReader reader = new BufferedReader(new InputStreamReader(new DataInputStream(fis)));

            Log.d("Loader: ", "Created reader");

            String line;

            int iIterations = 0;
            while ((line = reader.readLine()) != null)
            {
                Log.d("Line: ", line);
                switch (iIterations) {
                    case 0: {
                        Operator.setSsName(line);
                        iIterations = iIterations + 1;
                        Log.d("Loader: ", "Loaded Name");
                        Log.d("Line: ", line);
                        break;
                    }
                    case 1: {
                        if (line.contains("true")) {
                            Operator.setbIsMale(true);
                            iIterations = iIterations + 1;
                            Log.d("Loader: ", "Loaded Gender Male");
                            Log.d("Line: ", line);
                            break;
                        } else {
                            Operator.setbIsMale(false);
                            iIterations = iIterations + 1;
                            Log.d("Loader: ", "Loaded Gender Female");
                            Log.d("Line: ", line);
                            break;

                        }
                    }
                    case 2: {
                        float temp = Float.parseFloat(line);
                        Operator.setfKCal(temp);
                        iIterations = iIterations + 1;
                        Log.d("Loader: ", "Loaded KCal");
                        Log.d("Line: ", line);
                        break;
                    }
                    case 3: {
                        float temp = Float.parseFloat(line);
                        Operator.setfFats(temp);
                        iIterations = iIterations + 1;
                        Log.d("Loader: ", "Loaded Fats");
                        Log.d("Line: ", line);
                        break;
                    }
                    case 4: {
                        float temp = Float.parseFloat(line);
                        Operator.setfSaturates(temp);
                        iIterations = iIterations + 1;
                        Log.d("Loader: ", "Loaded Saturates");
                        Log.d("Line: ", line);
                        break;
                    }
                    case 5: {
                        float temp = Float.parseFloat(line);
                        Operator.setfSugars(temp);
                        iIterations = iIterations + 1;
                        Log.d("Loader: ", "Loaded Sugars");
                        Log.d("Line: ", line);
                        break;
                    }
                    case 6: {
                        float temp = Float.parseFloat(line);
                        Operator.setfSalts(temp);
                        iIterations = iIterations + 1;
                        Log.d("Loader: ", "Loaded Salts");
                        Log.d("Line: ", line);
                        break;
                    }
                    default:
                        {
                            break;
                    }
                }
            }
            fis.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Load Error", Toast.LENGTH_LONG).show();
            Log.d("ERROR: ", "Unable to load file!");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        LoadFile();
        if(!Operator.getSsName().trim().equals(""))
        {
            Intent skipWelcome = new Intent(this, MainActivity.class);
            skipWelcome.putExtra("User", Operator);
            startActivity(skipWelcome);
            finish();
        }

        nameField = (EditText) findViewById(R.id.foodNameEntryText);
        maleButton = (RadioButton) findViewById(R.id.maleButton);
        femaleButton = (RadioButton) findViewById(R.id.femaleButton);

        Button submitBtn = (Button) findViewById(R.id.welcomeSubmitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (nameField.getText().toString().trim().length() == 0)
                {

                }
                else
                {
                    Operator.setSsName((String) nameField.getText().toString());
                    if(maleButton.isChecked())
                    {
                        Operator.setbIsMale(true);
                    }
                    else
                    {
                        Operator.setbIsMale(false);
                    }
                }

                Log.d("Name: ", Operator.getSsName());

                SaveFile();

                Intent skipWelcome = new Intent(Welcome.this, MainActivity.class);
                skipWelcome.putExtra("User", Operator);
                startActivity(skipWelcome);
                finish();
            }

        });


        }
    }


