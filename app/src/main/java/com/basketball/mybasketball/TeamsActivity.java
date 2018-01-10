package com.basketball.mybasketball;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TeamsActivity extends AppCompatActivity implements AddTeamDialog.AddDialogListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.Addfab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddDialog();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.rv);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);






        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Team> teams = App.get().getDB().teamDao().getAll();
                populateProducts(teams);

            }
        }).start();
        recyclerView.setAdapter(mAdapter);


    }

    public void showAddDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new AddTeamDialog();
        dialog.show(getSupportFragmentManager(), "AddTeamDialog");
    }

    void changeActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    @Override
    public void onOk(DialogFragment dialog, String img) {
        System.out.println("Ok");
        EditText et = (EditText) dialog.getDialog().findViewById(R.id.tName);
        String name = et.getText().toString();
        final Team team;
        if(img != null){
            team = new Team(name,img);
        }else {
            team = new Team(name);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                App.get().getDB().teamDao().insert(team);

                populateProducts(App.get().getDB().teamDao().getAll());
            }
        }).start();
    }

    @Override
    public void onCancel(DialogFragment dialog) {
        System.out.println("Cancel");
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Team> teams = App.get().getDB().teamDao().getAll();
                populateProducts(teams);
            }
        }).start();
    }

    private void populateProducts(List<Team> teams) {
        final List<Team> t = teams.stream().sorted(new Comparator<Team>() {
            @Override
            public int compare(Team team, Team t1) {
                return t1.getScore() - team.getScore();
            }
        }).collect(Collectors.<Team>toList());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(new TeamsAdapter(t));
            }
        });
    }
}
