package com.example.anonymous.myproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.anonymous.myproject.prepare.ScrollingActivity;

import java.util.ArrayList;

public class Path extends AppCompatActivity implements View.OnClickListener {

    private static LinearLayout scrollView;
    private static TextView tv;
    private static Button forward, back, left, right, btn;
    LinearLayout mainLL;
    ImageButton map, inventory, journal;

    static GameClasses.World myWorld;
    static GameClasses.Hero king;

    @Override
    public void onBackPressed() {}

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        btn = (Button) findViewById(R.id.btn);
        scrollView = (LinearLayout) findViewById(R.id.scrollLayout);
        mainLL = (LinearLayout) findViewById(R.id.mainLL);
        tv = (TextView) findViewById(R.id.tv);
        right = (Button) findViewById(R.id.right);
        forward = (Button) findViewById(R.id.forward);
        back = (Button) findViewById(R.id.back);
        left = (Button) findViewById(R.id.left);
        map = (ImageButton) findViewById(R.id.map);
        inventory = (ImageButton) findViewById(R.id.inv);
        journal = (ImageButton) findViewById(R.id.journal);

        btn.setVisibility(View.INVISIBLE);
        left.setOnClickListener(this);
        right.setOnClickListener(this);
        forward.setOnClickListener(this);
        back.setOnClickListener(this);
        map.setOnClickListener(this);
        inventory.setOnClickListener(this);
        journal.setOnClickListener(this);

        myWorld = new GameClasses.World(getIntent().getIntExtra("difficult", 0));
        myWorld.questList = new ArrayList<>();
        myWorld.questList.add(new GameClasses.Quest("Месть сладка", new int[]{R.string.main_quest_first_step, R.string.main_quest_second_step}, new int[]{7, 5}, new GameClasses.QuestItem[]{null, null}));

        GameClasses.traderItems1 = new ArrayList<>();
        GameClasses.traderItems2 = new ArrayList<>();
        GameClasses.traderItems3 = new ArrayList<>();

        GameClasses.traderItems1.add(new GameClasses.Weapon(12, "Заточенный кинжал", 3, 0.7)); // Торговец оружием
        GameClasses.traderItems1.add(new GameClasses.Weapon(18, "Стальной меч", 8, 0.4));
        GameClasses.traderItems1.add(new GameClasses.Weapon(48, "Катана Старого Самурая", 14, 0.6));
        GameClasses.traderItems1.add(new GameClasses.Weapon(72, "Меч Эскалибур", 25, 0.2));
        GameClasses.traderItems1.add(new GameClasses.Armor(16, "Броня Оруженосца", 8));
        GameClasses.traderItems1.add(new GameClasses.Drink(16, "hp", "Ром", 30));

        GameClasses.traderItems2.add(new GameClasses.Armor(48, "Черепашьи доспехи", 24)); // Торговец бронёй
        GameClasses.traderItems2.add(new GameClasses.Armor(36, "Одеяния Мага", 18));
        GameClasses.traderItems2.add(new GameClasses.Armor(64, "Латы Рыцаря", 32));
        GameClasses.traderItems2.add(new GameClasses.Armor(100, "Небесные доспехи", 50));
        GameClasses.traderItems2.add(new GameClasses.Weapon(12, "Подсапожный нож", 4, 0.6));
        GameClasses.traderItems2.add(new GameClasses.Drink(22, "hp", "Медовуха", 40));

        GameClasses.traderItems3.add(new GameClasses.Drink(30, "hp", "Среднее зелье восстановления", 50)); // Торговец зельями
        GameClasses.traderItems3.add(new GameClasses.Drink(30, "mana", "Среднее зелье концентрации", 50));
        GameClasses.traderItems3.add(new GameClasses.Drink(64, "hp", "Большое зелье восстановления", 100));
        GameClasses.traderItems3.add(new GameClasses.Drink(64, "mana", "Большое зелье концентрации", 100));
        GameClasses.traderItems3.add(new GameClasses.Weapon(18, "Кинжал Алхимика", 6, 0.6));
        GameClasses.traderItems3.add(new GameClasses.Armor(16, "Нагрудник Пивовара", 8));

