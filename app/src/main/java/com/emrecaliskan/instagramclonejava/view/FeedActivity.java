package com.emrecaliskan.instagramclonejava.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.emrecaliskan.instagramclonejava.R;
import com.emrecaliskan.instagramclonejava.adapter.FeedRecyclerAdapter;
import com.emrecaliskan.instagramclonejava.databinding.ActivityFeedBinding;
import com.emrecaliskan.instagramclonejava.databinding.ActivityUploadBinding;
import com.emrecaliskan.instagramclonejava.model.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class FeedActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth auth;
    ArrayList<Post> postArrayList;
    private ActivityFeedBinding binding;
    FeedRecyclerAdapter feedRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        postArrayList = new ArrayList<>();

        getData();

        binding.recylerView.setLayoutManager(new LinearLayoutManager(this));
        feedRecyclerAdapter = new FeedRecyclerAdapter(postArrayList);
        binding.recylerView.setAdapter(feedRecyclerAdapter);
    }

    private void getData(){
        firebaseFirestore.collection("Posts").orderBy("date", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null){
                    Toast.makeText(FeedActivity.this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
                if (value != null){
                    for (DocumentSnapshot documentSnapshot : value.getDocuments()){
                        Map<String,Object> data = documentSnapshot.getData();

                        String userMail = (String) data.get("usermail");
                        String comment = (String) data.get("comment");
                        String downloadurl = (String) data.get("downloadurl");

                        Post post = new Post(userMail,comment,downloadurl);
                        postArrayList.add(post);
                    }

                    feedRecyclerAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.addPost){
            //Upload Activity
            Intent intentToUpload = new Intent(FeedActivity.this, UploadActivity.class);
            startActivity(intentToUpload);

        }else if (item.getItemId() == R.id.signOut){
            //Sign Out
            auth.signOut();

            Intent intentToMain = new Intent(FeedActivity.this, MainActivity.class);
            startActivity(intentToMain);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}