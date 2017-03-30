package com.example.anonymous.myproject;

import android.content.Context;
import android.widget.TextView;

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

    private static class Weapon extends Item {
        int dmg;
        double critical, critical_multiplier;

        Weapon(String title, int dmg, double critical, double critical_multiplier) {
            this.itemType = "weapon";
            this.title = title;
            this.dmg = dmg;
            this.critical = critical;
            this.critical_multiplier = critical_multiplier;
        }
    }

    private static class Armor extends Item {
        int armor;

        Armor(String title, int armor) {
            this.itemType = "armor";
            this.title = title;
            this.armor = armor;
        }
    }

    private static class Drink extends Item {
        String drinkType;
        int bonus;

        Drink(String drinkType, int bonus) {
            this.drinkType = drinkType;
            this.bonus = bonus;
        }
    }

    abstract static class Hero {
        String name;
        Weapon weapon;
        Armor armor;
        int money, hp, mana, karma, strength, perception, endurance, charisma, intelligence, agility, luck, x, y;
    }

    static class Warrior extends Hero {
        Warrior() {
            this.weapon = new Weapon("Деревянная палка", 2, 0.05, 1.1);
            this.armor = new Armor("Рваный балахон", 1);
            this.money = 5;
            this.hp = 150;
            this.mana = 0;
            this.karma = 50;
            this.x = (int) (Math.random() * 15 + 5);
            this.y = (int) (Math.random() * 15 + 5);
        }
    }

    static class Mage extends Hero {
        Mage() {
            this.weapon = new Weapon("Деревянная палка", 2, 0.05, 1.1);
            this.armor = new Armor("Рваный балахон", 1);
            this.money = 5;
            this.hp = 100;
            this.mana = 10;
            this.karma = 50;
            this.x = (int) (Math.random() * 15 + 5);
            this.y = (int) (Math.random() * 15 + 5);
        }
    }

    static void event(TextView tv, short[][] world, Hero hero, Context context) {
        switch (world[hero.y][hero.x]) {
            case 1: {
                tv.setText(String.format(context.getString(R.string.mountains_walk_string), hero.name));
                break;
            }
            case 2: {
                tv.setText(String.format(context.getString(R.string.desert_walk_string), hero.name));
                break;
            }
            case 3: {
                tv.setText(String.format(context.getString(R.string.forest_walk_string), hero.name));
                break;
            }
            case 4: {
                tv.setText(String.format(context.getString(R.string.winter_walk_string), hero.name));
                break;
            }
            case 5: {
                tv.setText(String.format(context.getString(R.string.magnus_event_string), hero.name));
                break;
            }
            case 6: {
                tv.setText(String.format(context.getString(R.string.bandits_event_string), hero.name));
                break;
            }
            case 7: {
                tv.setText(String.format(context.getString(R.string.tavern_event_string), hero.name));
                break;
            }
            case 8: {
                tv.setText(String.format(context.getString(R.string.cave_event_string), hero.name));
                break;
            }
            case 9: {
                tv.setText(String.format(context.getString(R.string.rainbow_event_string), hero.name));
                break;
            }
            default: {
                break;
            }
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