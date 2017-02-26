package com.example.anonymous.myproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Anonymous on 19.02.2017.
 */

class GameClasses {
    private static Hero hero;
    private static World world;


    static class World {

        static short[][] map;

        World() {
            map = new short[32][32];
            fillArr();
        }

    }

    static class Weapon{
        String title;
        int dmg;
        double critical,criticalmultiplier;
    }

    static class TreeStick extends Weapon{
    TreeStick(){
    this.title="Деревянная палка";
        this.dmg=2;
        this.critical=0.05;
        this.criticalmultiplier=1.1;
    }
    }

    static class Armour{
        String title;
        int armor;
    }
    static class RaggedRobe extends Armour{
        RaggedRobe(){
            this.title="Рваный балахон";
            this.armor=1;
        }
    }
     static class Hero {
        static String name;
        Weapon weapon;
         Armour armour;
         static int money, hp,karma, strength, perception, endurance, charisma, intelligence, agility, luck;
        static int x, y;

    }

    static class Class1 extends Hero{
        Class1(){
            this.weapon= new TreeStick();
            this.armour=new RaggedRobe();
            this.money=5;
            this.hp=100;
            this.karma=50;
            this.strength=5;
            this.perception=5;
            this.endurance=5;
            this.charisma=5;
            this.intelligence=5;
            this.agility=5;
            this.luck=5;
            this.x=(int)(Math.random()*15+5);
            this.y=(int)(Math.random()*15+5);
        }

    }
    static class Class2 extends Hero{
        Class2(){
            this.weapon= new TreeStick();
            this.armour=new RaggedRobe();
            this.money=5;
            this.hp=150;
            this.karma=50;
            this.strength=5;
            this.perception=5;
            this.endurance=5;
            this.charisma=5;
            this.intelligence=5;
            this.agility=5;
            this.luck=10;
            this.x=(int)(Math.random()*15+5);
            this.y=(int)(Math.random()*15+5);
        }

    }


     static void event(TextView tv, short[][] world,Hero hero,String name) {
        switch (world[hero.y][hero.x]) {
            case 1: {
                tv.setText(name+", "+"Вы бредёте по горам, ни на что не натыкаясь.");
                break;
            }
            case 2: {
                tv.setText(name+", "+"Вы бредёте по пустыне, ни на что не натыкаясь.");
                break;
            }
            case 3: {
                tv.setText(name+", "+"Вы бредёте по лесу, ни на что не натыкаясь.");
                break;
            }
            case 4: {
                tv.setText(name+", "+"Вы бредёте по снежной долине, ни на что не натыкаясь.");
                break;
            }
            case 5: {
                tv.setText(name+", "+"Вы натыкаетесь на великую башню Магнуса. Потрясающее явление и очень редкое.");
                break;
            }
            case 6: {
                tv.setText(name+", "+"Вы замечаете бандитов, насилующих девушку, но проходите мимо.");
                break;
            }
            case 7: {
                tv.setText(name+", "+"Вы замечаете таверну, однако не решаетесь зайти.");
                break;
            }
            case 8: {
                tv.setText(name+", "+"Вы видите пещеру, но обходите её стороной.");
                break;
            }
            case 9: {
                tv.setText(name+", "+"Вы видите радугу. Мило.");
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
            world.map[i][0] = -1;
            world.map[i][31] = -1;
            world.map[31][i] = -1;
            world.map[0][i] = -1;
        }

        for (int i = 0; i < 32; i++) {
            world.map[i][0] = -1;
            world.map[i][31] = -1;
            world.map[31][i] = -1;
            world.map[0][i] = -1;
        }

        for (int i = 1; i < 31; i++) {
            for (int j = 1; j < i + 1; j++) {
                world.map[i][j] = 1;
                world.map[j][i] = 2;
            }
        }

        for (int i = 1; i < 31; i++) {
            for (int j = 1; j < i + 1; j++) {
                world.map[31 - i][j] = 3;
                world.map[j][31 - i] = 4;
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
                                if (world.map[i][j] < 5) {
                                    world.map[i][j] = k;
                                    events[k - 5]--;
                                }
                            } else {
                                k = 0;
                                count++;
                                if (k == 4)
                                    p = 0;
                                else
                                    p = k++;
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

