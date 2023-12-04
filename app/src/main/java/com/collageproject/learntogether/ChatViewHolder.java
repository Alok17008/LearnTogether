package com.collageproject.learntogether;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatViewHolder extends RecyclerView.ViewHolder {
    TextView messageTextView;

    public ChatViewHolder(@NonNull View itemView) {
        super(itemView);
        messageTextView = itemView.findViewById(R.id.text_view_message);
    }

    public void bind(String message) {
        messageTextView.setText(message);
    }
}
