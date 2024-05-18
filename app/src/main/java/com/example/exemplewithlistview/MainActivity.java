package com.example.exemplewithlistview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MangaCharacter[] characters = new MangaCharacter[] {
            new MangaCharacter("luffy", "Monkey D. Luffy", "Capitaine des Mugiwras", "Fondateur et capitaine de l'équipage du chapeau de paille, premier membre à faire de l'équipage. Il est également le fondateur involontaire de la Grande flotte du Chapeau de paille. Son ambition est de devenir le roi des pirates."),
            new MangaCharacter("zoro", "Roronoa Zoro", "Vice capitaine des Mugiwras", "Vice capitaine de l'équipage du chapeau de paille, il est le deuxième à avoir rejoint l'équipage. Il a pour ambition de devenir le meilleur sabreur du monde."),
            new MangaCharacter("nami", "Nami", "Navigatrice des Mugiwras", "Nami alias \"La chatte voleuse\", est la navigatrice de l'équipage du chapeau de paille et la troisième membre à avoir rejoint l'équipage. Son rêve est de déssiner la carte complète du monde."),
            new MangaCharacter("usopp", "Usopp", "Tireur d'élite des Mugiwras", "Tireur d'élite de l'équipage du chapeau de paille, il a également occupé le poste de charpentier. Son rêve est de devenir un valeureux guerrier des mers et suivre les traces de son père."),
            new MangaCharacter("sanji", "Vinsmoke Sanji", "Cuisinier des Mugiwras", "Surnommé \"Sanji la Jambe Noire\", il est le cuisinier de l'équipage du chapeau de paille, il a appris l'art culinaire sur le Baratie, un restaurant flottant appartenant à un ancien pirate. Il a pour rêve de trouver la légendaire All Blue, un mer regroupant les poissons des quatres coins du globe."),
            new MangaCharacter("chopper", "Tony Tony Chopper", "Medecin des Mugiwras", "Il est le medecin de l'équipage du chapeau de paille, c'est un renne qui a mangé le fruit Hito Hito no Mi qui lui permet de se transformer en humain."),
            new MangaCharacter("robin", "Nico Robin", "Archéologue des Mugiwras", "Aka \"l'Enfant du démon\", elle l'archélogue de l'équipage du chapeau de paille. Elle est recherchée depuis ses huit ans date à laquelle elle fut reconnue comme la dernière des démons d'Ohara. Elle est la recherche du Rio Ponéglyphe afinde décrouvrir la \"Véritable histoire\" du siècle oublié, elle est la dernière personne sur terre à savoir lire les ponéglyphes."),
            new MangaCharacter("franky", "Franky", "Charpentier des Mugiwras", "Franky est le charpentier de l'équipage du chapeau de paille, c'est un cyborg originaire de Water Seven et le chef de la Franky Family, un groupe de désosseurs de bateau."),
            new MangaCharacter("brook", "Brook", "Musicien des Mugiwras", "Surnommé Brook le fredonneur est le musicien ainsi que le second épéiste (après Zoro) de l''éuipage du Chapeau de paille. Il a les pouvoirs du Fruit de la Résurrection, il s'agit d'un mort-vivant revenu à la vie dans un état de pseudo-immortalité grâce aux pouvoirs de ce fruit, ce fruit n'étant pas la cause de son apparence actuelle."),
            new MangaCharacter("jimbe", "Jinbe", "Timonier des Mugiwras", "Jinbe \"Le Paladin des Mers\" est le timonier de l'équipage du Chapeau de paille. C'est un Homme-Poisson requin-baleine, qui a servi comme deuxième capitaine de l'équipage des pirates du Soleil succédant et un ancien Corsaire, qui a obtenu le poste il y a onze ans."),
    };
    MangaCharacterAdapter mangaCharacterAdapter;
    ListView listView;

    Button addButton;

    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabaseHelper = new MyDatabaseHelper(this);
        //initDatabase();

        listView = findViewById(R.id.characters_list);

        addButton = findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddCharacterActivity.class);
                startActivity(intent);
            }
        });

        initView();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initView();
    }

    private void initDatabase() {
        for(MangaCharacter character: characters) {
            myDatabaseHelper.save(character);
        }
    }

    private MangaCharacter[] getCharacters() {
        return myDatabaseHelper.findAll().toArray(new MangaCharacter[0]);
    }

    private void initView() {

        characters = getCharacters();
        mangaCharacterAdapter = new MangaCharacterAdapter(this, R.layout.row, characters);
        //mangaCharacterAdapter = new MangaCharacterAdapter(this, R.layout.row, getCharacters());

        listView.setAdapter(mangaCharacterAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), MangaCharacterActivity.class);
                intent.putExtra("data", characters[position]);
                startActivity(intent);
            }
        });
    }
}