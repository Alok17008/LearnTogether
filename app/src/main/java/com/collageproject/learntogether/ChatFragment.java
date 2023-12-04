package com.collageproject.learntogether;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class ChatFragment extends Fragment {

    private RecyclerView chatRecyclerView;
    private EditText messageEditText;
    private DatabaseReference messagesRef;
    private ArrayList<String> messagesList;
    private newAdpter chatAdapter;

    private String groupName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        chatRecyclerView = view.findViewById(R.id.recycler_view_chat);
        messageEditText = view.findViewById(R.id.TextMessageID);
        Button sendButton = view.findViewById(R.id.button_send_message);

        messagesList = new ArrayList<>();
        chatAdapter = new newAdpter(messagesList);

        chatRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        chatRecyclerView.setAdapter(chatAdapter);

        Bundle bundle = getArguments();
        if (bundle != null) {
            groupName = bundle.getString("groupName");
        }

        messagesRef = FirebaseDatabase.getInstance().getReference().child("Groups").child(groupName).child("Messages");

        fetchMessages();

        sendButton.setOnClickListener(v -> sendMessage());

        return view;
    }

    private void fetchMessages() {
        messagesRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                String message = dataSnapshot.getValue(String.class);
                messagesList.add(message);
                chatAdapter.notifyDataSetChanged();
                chatRecyclerView.smoothScrollToPosition(messagesList.size() - 1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                // Handle message changed event
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                // Handle message removed event
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                // Handle message moved event (even if it's empty)
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });
    }


    private void sendMessage() {
        String message = messageEditText.getText().toString().trim();

        if (!message.isEmpty()) {
            messagesRef.push().setValue(message);
            messageEditText.setText("");
        }
    }
}
