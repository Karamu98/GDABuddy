package com.callumpertoldi.gdabuddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItem extends AppCompatActivity
{
    String TAG = "AddItem: ";
    User Operator = new User();
    FoodItems NewItem = new FoodItems();

    EditText nameField;
    EditText kCalField;
    EditText fatsField;
    EditText saturatesField;
    EditText sugarsField;
    EditText saltsField;

    @Override
    public void onBackPressed()
    {
        Intent cancel = new Intent(AddItem.this, FoodList.class);
        cancel.putExtra("User", Operator);
        setResult(RESULT_CANCELED, cancel);
        finish();
        Log.d(TAG, "Cancelled Activity");

    }

    private void BackToList()
    {
        Log.d(TAG, "Operator set successfully, adding to list");
        Intent addToList = new Intent(AddItem.this, FoodList.class);
        addToList.putExtra("User", Operator);
        addToList.putExtra("Item", NewItem);
        setResult(RESULT_OK, addToList);
        finish();
        Log.d(TAG, Operator.getfKCal() + " " + Operator.getfFats() + " " + Operator.getfSaturates() + " " + Operator.getfSugars() + " " + Operator.getfSalts());
        Log.d(TAG, "Finished Activity");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        nameField = (EditText) findViewById(R.id.foodNameEntryText);
        kCalField = (EditText) findViewById(R.id.foodKCalEntryText);
        fatsField = (EditText) findViewById(R.id.foodFatsEntryText);
        saturatesField = (EditText) findViewById(R.id.foodSaturatesEntryText);
        sugarsField = (EditText) findViewById(R.id.foodSugarsEntryText);
        saltsField = (EditText) findViewById(R.id.foodSaltsEntryText);


        Button submitBtn = (Button) findViewById(R.id.addItemButton);
        Bundle data = getIntent().getExtras();
        Operator = data.getParcelable("User");




        submitBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) // On click
            {
                if (nameField.getText().toString().trim().length() == 0) // Check name field isn't empty
                {
                    Log.d(TAG, "No name");
                    Toast.makeText(getApplicationContext(), "Please enter a name.", Toast.LENGTH_LONG).show();
                }
                else if(kCalField.getText().toString().trim().length() == 0) // Check KCal Field isn't empty
                {
                    Toast.makeText(getApplicationContext(), "Please enter a value.", Toast.LENGTH_LONG).show();
                }
                else if(fatsField.getText().toString().trim().length() == 0) // Check KCal Field isn't empty
                {
                    Toast.makeText(getApplicationContext(), "Please enter a value.", Toast.LENGTH_LONG).show();
                }
                else if(saturatesField.getText().toString().trim().length() == 0) // Check KCal Field isn't empty
                {
                    Toast.makeText(getApplicationContext(), "Please enter a value.", Toast.LENGTH_LONG).show();
                }
                else if(sugarsField.getText().toString().trim().length() == 0) // Check KCal Field isn't empty
                {
                    Toast.makeText(getApplicationContext(), "Please enter a value.", Toast.LENGTH_LONG).show();
                }
                else if(saltsField.getText().toString().trim().length() == 0) // Check KCal Field isn't empty
                {
                    Toast.makeText(getApplicationContext(), "Please enter a value.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Log.d(TAG, "All tests passed");
                    Log.d("DEBUG: ", (NewItem.getfKCal() + "").trim());
                    NewItem.setSsName(nameField.getText().toString().trim());
                    NewItem.setfKCal(Float.valueOf(kCalField.getText().toString()));
                    NewItem.setfFats(Float.valueOf(fatsField.getText().toString()));
                    NewItem.setfSaturates(Float.valueOf(saturatesField.getText().toString()));
                    NewItem.setfSugars(Float.valueOf(sugarsField.getText().toString()));
                    NewItem.setfSalts(Float.valueOf(saltsField.getText().toString()));

                    Log.d(TAG, "Setting Operator");
                    Operator.setfKCal(Operator.getfKCal() + NewItem.getfKCal());
                    Operator.setfFats(Operator.getfFats() + NewItem.getfFats());
                    Operator.setfSaturates(Operator.getfSaturates() + NewItem.getfSaturates());
                    Operator.setfSugars(Operator.getfSugars() + NewItem.getfSugars());
                    Operator.setfSalts(Operator.getfSalts() + NewItem.getfSalts());

                    BackToList();
                }
            }
        });

        }




    }


