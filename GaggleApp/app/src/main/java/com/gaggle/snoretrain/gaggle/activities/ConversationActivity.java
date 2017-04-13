package com.gaggle.snoretrain.gaggle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.fragments.ConversationFragment;
import com.gaggle.snoretrain.gaggle.listener.IMessageSendCallbackListener;
import com.gaggle.snoretrain.gaggle.models.MessageModel;
import com.gaggle.snoretrain.gaggle.models.MessagesModel;
import com.gaggle.snoretrain.gaggle.services.SendMessageTask;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Snore Train on 4/8/2017.
 */

public class ConversationActivity extends AppCompatActivity {
    ConversationFragment conversationFragment;
    @BindView(R.id.send_message_button)
    ImageButton sendMessageButton;
    @BindView(R.id.message_text)
    EditText messageText;
    private int receiverId;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation_layout);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        final MessagesModel messagesModel = (MessagesModel)intent.getSerializableExtra("messages");
        receiverId = messagesModel.getUserId();
        conversationFragment = new ConversationFragment();
        conversationFragment.setMessages(messagesModel);
        setTitle(messagesModel.getFname() + " " + messagesModel.getLname());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_messages, conversationFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMessageTask sendMessageTask = new SendMessageTask(new IMessageSendCallbackListener() {
                    @Override
                    public void onSendCallback(ArrayList<MessageModel> messages) {
                        conversationFragment.updateMessages(messages);
                    }
                }, messageText.getText().toString(), receiverId);
                sendMessageTask.execute();
                messageText.setText("");
            }
        });
    }
    @Override
    public void onBackPressed() {
        this.finish();
    }
}
