package com.example.moham.pullgithubripos.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moham.pullgithubripos.BR;
import com.example.moham.pullgithubripos.POJOs.Repo;
import com.example.moham.pullgithubripos.R;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private static final int OWNER_INDEX = 0;
    private static final int REPO_INDEX = 1;
    private List<Repo> repos;
    private Context context;

    public RecyclerAdapter(List repos, Context c) {
        this.repos = repos;
        this.context = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Set our CardView here
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Repo repo = repos.get(position);
        holder.bind(repo);
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("choose_url")
                        .setItems(R.array.pick_url, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case OWNER_INDEX:
                                        sendToBrowser(repo.getOwner().getUrl());
                                        break;
                                    case REPO_INDEX:
                                        sendToBrowser(repo.getUrl());
                                        break;
                                    default:
                                        break;
                                }
                            }
                        });
                builder.show();
                return true;
            }
        });


    }

    private void sendToBrowser(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding binding;
         CardView cardView;
        ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            cardView= (CardView) binding.getRoot().findViewById(R.id.cardview);
            this.binding = binding;
        }

        void bind(Object obj) {
            binding.setVariable(BR.myRepo, obj);
            binding.executePendingBindings();
        }
    }
}
