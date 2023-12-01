package com.collageproject.learntogether;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TechnewsActivity extends AppCompatActivity implements RedditAdapter.OnItemClickListener {

    private static final String TAG = "TechnewsActivity";
    private RecyclerView recyclerView;
    private RedditAdapter adapter;
    private List<RedditPostData> redditPostDataList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technews);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        redditPostDataList = new ArrayList<>();
        adapter = new RedditAdapter(redditPostDataList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);

        fetchTechNews();
    }

    private void fetchTechNews() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.reddit.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RedditApiService apiService = retrofit.create(RedditApiService.class);

        Call<RedditResponse> call = apiService.getTechNews("RiRmvBUYCwgFIxYwvchTjw");
        call.enqueue(new Callback<RedditResponse>() {
            @Override
            public void onResponse(Call<RedditResponse> call, Response<RedditResponse> response) {
                if (response.isSuccessful()) {
                    RedditResponse redditResponse = response.body();
                    if (redditResponse != null && redditResponse.getData() != null
                            && redditResponse.getData().getChildren() != null) {
                        redditPostDataList.addAll(redditResponse.getData().getChildren());
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Log.e(TAG, "Failed to fetch data: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<RedditResponse> call, Throwable t) {
                Log.e(TAG, "Failed to fetch data: " + t.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        RedditPostData selectedPost = redditPostDataList.get(position);
        openNewsDetailsFragment(selectedPost);
    }

    private void openNewsDetailsFragment(RedditPostData selectedPost) {
        NewsDetailsFragment fragment = NewsDetailsFragment.newInstance(selectedPost);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
