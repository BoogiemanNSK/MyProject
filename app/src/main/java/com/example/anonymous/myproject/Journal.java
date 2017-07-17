package com.example.anonymous.myproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Journal extends AppCompatActivity implements AdapterView.OnItemClickListener {

    QuestAdapter questAdapter;
    ListView questList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_journal);

        questList = (ListView) findViewById(R.id.questList);
        questAdapter = new QuestAdapter(getApplicationContext(), R.layout.journal_quest, Path.myWorld.questList);
        questList.setAdapter(questAdapter);
        questList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final GameClasses.Quest selectedQuest = (GameClasses.Quest) parent.getItemAtPosition(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(Journal.this);

        builder.setTitle("Выберите действие")
                .setNegativeButton("Сделать активным", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Path.king.quest = selectedQuest;
                        questAdapter.notifyDataSetInvalidated();
                    }
                });

        builder.create().show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public static void questUpdate(){
        for (GameClasses.Quest quest: Path.myWorld.questList) {
            if (quest.questTitle.equals(Path.king.quest.questTitle))
                Path.king.quest.stepNum = quest.stepNum;
        }
    }
}
