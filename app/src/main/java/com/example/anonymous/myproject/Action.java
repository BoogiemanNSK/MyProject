package com.example.anonymous.myproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

public class Action extends AppCompatActivity {

    Context context;
    LinearLayout actionLL;
    LinearLayout scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        context = getApplicationContext();

        actionLL = (LinearLayout) findViewById(R.id.actionLL);
        scrollView = (LinearLayout) findViewById(R.id.scrollActionLayout);

        switch (getIntent().getStringExtra("action_type")) {
            case "magnus_tower":
                actionMagnusTower();
                break;
            case "bandits_ambush":
                actionBanditsAmbush();
                break;
            case "tavern":
                actionTavern();
                break;
            case "cave":
                actionCaveEntrance();
                break;
            default:
                break;
        }
    }

    private void actionMagnusTower() {
        actionLL.setBackgroundResource(R.mipmap.magnus);

        final TextView tv1 = new TextView(context);
        tv1.setText("Маг: Что... Что ты здесь делаешь!? Пошёл вон! Убирайся, пока ничего здесь не сломал!");
        scrollView.addView(tv1);

        final Button btn1 = new Button(context);
        btn1.setText("Спокойно, я не создам никаких проблем [Интеллект " + Path.king.intelligence + "/6]");
        scrollView.addView(btn1);

        final Button btn2 = new Button(context);
        btn2.setText("Извините, кажется я заблудился [Уйти]");
        scrollView.addView(btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setOnClickListener(null);
                btn2.setOnClickListener(null);
                final TextView tv2 = new TextView(context);
                scrollView.addView(tv2);
                if (Path.king.intelligence >= 6) {
                    tv2.setText("Обойдя магическую руну на полу, вы подходите к магу.\nМаг: Ну надо же, какой смышлёный. Послушай, мне сейчас не до тебя, хочешь," +
                            " возьми этот дурацкий посох, мне вообще плевать, только не возвращайся сюда.\n[Посох Самосожжения добавлен в инвентарь]");
                    Path.king.inventory.add(new GameClasses.Weapon("Посох самоможжения", 10, 0.3));

                    final Button btn3 = new Button(context);
                    btn3.setText("Спасибо, ну, бывай. [Уйти]");
                    scrollView.addView(btn3);

                    btn3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btn3.setOnClickListener(null);
                            clearSpace(Path.king.x, Path.king.y);
                            finish();
                        }
                    });
                } else {
                    tv2.setText("Целеустремлённо направившись к магу Вы наступаете прямо на Руну призыва Огненного Дождя, таким образом призывая этот самый дождь." +
                            "Из метеоритов. Огненных. Коротко говоря, от башни и мокрого места не осталось. Впрочем, как и от Вас.");

                    final Button btn3 = new Button(context);
                    btn3.setText("Досадно.");
                    scrollView.addView(btn3);

                    btn3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btn3.setOnClickListener(null);
                            Path.onDeath(context);
                            finish();
                        }
                    });
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setOnClickListener(null);
                btn2.setOnClickListener(null);
                finish();
            }
        });
    }

    private void actionBanditsAmbush() {
        actionLL.setBackgroundResource(R.mipmap.bandit_leader);

        final TextView tv1 = new TextView(context);
        tv1.setText("Главарь Бандитов: Эй, урод! Не хочешь поближе познакомиться с моей дубиной?");
        scrollView.addView(tv1);

        final Button btn1 = new Button(context);
        btn1.setText("Эээ, нет? [Бой]");
        scrollView.addView(btn1);

        final Button btn2 = new Button(context);
        btn2.setText("Может лучше я познакомлю тебя со своими денюжками? [Харизма " + Path.king.charisma + "/5, Золото " + Path.king.money + "/50]");
        scrollView.addView(btn2);

        final Button btn3 = new Button(context);
        btn3.setText("Полегче, приятель! Я такой же бродяга как и вы, мы, изгои общества, должны помогать друг другу! [Харизма " + Path.king.charisma + "/9]");
        scrollView.addView(btn3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setOnClickListener(null);
                btn2.setOnClickListener(null);
                btn3.setOnClickListener(null);

                Intent fightIntent = new Intent(context, Fight.class);
                fightIntent.putExtra("enemyNum", 3);

                fightIntent.putExtra("enemyName0", "Худой Бандит");
                fightIntent.putExtra("enemyHP0", 8);
                fightIntent.putExtra("enemyDmg0", 4);
                fightIntent.putExtra("enemyArmor0", 8);

                fightIntent.putExtra("enemyName1", "Толстый Бандит");
                fightIntent.putExtra("enemyHP1", 16);
                fightIntent.putExtra("enemyDmg1", 4);
                fightIntent.putExtra("enemyArmor1", 8);

                fightIntent.putExtra("enemyName2", "Главарь Бандитов");
                fightIntent.putExtra("enemyHP2", 16);
                fightIntent.putExtra("enemyDmg2", 8);
                fightIntent.putExtra("enemyArmor2", 20);

                startActivity(fightIntent);

                final Button btn5 = new Button(context);
                btn5.setText("[Продолжить]");
                scrollView.addView(btn5);

                btn5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btn5.setOnClickListener(null);

                        if (!Logic.isLastFightWin) {
                            Path.onDeath(context);
                            finish();
                        }

                        final TextView tv5 = new TextView(context);
                        tv5.setText(" Что ж, бандиты убиты. Из полезного вам приглянулись Факел и Дубина Главаря Бандитов\n [Дубина Главаря Бандитов добавлена в инвентарь]" +
                                "\n [Факел добавлен в инвентарь]");
                        scrollView.addView(tv5);

                        Path.king.inventory.add(new GameClasses.Weapon("Дубина Главаря Бандитов", 8, 0.4));
                        Path.king.inventory.add(new GameClasses.QuestItem("Факел"));

                        final Button btn6 = new Button(context);
                        btn6.setText("Продолжить путь");
                        scrollView.addView(btn6);

                        btn6.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                btn6.setOnClickListener(null);
                                clearSpace(Path.king.x, Path.king.y);
                                finish();
                            }
                        });
                    }
                });
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setOnClickListener(null);
                btn2.setOnClickListener(null);
                btn3.setOnClickListener(null);
                if ((Path.king.charisma >= 5) && (Path.king.money >= 50)) {
                    final TextView tv4 = new TextView(context);
                    tv4.setText("Главарь Бандитов: Ха! По мне так идея что надо! Давай сюда золотые и проваливай!\n[-50 золотых]");
                    scrollView.addView(tv4);

                    Path.king.money -= 50;

                    final Button btn4 = new Button(context);
                    btn4.setText("Вот, пожалуйста, развлекайтесь.");
                    scrollView.addView(btn4);

                    btn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btn4.setOnClickListener(null);
                            clearSpace(Path.king.x, Path.king.y);
                            finish();
                        }
                    });
                } else {
                    final TextView tv4 = new TextView(context);
                    tv4.setText("Главарь Бандитов: Зачем мне твои жалкие монеты, если я могу забрать всё с твоего трупа?");
                    scrollView.addView(tv4);

                    final Button btn4 = new Button(context);
                    btn4.setText("Что ж, защищайтесь, глупцы!");
                    scrollView.addView(btn4);

                    btn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btn4.setOnClickListener(null);

                            Intent fightIntent = new Intent(context, Fight.class);
                            fightIntent.putExtra("enemyNum", 3);

                            fightIntent.putExtra("enemyName0", "Худой Бандит");
                            fightIntent.putExtra("enemyHP0", 8);
                            fightIntent.putExtra("enemyDmg0", 4);
                            fightIntent.putExtra("enemyArmor0", 8);

                            fightIntent.putExtra("enemyName1", "Толстый Бандит");
                            fightIntent.putExtra("enemyHP1", 16);
                            fightIntent.putExtra("enemyDmg1", 4);
                            fightIntent.putExtra("enemyArmor1", 8);

                            fightIntent.putExtra("enemyName2", "Главарь Бандитов");
                            fightIntent.putExtra("enemyHP2", 16);
                            fightIntent.putExtra("enemyDmg2", 8);
                            fightIntent.putExtra("enemyArmor2", 20);

                            startActivity(fightIntent);

                            final Button btn5 = new Button(context);
                            btn5.setText("[Продолжить]");
                            scrollView.addView(btn5);

                            btn5.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    btn5.setOnClickListener(null);

                                    if (!Logic.isLastFightWin) {
                                        Path.onDeath(context);
                                        finish();
                                    }

                                    final TextView tv5 = new TextView(context);
                                    tv5.setText(" Что ж, бандиты убиты. Из полезного вам приглянулись Факел и Дубина Главаря Бандитов\n [Дубина Главаря Бандитов добавлена в инвентарь]" +
                                            "\n [Факел добавлен в инвентарь]");
                                    scrollView.addView(tv5);

                                    Path.king.inventory.add(new GameClasses.Weapon("Дубина Главаря Бандитов", 8, 0.4));
                                    Path.king.inventory.add(new GameClasses.QuestItem("Факел"));

                                    final Button btn6 = new Button(context);
                                    btn6.setText("Продолжить путь");
                                    scrollView.addView(btn6);

                                    btn6.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            btn6.setOnClickListener(null);
                                            clearSpace(Path.king.x, Path.king.y);
                                            finish();
                                        }
                                    });
                                }
                            });
                        }
                    });
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setOnClickListener(null);
                btn2.setOnClickListener(null);
                btn3.setOnClickListener(null);
                if (Path.king.charisma >= 9) {
                    final TextView tv4 = new TextView(context);
                    tv4.setText("Главарь Бандитов: Чёрт меня раздери, если этот парень не прав! Ступай своей дорогой, дружище, и вот возьми, тебе это нужнее.\n" +
                            "[Факел добавлен в инвентарь]");
                    scrollView.addView(tv4);

                    Path.king.inventory.add(new GameClasses.QuestItem("Факел"));

                    final Button btn4 = new Button(context);
                    btn4.setText("Спасибо! Удачи вам, ребята.");
                    scrollView.addView(btn4);

                    btn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btn4.setOnClickListener(null);
                            clearSpace(Path.king.x, Path.king.y);
                            finish();
                        }
                    });
                } else {
                    final TextView tv4 = new TextView(context);
                    tv4.setText("Главарь Бандитов: Ты лжец! И я отрежу твой чёртов язык!");
                    scrollView.addView(tv4);

                    final Button btn4 = new Button(context);
                    btn4.setText("Что ж, защищайтесь, глупцы!");
                    scrollView.addView(btn4);

                    btn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btn4.setOnClickListener(null);

                            Intent fightIntent = new Intent(context, Fight.class);
                            fightIntent.putExtra("enemyNum", 3);

                            fightIntent.putExtra("enemyName0", "Худой Бандит");
                            fightIntent.putExtra("enemyHP0", 8);
                            fightIntent.putExtra("enemyDmg0", 4);
                            fightIntent.putExtra("enemyArmor0", 8);

                            fightIntent.putExtra("enemyName1", "Толстый Бандит");
                            fightIntent.putExtra("enemyHP1", 16);
                            fightIntent.putExtra("enemyDmg1", 4);
                            fightIntent.putExtra("enemyArmor1", 8);

                            fightIntent.putExtra("enemyName2", "Главарь Бандитов");
                            fightIntent.putExtra("enemyHP2", 16);
                            fightIntent.putExtra("enemyDmg2", 8);
                            fightIntent.putExtra("enemyArmor2", 20);

                            startActivity(fightIntent);

                            final Button btn5 = new Button(context);
                            btn5.setText("[Продолжить]");
                            scrollView.addView(btn5);

                            btn5.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    btn5.setOnClickListener(null);

                                    if (!Logic.isLastFightWin) {
                                        Path.onDeath(context);
                                        finish();
                                    }

                                    final TextView tv5 = new TextView(context);
                                    tv5.setText(" Что ж, бандиты убиты. Из полезного вам приглянулись Факел и Дубина Главаря Бандитов\n [Дубина Главаря Бандитов добавлена в инвентарь]" +
                                            "\n [Факел добавлен в инвентарь]");
                                    scrollView.addView(tv5);

                                    Path.king.inventory.add(new GameClasses.Weapon("Дубина Главаря Бандитов", 8, 0.4));
                                    Path.king.inventory.add(new GameClasses.QuestItem("Факел"));

                                    final Button btn6 = new Button(context);
                                    btn6.setText("Продолжить путь");
                                    scrollView.addView(btn6);

                                    btn6.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            btn6.setOnClickListener(null);
                                            clearSpace(Path.king.x, Path.king.y);
                                            finish();
                                        }
                                    });
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    private void actionTavern() {

    }

    private void actionCaveEntrance() {
        actionLL.setBackgroundResource(R.mipmap.dark_cave);

        final TextView tv1 = new TextView(context);
        tv1.setText("Вы проходите пару метров, минуя острые камни, пока наконец не понимаете, что в пещере стало совсем темно.");
        scrollView.addView(tv1);

        final Button btn1 = new Button(context);
        btn1.setText("Со зрением у меня всё в полном порядке, не пропаду [Восприятие " + Path.king.perception + "/8]");
        scrollView.addView(btn1);

        final Button btn2 = new Button(context);
        btn2.setText("У меня с собой как раз есть факел [Необходим факел в инвентаре]");
        scrollView.addView(btn2);

        final Button btn3 = new Button(context);
        btn3.setText("Вернусь позже [Уйти]");
        scrollView.addView(btn3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setOnClickListener(null);
                btn2.setOnClickListener(null);
                btn3.setOnClickListener(null);
                if (Path.king.perception >= 8) {
                    actionLL.setBackgroundResource(R.mipmap.inside_cave_dark);
                    actionCaveInside();
                } else {
                    final TextView tv2 = new TextView(context);
                    scrollView.addView(tv2);

                    tv2.setText("Самоуверенно шагнув в пустоту, вы действительно шагаете в пустоту и пролетаете несколько метров, пока не разбиваетесь об острые сталагмиты.");

                    final Button btn4 = new Button(context);
                    btn4.setText("Досадно.");
                    scrollView.addView(btn4);

                    btn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btn4.setOnClickListener(null);
                            Path.onDeath(context);
                            finish();
                        }
                    });
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setOnClickListener(null);
                btn2.setOnClickListener(null);
                btn3.setOnClickListener(null);
                if (checkTorch()) {
                    actionLL.setBackgroundResource(R.mipmap.inside_cave_torch);
                    actionCaveInside();
                } else {
                    final TextView tv2 = new TextView(context);
                    scrollView.addView(tv2);

                    tv2.setText("Самоуверенно шагнув в пустоту, вы действительно шагаете в пустоту и пролетаете несколько метров, пока не разбиваетесь об острые сталагмиты.");

                    final Button btn4 = new Button(context);
                    btn4.setText("Досадно.");
                    scrollView.addView(btn4);

                    btn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btn4.setOnClickListener(null);
                            Path.onDeath(context);
                            finish();
                        }
                    });
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setOnClickListener(null);
                btn2.setOnClickListener(null);
                btn3.setOnClickListener(null);
                finish();
            }
        });
    }

    private void actionCaveInside() {
        final TextView cave_tv1 = new TextView(context);
        cave_tv1.setText("Вы бредёте по пещере, гадая, что же вас ожидает впереди...");
        scrollView.addView(cave_tv1);

        final Button cave_button1 = new Button(context);
        cave_button1.setText("Пусть мне повезёт [Удача " + Path.king.luck + "]");
        scrollView.addView(cave_button1);

        final int luck = (int) (Math.random() * 15) * Path.king.luck;

        cave_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cave_button1.setOnClickListener(null);
                if (luck < 20) {
                    actionLL.setBackgroundResource(R.mipmap.big_bear);
                    final TextView cave_tv2 = new TextView(context);
                    cave_tv2.setText("По всей пещере сплошь и рядом раскиданы кости и черепа... Вскоре ваши худшие опасения подтверждаются и из тьмы на вас вываливается Огромный " +
                            "Пещерный Медведь. С громким боевым кличем вы атакуете его!");
                    scrollView.addView(cave_tv2);

                    final Button cave_button2 = new Button(context);
                    cave_button2.setText("В бой!");
                    scrollView.addView(cave_button2);

                    cave_button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cave_button2.setOnClickListener(null);

                            Intent fightIntent = new Intent(context, Fight.class);
                            fightIntent.putExtra("enemyNum", 1);
                            fightIntent.putExtra("enemyName0", "Огромный Пещерный Медведь");
                            fightIntent.putExtra("enemyHP0", 40);
                            fightIntent.putExtra("enemyDmg0", 8);
                            fightIntent.putExtra("enemyArmor0", 0);
                            startActivity(fightIntent);

                            final Button cave_button3 = new Button(context);
                            cave_button3.setText("[Продолжить]");
                            scrollView.addView(cave_button3);

                            cave_button3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    cave_button3.setOnClickListener(null);

                                    if (!Logic.isLastFightWin) {
                                        Path.onDeath(context);
                                        finish();
                                    }

                                    final TextView cave_tv3 = new TextView(context);
                                    cave_tv3.setText(" Медведь пал замервто... А ведь из его шкуры выйдет неплохая броня!\n [Броня из шкуры Пещерного Медведя добавлена в инвентарь]");
                                    scrollView.addView(cave_tv3);

                                    Path.king.inventory.add(new GameClasses.Armor("Броня из шкуры Пещерного Медведя", 40));

                                    final Button cave_button4 = new Button(context);
                                    cave_button4.setText("Пора сваливать из этой дыры!");
                                    scrollView.addView(cave_button4);

                                    cave_button4.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            cave_button4.setOnClickListener(null);
                                            clearSpace(Path.king.x, Path.king.y);
                                            finish();
                                        }
                                    });
                                }
                            });
                        }
                    });
                } else if (luck < 50) {
                    actionLL.setBackgroundResource(R.mipmap.gold_bag);
                    final TextView cave_tv2 = new TextView(context);
                    cave_tv2.setText("Пещера оказывается пуста, разве что вы находите небольшой мешочек с золотыми, видимо чей-то небольшой тайник.\n[+25 золотых]");
                    scrollView.addView(cave_tv2);

                    Path.king.money += 25;

                    final Button cave_button2 = new Button(context);
                    cave_button2.setText("Ну что ж, уходим отсюда");
                    scrollView.addView(cave_button2);

                    cave_button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cave_button2.setOnClickListener(null);
                            clearSpace(Path.king.x, Path.king.y);
                            finish();
                        }
                    });
                } else {
                    actionLL.setBackgroundResource(R.mipmap.many_gold);
                    final TextView cave_tv2 = new TextView(context);
                    cave_tv2.setText("Похоже вам крупно повезло, иначе как вы объясните кучу золота и Крепкую Броню Наемника, которые вы нашли, бродя по пещере?\n[+150 золотых]" +
                            "\n[Крепкая Броня Наемника добавлена в инвентарь]");
                    scrollView.addView(cave_tv2);

                    Path.king.inventory.add(new GameClasses.Armor("Крепкая Броня Наёмника", 20));
                    Path.king.money += 150;

                    final Button cave_button2 = new Button(context);
                    cave_button2.setText("Ну что ж, уходим отсюда");
                    scrollView.addView(cave_button2);

                    cave_button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cave_button2.setOnClickListener(null);
                            clearSpace(Path.king.x, Path.king.y);
                            finish();
                        }
                    });
                }
            }
        });
    }

    private boolean checkTorch() {
        for (GameClasses.Item i: Path.king.inventory) {
            if ((Objects.equals(i.title, "Факел")) || (Objects.equals(i.title, "Torch")) )
                return true;
        }
        return false;
    }

    private void clearSpace(int x, int y){
        if ((y >= x) && (y >= 30 - x))
            GameClasses.World.map[y][x] = 1;
        else if ((y <= x) && (y >= 30 - x))
            GameClasses.World.map[y][x] = 2;
        else if ((y <= x) && (y <= 30 - x))
            GameClasses.World.map[y][x] = 3;
        else if ((y >= x) && (y <= 30 - x))
            GameClasses.World.map[y][x] = 4;
    }

}