        king = new GameClasses.Hero();
        switch (getIntent().getIntExtra("class", 0)) {
            case 1:
                king.setWeapon(new GameClasses.Weapon(2, "Деревянная палка", 2, 0.05));
                king.setArmor(new GameClasses.Armor(1, "Рваный балахон", 1));
                king.setMoney(16);
                king.setHp(150);
                king.setHp_max(150);
                king.setMana(0);
                king.setMana_max(0);
                king.setX((int) (Math.random() * 15 + 5));
                king.setY((int) (Math.random() * 15 + 5));
                king.setCritical_multipler(1.8);
                break;
            case 2:
                king.setWeapon(new GameClasses.Weapon(2, "Деревянная палка", 2, 0.05));
                king.setArmor(new GameClasses.Armor(1, "Рваный балахон", 1));
                king.setMoney(16);
                king.setHp(100);
                king.setHp_max(100);
                king.setMana(100);
                king.setMana_max(100);
                king.setX((int) (Math.random() * 15 + 5));
                king.setY((int) (Math.random() * 15 + 5));
                king.setCritical_multipler(1.4);
                break;
            default:
                break;
        }

        king.name = getIntent().getStringExtra("name");
        king.strength = getIntent().getIntExtra("strength", 0);
        king.perception = getIntent().getIntExtra("perception", 0);
        king.endurance = getIntent().getIntExtra("endurance", 0);
        king.charisma = getIntent().getIntExtra("charisma", 0);
        king.intelligence = getIntent().getIntExtra("intelligence", 0);
        king.agility = getIntent().getIntExtra("agility", 0);
        king.luck = getIntent().getIntExtra("luck", 0);
        king.inventory = new ArrayList();

        king.inventory.add(new GameClasses.Weapon(2, "Деревянная палка", 2, 0.05));
        king.inventory.add(new GameClasses.Armor(1, "Рваный балахон", 1));
        king.inventory.add(new GameClasses.Drink(10, "hp", "Бутылка воды", 10));
        king.inventory.add(new GameClasses.Drink(15, "mana", "Лимонад Гаврош", 15));

        king.quest = myWorld.questList.get(0);

        Logic.isFightEnded = true;
        Logic.isBarmanVisited = false;
        Logic.isBanditLeaderDefeated = false;
        Logic.isCaveArmorFound = false;
        Logic.isCaveBearDefeated = false;
        Logic.isMagnusStaffFound = false;

        tv.setText("Добро пожаловать в Overthrown! Для того чтобы узнать ваше местоположение, используйте карту, а чтобы менять оружие, броню, пить напитики" +
                " или узнать свои хар-ки, зайдите в инвентарь.\nДля перемещения по миру нажимайте на соответственные кнопки внизу.");
    }

    public static void onDeath(final Context context) {
        setMoveButtonsUnclickable();
        tv.setText("Вы явно мертвы, насколько это возможно. Может вы хотите начать сначала?");

        Button newGame = new Button(context);
        newGame.setText("Новая игра");
        scrollView.addView(newGame);

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(context, ScrollingActivity.class);
                start.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(start);
            }
        });
    }

    public static void setMoveButtonsClickable() {
        forward.setClickable(true);
        back.setClickable(true);
        left.setClickable(true);
        right.setClickable(true);
    }

    public static void setMoveButtonsUnclickable() {
        forward.setClickable(false);
        back.setClickable(false);
        left.setClickable(false);
        right.setClickable(false);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forward: {
                if (king.x != 1) {
                    king.x--;
                    GameClasses.event(mainLL, tv, btn, GameClasses.World.map, king, getApplicationContext());
                } else {
                    tv.setText(getString(R.string.get_lost_string, king.name));
                }
                break;
            }
            case R.id.back: {
                if (king.x != 30) {
                    king.x++;
                    GameClasses.event(mainLL, tv, btn, GameClasses.World.map, king, getApplicationContext());
                } else {
                    tv.setText(getString(R.string.get_lost_string, king.name));
                }
                break;
            }

            case R.id.left: {
                if (king.y != 1) {
                    king.y--;
                    GameClasses.event(mainLL, tv, btn, GameClasses.World.map, king, getApplicationContext());
                } else {
                    tv.setText(getString(R.string.get_lost_string, king.name));
                }
                break;
            }

            case R.id.right: {
                if (king.y != 30) {
                    king.y++;
                    GameClasses.event(mainLL, tv, btn, GameClasses.World.map, king, getApplicationContext());
                } else {
                    tv.setText(getString(R.string.get_lost_string, king.name));
                }
                break;
            }
            case R.id.map: {
                Intent maps = new Intent(this, Map.class);
                startActivity(maps);
                break;
            }
            case R.id.inv: {
                Intent invs = new Intent(this, Inventory.class);
                startActivity(invs);
                break;
            }
            case R.id.journal: {
                Intent jrnls = new Intent(this, Journal.class);
                startActivity(jrnls);
                break;
            }
            default:
                break;
        }
    }
}