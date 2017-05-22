package com.example.anonymous.myproject;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        GameClasses.Item item = items.get(position);

        if (Logic.isTrading) {
            viewHolder.itemPrice.setText(String.valueOf(item.price));
            viewHolder.itemLayout.setBackgroundColor(Color.argb(256, 0, 0, 0));
            for (GameClasses.Item i: Trade.itemsToSell)
                if (item.title.equals(i.title))
                    viewHolder.itemLayout.setBackgroundColor(Color.argb(128, 64, 64, 0));
            for (GameClasses.Item i: Trade.itemsToBuy)
                if (item.title.equals(i.title))
                    viewHolder.itemLayout.setBackgroundColor(Color.argb(128, 64, 64, 0));
        }

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
                viewHolder.criticalView.setText("");
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
                viewHolder.criticalView.setText("");
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
            case "quest_item":
                viewHolder.imageView.setImageResource(R.mipmap.quest_item);
                viewHolder.nameView.setText(item.title);
                viewHolder.tickView.setVisibility(View.GONE);
                viewHolder.bonusView.setText("");
                viewHolder.criticalView.setText("");
            default:
                break;
        }

        return convertView;
    }

    private class ViewHolder {
        final LinearLayout itemLayout;
        final ImageView imageView, tickView;
        final TextView nameView, bonusView, criticalView, itemPrice;

        ViewHolder(View view) {
            itemLayout = (LinearLayout) view.findViewById(R.id.itemLayout);
            imageView = (ImageView) view.findViewById(R.id.item_icon);
            tickView = (ImageView) view.findViewById(R.id.tick);
            nameView = (TextView) view.findViewById(R.id.name);
            bonusView = (TextView) view.findViewById(R.id.bonus);
            criticalView = (TextView) view.findViewById(R.id.critical_chance);
            itemPrice = (TextView) view.findViewById(R.id.itemPrice);
        }
    }
}