package com.collageproject.learntogether;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.GroupViewHolder> {

    private ArrayList<String> groupNames;
    private OnItemClickListener clickListener;

    public ChatAdapter(ArrayList<String> groupNames) {
        this.groupNames = groupNames;
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        String groupName = groupNames.get(position);
        holder.bind(groupName);

        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return groupNames.size();
    }

    public static class GroupViewHolder extends RecyclerView.ViewHolder {
        TextView groupNameTextView;

        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            groupNameTextView = itemView.findViewById(android.R.id.text1);
        }

        public void bind(String groupName) {
            groupNameTextView.setText(groupName);
        }
    }

    // Interface for item click handling
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
