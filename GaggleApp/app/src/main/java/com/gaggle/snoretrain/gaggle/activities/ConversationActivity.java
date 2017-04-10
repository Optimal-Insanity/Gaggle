package com.gaggle.snoretrain.gaggle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.fragments.ConversationFragment;
import com.gaggle.snoretrain.gaggle.models.MessagesModel;

/**
 * Created by Snore Train on 4/8/2017.
 */

public class ConversationActivity extends AppCompatActivity {
    ConversationFragment conversationFragment;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation_layout);

        Intent intent = getIntent();
        MessagesModel messagesModel = (MessagesModel)intent.getSerializableExtra("messages");
        conversationFragment = new ConversationFragment();
        conversationFragment.setMessages(messagesModel);
        setTitle(messagesModel.getFname() + " " + messagesModel.getLname());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_messages, conversationFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    public void onBackPressed() {
        this.finish();
    }
}
