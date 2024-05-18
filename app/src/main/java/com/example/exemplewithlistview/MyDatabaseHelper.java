package com.example.exemplewithlistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;

    // Database
    private static  final String DB_NAME = "myDb.db";
    private static final int DB_VERSION = 2;

    private static  final String TABLE_NAME = "MANGA_CHARS";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                 COLUMN_IMAGE + " TEXT, " +
                 COLUMN_NAME + " TEXT, " +
                 COLUMN_TITLE + " TEXT, " +
                 COLUMN_DESCRIPTION + " TEXT " +
                ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void save(MangaCharacter mangaCharacter) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_IMAGE, mangaCharacter.getImage());
        values.put(COLUMN_NAME, mangaCharacter.getName());
        values.put(COLUMN_TITLE, mangaCharacter.getTitle());
        values.put(COLUMN_DESCRIPTION, mangaCharacter.getDescription());

        long result = db.insert(TABLE_NAME, null, values);
        String message = result == -1 ? "Une erreur est survenue lors de l'ajout" : "L'ajout s'est effectué avec succès";

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public List<MangaCharacter> findAll() {
        List<MangaCharacter> characters = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        if(db != null) {
            try(Cursor cursor = db.rawQuery(query, null)) {
                while (cursor.moveToNext()) {
                    MangaCharacter mangaCharacter = new MangaCharacter(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                    characters.add(mangaCharacter);
                }
            }
        }

        return characters;
    }
}
