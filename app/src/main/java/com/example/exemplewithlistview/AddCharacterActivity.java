package com.example.exemplewithlistview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddCharacterActivity extends AppCompatActivity {

    EditText image;
    EditText name;
    EditText title;
    EditText description;
    Button addButton;
    FloatingActionButton backButton;

    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_character);
        myDatabaseHelper = new MyDatabaseHelper(this);

        image = findViewById(R.id.image);
        name = findViewById(R.id.name);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);

        addButton = findViewById(R.id.add_button);
        backButton = findViewById(R.id.backButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(
                        isNotEmpty(image) &&
                        isNotEmpty(name) &&
                        isNotEmpty(title) &&
                        isNotEmpty(description)
                ) {
                    addCharacter();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addCharacter() {
        MangaCharacter mangaCharacter = new MangaCharacter(
                image.getText().toString(),
                name.getText().toString(),
                title.getText().toString(),
                description.getText().toString()
        );

        System.out.println(mangaCharacter);

        myDatabaseHelper.save(mangaCharacter);

        image.setText("");
        name.setText("");
        title.setText("");
        description.setText("");
    }

    private boolean isNotEmpty(EditText editText) {
        return editText.getText().length() > 0;
    }
}