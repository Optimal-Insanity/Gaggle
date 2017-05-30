package com.gaggle.snoretrain.gaggle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.fragments.ConversationFragment;
import com.gaggle.snoretrain.gaggle.interactor.ApiInteractor;
import com.gaggle.snoretrain.gaggle.interactor.GaggleApplicationView;
import com.gaggle.snoretrain.gaggle.interactor.Interactor;
import com.gaggle.snoretrain.gaggle.models.MessagesModel;
import com.gaggle.snoretrain.gaggle.presenter.ViewPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Snore Train on 4/8/2017.
 */

public class ConversationActivity extends AppCompatActivity implements GaggleApplicationView<MessagesModel>{
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
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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
                sendMessage();
            }
        });
    }
    @Override
    public void onBackPressed() {
        this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void presentGaggleData(MessagesModel data) {
        conversationFragment.updateMessages(data.getMessages());
    }
    public void sendMessage(){
        Interactor interactor = new ApiInteractor.Builder()
                .setAdapterMethod("sendMessage")
                .setMethodParameters(receiverId, messageText.getText().toString())
                .setMethodParameterTypes(int.class, String.class)
                .build();
        ViewPresenter presenter = new ViewPresenter(ConversationActivity.this, interactor);
        presenter.getData();
        messageText.setText("");
    }
}
