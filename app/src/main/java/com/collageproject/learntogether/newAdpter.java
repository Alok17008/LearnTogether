package com.collageproject.learntogether;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class newAdpter extends RecyclerView.Adapter<ChatViewHolder> {
    private ArrayList<String> messagesList;

    public newAdpter(ArrayList<String> messagesList) {
        this.messagesList = messagesList;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_message, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        String message = messagesList.get(position);
        holder.bind(message);
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }
}
