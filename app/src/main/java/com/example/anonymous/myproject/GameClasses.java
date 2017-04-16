package com.example.anonymous.myproject;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

class GameClasses {

    static class World {
        int difficult;
        static short[][] map;

        World() {
            map = new short[32][32];
            fillArr();
        }
    }

    abstract static class Item {
        String title, itemType;
    }

    static class Weapon extends Item {
        int damage;
        double critical;

        Weapon(String title, int dmg, double critical) {
            this.itemType = "weapon";
            this.title = title;
            this.damage = dmg;
            this.critical = critical;
        }
    }

    static class Armor extends Item {
        int armor;

        Armor(String title, int armor) {
            this.itemType = "armor";
            this.title = title;
            this.armor = armor;
        }
    }

    static class Drink extends Item {
        String drinkType;
        int bonus;

        Drink(String drinkType, String title, int bonus) {
            this.itemType = "drink";
            this.drinkType = drinkType;
            this.title = title;
            this.bonus = bonus;
        }
    }

    abstract static class Hero {
        List<Item> inventory;
        String name;
        Weapon weapon;
        Armor armor;
        int money, hp, mana, karma, strength, perception, endurance, charisma, intelligence, agility, luck, x, y;
        double critical_multipler;
    }

    static class Warrior extends Hero {
        Warrior() {
            this.weapon = new Weapon("Деревянная палка", 2, 0.05);
            this.armor = new Armor("Рваный балахон", 1);
            this.money = 5;
            this.hp = 150;
            this.mana = 0;
            this.karma = 50;
            this.x = (int) (Math.random() * 15 + 5);
            this.y = (int) (Math.random() * 15 + 5);
            this.critical_multipler = 1.1;
        }
    }

    static class Mage extends Hero {
        Mage() {
            this.weapon = new Weapon("Деревянная палка", 2, 0.05);
            this.armor = new Armor("Рваный балахон", 1);
            this.money = 5;
            this.hp = 100;
            this.mana = 10;
            this.karma = 50;
            this.x = (int) (Math.random() * 15 + 5);
            this.y = (int) (Math.random() * 15 + 5);
            this.critical_multipler = 1.1;
        }
    }

    static void event(LinearLayout ll, TextView tv, final Button btn, short[][] world, Hero hero, final Context context) {
        final Intent intent = new Intent(context, Action.class);
        switch (world[hero.y][hero.x]) {
            case 1:
                btn.setVisibility(View.INVISIBLE);
                ll.setBackgroundResource(R.mipmap.mountains);
                tv.setText(String.format(context.getString(R.string.mountains_walk_string), hero.name));
                break;
            case 2:
                btn.setVisibility(View.INVISIBLE);
                ll.setBackgroundResource(R.mipmap.desert);
                tv.setText(String.format(context.getString(R.string.desert_walk_string), hero.name));
                break;
            case 3:
                btn.setVisibility(View.INVISIBLE);
                ll.setBackgroundResource(R.mipmap.forest);
                tv.setText(String.format(context.getString(R.string.forest_walk_string), hero.name));
                break;
            case 4:
                btn.setVisibility(View.INVISIBLE);
                ll.setBackgroundResource(R.mipmap.winter);
                tv.setText(String.format(context.getString(R.string.winter_walk_string), hero.name));
                break;
            case 5:
                ll.setBackgroundResource(R.mipmap.tower);
                tv.setText(String.format(context.getString(R.string.magnus_event_string), hero.name));
                btn.setText("Зайти внутрь");
                btn.setVisibility(View.VISIBLE);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent.putExtra("action_type", "magnus_tower");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        btn.setVisibility(View.INVISIBLE);
                    }
                });
                break;
            case 6:
                ll.setBackgroundResource(R.mipmap.bandits);
                Path.setMoveButtonsUnclickable();
                tv.setText(String.format(context.getString(R.string.bandits_event_string), hero.name));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("action_type", "bandits_ambush");
                context.startActivity(intent);
                Path.setMoveButtonsClickable();
                break;
            case 7:
                ll.setBackgroundResource(R.mipmap.tavern);
                tv.setText(String.format(context.getString(R.string.tavern_event_string), hero.name));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("action_type", "tavern");
                context.startActivity(intent);
                break;
            case 8:
                ll.setBackgroundResource(R.mipmap.cave);
                tv.setText(String.format(context.getString(R.string.cave_event_string), hero.name));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("action_type", "cave");
                context.startActivity(intent);
                break;
            case 9:
                btn.setVisibility(View.INVISIBLE);
                ll.setBackgroundResource(R.mipmap.rainbow);
                tv.setText(String.format(context.getString(R.string.rainbow_event_string), hero.name));
                break;
            default:
                break;
        }
    }

    private static void fillArr() {
        int count = 0, count1 = 1;
        short p = 0, k = 0;
        int[] events = new int[5];

        for (int i = 0; i < 5; i++) {
            events[i] = i + 1;
        }

        for (int i = 0; i < 32; i++) {
            World.map[i][0] = -1;
            World.map[i][31] = -1;
            World.map[31][i] = -1;
            World.map[0][i] = -1;
        }

        for (int i = 0; i < 32; i++) {
            World.map[i][0] = -1;
            World.map[i][31] = -1;
            World.map[31][i] = -1;
            World.map[0][i] = -1;
        }

        for (int i = 1; i < 31; i++) {
            for (int j = 1; j < i + 1; j++) {
                World.map[i][j] = 1;
                World.map[j][i] = 2;
            }
        }

        for (int i = 1; i < 31; i++) {
            for (int j = 1; j < i + 1; j++) {
                World.map[31 - i][j] = 3;
                World.map[j][31 - i] = 4;
            }
        }

        while (count1 > 0) {
            for (int i = 1; i < 31; i++) {
                for (int j = 1; j < 31; j++) {
                    if ((int) (Math.random() * 60) == 50) {
                        count1 = 0;
                        while (k == 0) {
                            for (int l = 0; l < 5; l++) {
                                if (events[l] != 0) {
                                    count1++;
                                }
                            }
                            if (count1 == 0) {
                                break;
                            }
                            if (count != 0)
                                k = p;
                            else
                                k = (short) (Math.random() * 5 + 5);
                            if (events[k - 5] != 0) {
                                if (World.map[i][j] < 5) {
                                    World.map[i][j] = k;
                                    events[k - 5]--;
                                }
                            } else {
                                k = 0;
                                count++;
                                p = k++;
                                /*if (k == 4)
                                    p = 0;
                                else
                                    p = k++;*/
                            }
                        }
                        k = 0;
                        count = 0;
                    }
                }
                if (count1 == 0)
                    break;
            }
            for (int l = 0; l < 5; l++) {
                if (events[l] != 0) {
                    count1++;
                }
            }
        }
    }
}