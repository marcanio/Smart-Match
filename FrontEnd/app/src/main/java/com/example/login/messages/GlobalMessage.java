package com.example.login.messages;
//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Abstract method for messages that extends to appCompat activity.
 */
public class GlobalMessage extends AppCompatActivity {
    GlobalMessagesAdapter adapter;
    String idThis = "444";

    /**
     * On create method for message and chat conversation.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_message);
        RecyclerView recyclerView = findViewById(R.id.rvId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final List<Message> msgs = new ArrayList<>();//TODO   -get messages from backend
        msgs.add(new Message("555", "444", "Wendy Wintersteen", "Kim Reynolds", new Date(), "1 Unread message"));
        msgs.add(new Message("444", "222", "Kim Reynolds", "Joni Ernst", new Date(), "Joni is waiting for your reply"));
        msgs.add(new Message("333", "444", "Theresa Greenfield", "Kim Reynolds", new Date(), "No new Message"));
        adapter = new GlobalMessagesAdapter(this, msgs, "Kim Reynolds");
        adapter.setClickListener(new GlobalMessagesAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(GlobalMessage.this, MessageActivity.class);
                Message msg = msgs.get(position);
                System.out.println(msg);
                String kk = msg.getIdSender();
                if (kk.equals(idThis)) {
                    kk = msg.getIdReceiver();
                }
                intent.putExtra("id", kk);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}