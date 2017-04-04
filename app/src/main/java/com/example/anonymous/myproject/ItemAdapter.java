package com.example.anonymous.myproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class ItemAdapter extends ArrayAdapter<GameClasses.Item> {
    private LayoutInflater inflater;
    private List<GameClasses.Item> items;
    private int layout;

    ItemAdapter(Context context, int resource, List<GameClasses.Item> items) {
        super(context, resource, items);
        this.layout = resource;
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        GameClasses.Item item = items.get(position);

        switch (item.itemType) {
            case "weapon":
                GameClasses.Weapon currentWeapon = (GameClasses.Weapon) item;
                viewHolder.imageView.setImageResource(R.mipmap.weapon);
                viewHolder.nameView.setText(currentWeapon.title);
                viewHolder.bonusView.setText("Урон: " + String.valueOf(currentWeapon.damage));
                viewHolder.criticalView.setText("Шанс крита: " + String.valueOf(currentWeapon.critical * 100) + "%");
                if (currentWeapon.title.equals(Inventory.currentWeapon.title)) {
                    viewHolder.tickView.setVisibility(View.VISIBLE);
                    viewHolder.tickView.setImageResource(R.mipmap.tick);
                } else
                    viewHolder.tickView.setVisibility(View.INVISIBLE);
                break;
            case "armor":
                GameClasses.Armor currentArmor = (GameClasses.Armor) item;
                viewHolder.imageView.setImageResource(R.mipmap.armor);
                viewHolder.nameView.setText(currentArmor.title);
                viewHolder.bonusView.setText("Защита: " + String.valueOf(currentArmor.armor));
                if (currentArmor.title.equals(Inventory.currentArmor.title)) {
                    viewHolder.tickView.setVisibility(View.VISIBLE);
                    viewHolder.tickView.setImageResource(R.mipmap.tick);
                } else
                    viewHolder.tickView.setVisibility(View.GONE);
                break;
            case "drink":
                GameClasses.Drink currentDrink = (GameClasses.Drink) item;
                viewHolder.imageView.setImageResource(R.mipmap.drink);
                viewHolder.nameView.setText(item.title);
                viewHolder.tickView.setVisibility(View.GONE);
                switch (currentDrink.drinkType) {
                    case "hp":
                        viewHolder.bonusView.setText("+" + String.valueOf(currentDrink.bonus) + " HP");
                        break;
                    case "mana":
                        viewHolder.bonusView.setText("+" + String.valueOf(currentDrink.bonus) + " Mana");
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

        return convertView;
    }

    private class ViewHolder {
        final ImageView imageView, tickView;
        final TextView nameView, bonusView, criticalView;

        ViewHolder(View view){
            imageView = (ImageView)view.findViewById(R.id.item_icon);
            tickView = (ImageView)view.findViewById(R.id.tick);
            nameView = (TextView) view.findViewById(R.id.name);
            bonusView = (TextView) view.findViewById(R.id.bonus);
            criticalView = (TextView) view.findViewById(R.id.critical_chance);
        }
    }
}