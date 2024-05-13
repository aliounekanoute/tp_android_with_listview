package com.example.exemplewithlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MangaCharacterAdapter extends ArrayAdapter<MangaCharacter> {

    Context context;
    int layouId;
    MangaCharacter[] data;

    public MangaCharacterAdapter(@NonNull Context context, int resource, @NonNull MangaCharacter[] objects) {
        super(context, resource, objects);
        this.context = context;
        layouId = resource;
        data = objects;
    }

    @Override
    public MangaCharacter getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;

        LayoutInflater inflater = LayoutInflater.from(context);
        row = inflater.inflate(layouId, parent, false);

        ImageView pic = row.findViewById(R.id.pic);
        TextView name = row.findViewById(R.id.name);
        TextView title = row.findViewById(R.id.title);

        MangaCharacter character = data[position];

        name.setText(character.getName());
        title.setText(character.getTitle());

        int imageResId = context.getResources().getIdentifier(character.getImage(), "drawable", context.getPackageName());
        pic.setImageResource(imageResId);

        return row;
    }
}
