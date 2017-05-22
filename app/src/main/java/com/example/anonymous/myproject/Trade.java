package com.example.anonymous.myproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Trade extends AppCompatActivity {

    ItemAdapter sellAdapter, buyAdapter;
    TextView heroNameTV, traderNameTV, summaryTV;
    ListView buyList, sellList;
    Button tradeButton, exitButton;
    List<GameClasses.Item> traderItems;
    static List<GameClasses.Item> itemsToBuy, itemsToSell;
    int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);

        Logic.isTrading = true;

        itemsToBuy = new ArrayList<>();
        itemsToSell = new ArrayList<>();
        traderItems = new ArrayList<>();
        chooseTraderItems();

        heroNameTV = (TextView) findViewById(R.id.buyerName);
        traderNameTV = (TextView) findViewById(R.id.traderName);
        summaryTV = (TextView) findViewById(R.id.tradeSumTextView);
        buyList = (ListView) findViewById(R.id.buyList);
        sellList = (ListView) findViewById(R.id.sellList);
        tradeButton = (Button) findViewById(R.id.tradeDealButton);
        exitButton = (Button) findViewById(R.id.tradeExitButton);

        traderNameTV.setText(getIntent().getStringExtra("traderName"));
        heroNameTV.setText(Path.king.name + "  [" + Path.king.money + " золотых]");
        summaryTV.setText("0 золотых");

        buyAdapter = new ItemAdapter(getApplicationContext(), R.layout.inventory_item, traderItems);
        buyList.setAdapter(buyAdapter);

        sellAdapter = new ItemAdapter(getApplicationContext(), R.layout.inventory_item, Path.king.inventory);
        sellList.setAdapter(sellAdapter);

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logic.isTrading = false;
                finish();
            }
        });

        tradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Path.king.money + sum < 0)
                    Toast.makeText(getApplicationContext(), "У вас не хватает денег", Toast.LENGTH_SHORT).show();
                else {
                    Path.king.inventory.addAll(itemsToBuy);
                    Path.king.inventory.removeAll(itemsToSell);

                    traderItems.addAll(itemsToSell);
                    traderItems.removeAll(itemsToBuy);

                    itemsToBuy.clear();
                    itemsToSell.clear();

                    Path.king.money += sum;
                    sum = 0;
                    setSum();
                    heroNameTV.setText(Path.king.name + "  [" + Path.king.money + " золотых]");

                    buyAdapter.notifyDataSetInvalidated();
                    sellAdapter.notifyDataSetInvalidated();
                }
            }
        });

        buyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GameClasses.Item selectedItem = (GameClasses.Item) parent.getItemAtPosition(position);

                if (searchList(itemsToBuy, selectedItem.title)) {
                    itemsToBuy.remove(selectedItem);
                    sum += selectedItem.price;
                } else {
                    itemsToBuy.add(selectedItem);
                    sum -= selectedItem.price;
                }

                setSum();
                buyAdapter.notifyDataSetInvalidated();
            }
        });

        sellList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GameClasses.Item selectedItem = (GameClasses.Item) parent.getItemAtPosition(position);

                if ((selectedItem.itemType == "weapon" && selectedItem.title.equals(Path.king.weapon.title)) ||
                        ((selectedItem.itemType == "armor" && selectedItem.title.equals(Path.king.armor.title))))
                    Toast.makeText(getApplicationContext(), "Нельзя продать экипированные вещи", Toast.LENGTH_SHORT).show();
                else if (selectedItem.itemType == "quest_item")
                    Toast.makeText(getApplicationContext(), "Нельзя продать квестовые предметы", Toast.LENGTH_SHORT).show();
                else {
                    if (searchList(itemsToSell, selectedItem.title)) {
                        itemsToSell.remove(selectedItem);
                        sum -= selectedItem.price;
                    } else {
                        itemsToSell.add(selectedItem);
                        sum += selectedItem.price;
                    }

                    setSum();
                    sellAdapter.notifyDataSetInvalidated();
                }
            }
        });
    }

    private boolean searchList(List<GameClasses.Item> list, String name) {
        for (GameClasses.Item i: list) {
            if (i.title.equals(name))
                return true;
        }
        return false;
    }

    private void setSum() {
        if (sum > 0)
            summaryTV.setText("+" + sum + " золотых");
        else
            summaryTV.setText(sum + " золотых");
    }

    private void chooseTraderItems() {
        switch ((Path.king.x + Path.king.y) % 3) {
            case 0:
                traderItems = GameClasses.traderItems1;
                break;
            case 1:
                traderItems = GameClasses.traderItems2;
                break;
            case 2:
                traderItems = GameClasses.traderItems3;
                break;
        }
    }
}