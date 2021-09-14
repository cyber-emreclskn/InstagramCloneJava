package com.emrecaliskan.instagramclonejava.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emrecaliskan.instagramclonejava.databinding.RecylerRowBinding;
import com.emrecaliskan.instagramclonejava.model.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FeedRecyclerAdapter extends RecyclerView.Adapter<FeedRecyclerAdapter.PostHolder> {

    private ArrayList<Post> postArrayList;

    public FeedRecyclerAdapter(ArrayList<Post> postArrayList) {
        this.postArrayList = postArrayList;
    }

    public class PostHolder extends RecyclerView.ViewHolder {
        RecylerRowBinding recylerRowBinding;

        public PostHolder(@NonNull RecylerRowBinding recylerRowBinding) {
            super(recylerRowBinding.getRoot());
            this.recylerRowBinding = recylerRowBinding;

        }
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecylerRowBinding recylerRowBinding = RecylerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PostHolder(recylerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {

        holder.recylerRowBinding.recylerViewViewUserEmailText.setText(postArrayList.get(position).getEmail());
        holder.recylerRowBinding.recylerViewCommentText.setText(postArrayList.get(position).getComment());
        Picasso.get().load(postArrayList.get(position).getDownloadUri()).into(holder.recylerRowBinding.recylerViewImageView);
    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }


}
