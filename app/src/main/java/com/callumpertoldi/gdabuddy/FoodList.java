package com.callumpertoldi.gdabuddy;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class FoodList extends AppCompatActivity {

    String TAG = "FoodList: ";

    Bundle data;

    int iItemIndex = 0;
    User Operator = new User();
    FoodItems NewItem = new FoodItems();

    List<FoodItems> FoodArray = new ArrayList<>();

    String CurrentFoodFile = "FoodList";

    ListView ListViewMain;

    private void SaveFile(String FileName, List<FoodItems> ItemArray)
    {
        try
        {
            Log.d(TAG, "Started Saving");
            FileOutputStream fos = openFileOutput(FileName, Context.MODE_PRIVATE);
            Log.d(TAG, "Created FoS");
            if(ItemArray.size() == 0)
            {
                fos.write(("0").getBytes());
            }
            else
            {
                iItemIndex = ItemArray.size();
                fos.write((iItemIndex + "\n").getBytes());
                Log.d(TAG, "Saved length");
                for(int i = 0; i < iItemIndex; i++)
                {
                    fos.write(ItemArray.get(i).getBytes());
                }
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();

            }
            fos.close();
        } catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Save Error", Toast.LENGTH_LONG).show();
            Log.d("ERROR: ", "Unable to save file!");
        }
    }

    private void LoadFile(String FileName) {
        try {
            FileInputStream fis = openFileInput(FileName);

            Log.d("Loader: ", "Created fis");

            BufferedReader reader = new BufferedReader(new InputStreamReader(new DataInputStream(fis)));

            Log.d("Loader: ", "Created reader");

            String line;

            iItemIndex =  Integer.parseInt((reader.readLine()).trim());

            Log.d("Loader: ", "Index = " + iItemIndex);

            for (int i = 0; i < iItemIndex; i++)
            {
                Log.d("Loader: ", "For Loop Iteration:  " + i);
                int iIterations = 0;

                FoodItems tempItem = new FoodItems();
                while (iIterations != 6)
                {
                    line = reader.readLine();
                    switch (iIterations)
                    {
                        case 0:
                        {
                            tempItem.setSsName(line);
                            iIterations = iIterations + 1;
                            Log.d("Loader: ", "Loaded Name");
                            Log.d("Line: ", line);
                            Log.d("Line: ", "iIterations: " + iIterations);
                            break;
                        }
                        case 1: {
                            Log.d("Line: ", line);
                            float temp = Float.parseFloat(line);
                            Log.d("Loader: ", "Set temp to " + temp);
                            tempItem.setfKCal(temp);
                            iIterations = iIterations + 1;
                            Log.d("Loader: ", "Loaded KCal");
                            Log.d("Line: ", line);
                            Log.d("Line: ", "iIterations: " + iIterations);
                            break;
                        }
                        case 2: {
                            float temp = Float.parseFloat(line);
                            tempItem.setfFats(temp);
                            iIterations = iIterations + 1;
                            Log.d("Loader: ", "Loaded Fats");
                            Log.d("Line: ", line);
                            Log.d("Line: ", "iIterations: " + iIterations);
                            break;
                        }
                        case 3: {
                            float temp = Float.parseFloat(line);
                            tempItem.setfSaturates(temp);
                            iIterations = iIterations + 1;
                            Log.d("Loader: ", "Loaded Sats");
                            Log.d("Line: ", line);
                            Log.d("Line: ", "iIterations: " + iIterations);
                            break;
                        }
                        case 4: {
                            float temp = Float.parseFloat(line);
                            tempItem.setfSugars(temp);
                            iIterations = iIterations + 1;
                            Log.d("Loader: ", "Loaded Sugars");
                            Log.d("Line: ", line);
                            Log.d("Line: ", "iIterations: " + iIterations);
                            break;
                        }
                        case 5: {
                            Log.d("Line: ", line);
                            float temp = Float.parseFloat(line);
                            tempItem.setfSalts(temp);
                            Log.d("Loader: ", "Loaded Salts");
                            Log.d("Line: ", "iIterations: " + iIterations);
                            iIterations = iIterations + 1;
                            break;
                        }
                        default:
                        {
                            break;
                        }
                    }
                }
                FoodArray.add(tempItem);
            }


            fis.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Load Error", Toast.LENGTH_LONG).show();
            Log.d("ERROR: ", "Unable to load file!");
        }
    }

    public void onTrimMemory(final int level) {
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            SaveFile(CurrentFoodFile, FoodArray);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        LoadFile(CurrentFoodFile);

        ListViewMain = (ListView) findViewById(R.id.listViewMain);

        RefreshList();
        registerClickCallback();

        data = getIntent().getExtras();
        Operator = data.getParcelable("User");

        Log.d(TAG, "Loading Nav bar");
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.itemsNavigation);
        navigation.setSelectedItemId(R.id.navigation_Items);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Log.d(TAG, "Nav bar successful");

        FloatingActionButton addItemButton = (FloatingActionButton) findViewById(R.id.addItemButton);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent addItem = new Intent(FoodList.this, AddItem.class);
                addItem.putExtra("User", Operator);
                startActivityForResult(addItem, 1);
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_History:
                    //setContentView(R.layout.activity_main);
                    return true;
                case R.id.navigation_dashboard:
                    SaveFile(CurrentFoodFile, FoodArray);
                    ToMainMenu();

                    return true;
                case R.id.navigation_Items:

                    return true;
            }
            return false;
        }

    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent a_Intent)
    {
        super.onActivityResult(requestCode, resultCode, a_Intent);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {

                Log.d(TAG, "Loading Parcels");
                data = a_Intent.getExtras();
                Log.d(TAG, "Retrieved Package");


                Operator = data.getParcelable("User");
                NewItem = data.getParcelable("Item");
                Log.d(TAG, "Loaded Parcels");

                Log.d(TAG, NewItem.getSsName());

                FoodArray.add(NewItem);

                RefreshList();
                // Update List probably

            }
            else if(requestCode == RESULT_CANCELED)
            {
                data = a_Intent.getExtras();
                Operator = data.getParcelable("User");
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) { //Back key pressed

            SaveFile(CurrentFoodFile, FoodArray);
            ToMainMenu();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void ToMainMenu()
    {
        //SaveFile(); // Save Food list
        Intent mainMenu = new Intent(FoodList.this, MainActivity.class);
        mainMenu.putExtra("User", Operator); // Pass the operator back
        setResult(Activity.RESULT_OK, mainMenu);
        finish(); // Close this activity
    }


    private void RefreshList() {
        ArrayAdapter<FoodItems> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.listViewMain);
        list.setAdapter(adapter);
    }

    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.listViewMain);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long id)
            {
                // Do page transition here with passed position

                //FoodItems clickedItem = FoodArray.get(position);

                Toast.makeText(FoodList.this, "Clicked!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private class MyListAdapter extends ArrayAdapter<FoodItems> {
        public MyListAdapter() {
            super(FoodList.this, R.layout.item_layout, FoodArray);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Make sure we have a view to work with (may have been given null)
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_layout, parent, false);
            }

            // Find the car to work with.
            FoodItems currentItem = FoodArray.get(position);

            // Item image
            //ImageView imageView = (ImageView)itemView.findViewById(R.id.item_icon);
            //imageView.setImageResource(currentItem.getIconID());

            // Name:
            TextView nameText = (TextView) itemView.findViewById(R.id.listItemName);
            nameText.setText(currentItem.getSsName());

            // Energy:
            TextView kcalText = (TextView) itemView.findViewById(R.id.layoutlistKCal);
            kcalText.setText("" + currentItem.getfKCal());

            // Fats:
            TextView fatsText = (TextView) itemView.findViewById(R.id.layoutlistFats);
            fatsText.setText("" + currentItem.getfFats());

            // Saturates:
            TextView saturatesText = (TextView) itemView.findViewById(R.id.layoutlistSaturates);
            saturatesText.setText("" + currentItem.getfSaturates());

            // Sugars:
            TextView sugarsText = (TextView) itemView.findViewById(R.id.layoutlistSugars);
            sugarsText.setText("" + currentItem.getfSugars());

            // Salts:
            TextView saltsText = (TextView) itemView.findViewById(R.id.layoutlistSalts);
            saltsText.setText("" + currentItem.getfSalts());

            return itemView;
        }
    }

}
