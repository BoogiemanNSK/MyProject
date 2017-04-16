package com.example.anonymous.myproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class Inventory extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ItemAdapter itemAdapter;
    TextView hp, mana, strength, perception, endurance, charisma, intelligence, agility, luck, heroName, gold;
    ListView itemList;
    static GameClasses.Weapon currentWeapon = Path.king.weapon;
    static GameClasses.Armor currentArmor = Path.king.armor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_inventory);

        heroName = (TextView) findViewById(R.id.hero_name);
        itemList = (ListView) findViewById(R.id.itemList);
        hp = (TextView) findViewById(R.id.hp);
        mana = (TextView) findViewById(R.id.mana);
        gold = (TextView) findViewById(R.id.gold);
        strength = (TextView) findViewById(R.id.strength);
        perception = (TextView) findViewById(R.id.perception);
        endurance = (TextView) findViewById(R.id.endurance);
        charisma = (TextView) findViewById(R.id.charisma);
        intelligence = (TextView) findViewById(R.id.intelligence);
        agility = (TextView) findViewById(R.id.agility);
        luck = (TextView) findViewById(R.id.luck);

        heroName.setText(Path.king.name);
        hp.setText(String.valueOf(Path.king.hp));
        mana.setText(String.valueOf(Path.king.mana));
        gold.setText(String.valueOf(Path.king.money));
        strength.setText(String.valueOf(Path.king.strength));
        perception.setText(String.valueOf(Path.king.perception));
        endurance.setText(String.valueOf(Path.king.endurance));
        charisma.setText(String.valueOf(Path.king.charisma));
        intelligence.setText(String.valueOf(Path.king.intelligence));
        agility.setText(String.valueOf(Path.king.agility));
        luck.setText(String.valueOf(Path.king.luck));

        itemAdapter = new ItemAdapter(getApplicationContext(), R.layout.inventory_item, Path.king.inventory);
        itemList.setAdapter(itemAdapter);
        itemList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        GameClasses.Item selectedItem = (GameClasses.Item) parent.getItemAtPosition(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(Inventory.this);

        switch (selectedItem.itemType) {
            case "weapon":
                final GameClasses.Weapon selectedWeapon = (GameClasses.Weapon) selectedItem;

                builder.setTitle("Выберите действие")
                        .setNegativeButton("Экипировать", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Path.king.weapon = selectedWeapon;
                                currentWeapon = selectedWeapon;
                                itemAdapter.notifyDataSetInvalidated();
                            }
                        });

                builder.create().show();

                break;
            case "armor":
                final GameClasses.Armor selectedArmor = (GameClasses.Armor) selectedItem;

                builder.setTitle("Выберите действие")
                        .setNegativeButton("Экипировать", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Path.king.armor = selectedArmor;
                                currentArmor = selectedArmor;
                                itemAdapter.notifyDataSetInvalidated();
                            }
                        });

                builder.create().show();

                break;
            case "drink":
                final GameClasses.Drink selectedDrink = (GameClasses.Drink) selectedItem;

                builder.setTitle("Выберите действие")
                        .setNegativeButton("Выпить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (selectedDrink.drinkType) {
                                    case "hp":
                                        Path.king.hp += selectedDrink.bonus;
                                        hp.setText(String.valueOf(Path.king.hp));
                                        break;
                                    case "mana":
                                        Path.king.mana += selectedDrink.bonus;
                                        mana.setText(String.valueOf(Path.king.mana));
                                        break;
                                    default:
                                        break;
                                }
                                Path.king.inventory.remove(selectedDrink);
                                itemAdapter.notifyDataSetInvalidated();
                            }
                        });

                builder.create().show();

                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}