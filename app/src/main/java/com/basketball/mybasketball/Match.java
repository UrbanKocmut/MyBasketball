package com.basketball.mybasketball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.LinkedList;
import java.util.List;

public class Match extends AppCompatActivity {

    Spinner top;
    Spinner bot;
    EditText scTop;
    EditText scBot;
    Button add;
    List<Team> teams;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        top = (Spinner) findViewById(R.id.spinnerTop);
        bot = (Spinner) findViewById(R.id.spinnerBot);
        scTop = (EditText) findViewById(R.id.scoreTop);
        scBot = (EditText) findViewById(R.id.scoreBot);
        add = (Button) findViewById(R.id.btnDodajTekmo);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addScores();
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Team> data = App.get().getDB().teamDao().getAll();
                addTeams(data);

            }
        }).start();


    }

    private void addScores() {
        final int posT = top.getSelectedItemPosition();
        final int posB = bot.getSelectedItemPosition();
        teams.get(posT).setScore(Integer.valueOf(scTop.getText().toString()) + teams.get(posT).getScore());
        teams.get(posB).setScore(Integer.valueOf(scBot.getText().toString()) + teams.get(posB).getScore());

        new Thread(new Runnable() {
            @Override
            public void run() {
                App.get().getDB().teamDao().updateTeam(teams.get(posT));
                App.get().getDB().teamDao().updateTeam(teams.get(posB));
            }
        }).start();

        this.finish();
    }

    private void addTeams(List<Team> teams) {
        this.teams = teams;
        List<String> s = new LinkedList<>();
        for (Team t :
                teams) {
            s.add(t.getName());
        }
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,s);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        top.setAdapter(adapter);
        bot.setAdapter(adapter);
    }
}
