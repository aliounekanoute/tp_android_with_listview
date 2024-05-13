package com.example.exemplewithlistview;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MangaCharacterActivity extends AppCompatActivity {
    ImageView pic;
    TextView name;
    TextView title;
    TextView description;

    FloatingActionButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga_character);

        pic = findViewById(R.id.pic);
        name = findViewById(R.id.name);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        backButton = findViewById(R.id.backButton);

        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            MangaCharacter character = (MangaCharacter) extras.get("data");

            if(character != null) {
                int imageResId = this.getResources().getIdentifier(character.getImage(), "drawable", this.getPackageName());
                pic.setImageResource(imageResId);
                name.setText(character.getName());
                title.setText(character.getTitle());
                description.setText(character.getDescription());
            }
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}