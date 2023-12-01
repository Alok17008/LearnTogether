package com.collageproject.learntogether;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RedditAdapter extends RecyclerView.Adapter<RedditAdapter.RedditViewHolder> {

    private List<RedditPostData> dataList;
    private OnItemClickListener onItemClickListener;

    public RedditAdapter(List<RedditPostData> dataList) {
        this.dataList = dataList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public RedditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reddit_post_layout, parent, false);
        return new RedditViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RedditViewHolder holder, int position) {
        RedditPostData data = dataList.get(position);
        holder.bind(data);

        holder.itemView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class RedditViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView urlTextView;

        RedditViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            urlTextView = itemView.findViewById(R.id.url_text_view);
        }

        void bind(RedditPostData data) {
            RedditPost post = data.getPost();
            if (post != null) {
                titleTextView.setText(post.getTitle());
                urlTextView.setText(post.getUrl());
            }
        }
    }
}
