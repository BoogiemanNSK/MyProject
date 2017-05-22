package com.example.anonymous.myproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class QuestAdapter extends ArrayAdapter<GameClasses.Quest> {
    private LayoutInflater inflater;
    private List<GameClasses.Quest> quests;
    private int layout;

    QuestAdapter(Context applicationContext, int journal_quest, List<GameClasses.Quest> questList) {
        super(applicationContext, journal_quest, questList);
        this.layout = journal_quest;
        this.quests = questList;
        this.inflater = LayoutInflater.from(applicationContext);
    }

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        GameClasses.Quest quest = quests.get(position);

        viewHolder.nameView.setText(quest.questTitle);
        viewHolder.textView.setText(quest.stepTextID[quest.stepNum]);
        if (quest.questTitle.equals(Path.king.quest.questTitle)) {
            viewHolder.crossView.setVisibility(View.VISIBLE);
        } else
            viewHolder.crossView.setVisibility(View.INVISIBLE);

        return convertView;
    }

    private class ViewHolder {
        final TextView nameView, textView;
        final ImageView crossView;

        ViewHolder(View view) {
            nameView = (TextView) view.findViewById(R.id.questTitle);
            textView = (TextView) view.findViewById(R.id.questText);
            crossView = (ImageView) view.findViewById(R.id.path);
        }
    }
}