package com.example.moham.pullgithubripos.fragments;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

import com.example.moham.pullgithubripos.GithubService;
import com.example.moham.pullgithubripos.POJOs.Repo;
import com.example.moham.pullgithubripos.R;
import com.example.moham.pullgithubripos.adapter.RecyclerAdapter;
import com.example.moham.pullgithubripos.database.Controller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PullFragment extends Fragment {
    String TAG = "MainActivity";
    private RecyclerView recyclerView;
    boolean userScrolled = true;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerAdapter adapter;
    private List<Repo> data;
    Controller controller;
    private static int PAGE_NUM = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pull, container, false);
        controller = new Controller(getActivity());
        initViews(view);
        recyclerViewScrollAction();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchGitHub(PAGE_NUM);
            }
        });
        List<Repo> allRepos = controller.getAllRepos();
        if (allRepos.isEmpty())
        {
            fetchGitHub(PAGE_NUM);
        } else
        {
            adapter = new RecyclerAdapter(allRepos, getActivity());
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

    private void initViews(View v) {
        recyclerView = (RecyclerView) v.findViewById(R.id.card_recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swiperefresh);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    private void recyclerViewScrollAction() {
            final int[] pastVisiblesItems = new int[1];
            final int[] visibleItemCount = new int[1];
            int totalItemCount;
            recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                        userScrolled = true;

                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    LinearLayoutManager linearLayoutManager = null;
                    if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                        linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    }
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    visibleItemCount[0] = linearLayoutManager.getChildCount();
                    pastVisiblesItems[0] = linearLayoutManager
                            .findFirstVisibleItemPosition();

                    // Now check if userScrolled is true and also check if
                    // the item is end then update recycler view and set
                    // userScrolled to false
                    if (userScrolled && (visibleItemCount[0] + pastVisiblesItems[0]) == totalItemCount) {
                        userScrolled = false;

                        fetchGitHub(++PAGE_NUM);
                    }

                }

            });
    }


    private void fetchGitHub(int num) {
        Gson gson = new GsonBuilder()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GithubService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        GithubService githubClient = retrofit.create(GithubService.class);

        // Fetch a list of the Github repositories.
        Call<List<Repo>> call = githubClient.reposForUser("square", num,10);

        // Execute the call asynchronously.
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call,
                                   Response<List<Repo>> response) {
                // The network call was a success and we got a response
                controller.deleterepos();
                data = response.body();
                insertAllRepos(data);
                adapter = new RecyclerAdapter(data, getActivity());
                recyclerView.setAdapter(adapter);
                swipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(android.R.id.content, FailureFragment.newInstance(t.getLocalizedMessage()))
                        .commit();
            }

        });
    }

    private void insertAllRepos(List<Repo> data) {
        for (Repo repo : data) {
            controller.addRepo(repo);
        }
    }

}
