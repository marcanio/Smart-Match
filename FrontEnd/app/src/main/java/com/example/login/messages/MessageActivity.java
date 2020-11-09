package com.example.login.messages;
//import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageActivity extends AppCompatActivity {
    GlobalMessagesAdapter adapter;

    /**
     * On Create function for Message Activity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        RecyclerView recyclerView = findViewById(R.id.rvId2);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final List<Message> msgs = new ArrayList<>();//TODO   -get messages from backend
        String idOf = getIntent().getExtras().getString("id");
        /*Stub fucntions*/
        switch (idOf) {
            case "222":
                msgs.add(new Message("444", "222", "Kim Reynolds", "Joni Ernst", new Date(), "Wanna Hang?"));
                msgs.add(new Message("222", "444", "Joni Ernst", "Kim Reynolds", new Date(), "Trust me, I don't bite"));
                msgs.add(new Message("444", "222", "Kim Reynolds", "Joni Ernst", new Date(), " 8:00pm?"));
                break;
            case "333":
                msgs.add(new Message("333", "444", "Theresa Greenfield", "Kim Reynolds", new Date(), "I like the way you look"));
                msgs.add(new Message("444", "333", "Kim Reynolds", "Theresa Greenfield", new Date(), "So cute! :)"));
                msgs.add(new Message("333", "444", "Theresa Greenfield", "Kim Reynolds", new Date(), "I would like to take you out. Lol!"));
                break;
            case "555":
                msgs.add(new Message("555", "444", "Wendy Wintersteen", "Kim Reynolds", new Date(), "Hey! handsome <3"));
                msgs.add(new Message("444", "555", "Kim Reynolds", "Wendy Wintersteen", new Date(), "I am in love with you:)"));
                msgs.add(new Message("555", "444", "Wendy Wintersteen", "Kim Reynolds", new Date(), "Seriously falling in love with you"));
                break;

        }

        /**/
        adapter = new GlobalMessagesAdapter(this, msgs, "Kim Reynolds");
        adapter.setClickListener(new GlobalMessagesAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }
        });
        recyclerView.setAdapter(adapter);
    }
}