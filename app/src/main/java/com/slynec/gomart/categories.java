package com.slynec.gomart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.slynec.gomart.adapters.CategoryAdapter;
import com.slynec.gomart.models.Category;

import java.util.ArrayList;
import java.util.List;

public class categories extends AppCompatActivity {
    private RecyclerView Categoryrecycler;
    private CategoryAdapter CategoryAdapter;
    private List<Category> categoryList;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categories);

        Categoryrecycler = findViewById(R.id.CategoryRecycler);
        Categoryrecycler.setLayoutManager(new GridLayoutManager(this, 3));

        categoryList = new ArrayList<>();
        CategoryAdapter = new CategoryAdapter(this, categoryList); // Fixed here
        Categoryrecycler.setAdapter(CategoryAdapter);

        db = FirebaseFirestore.getInstance();

        ImageView navigateToCart = findViewById(R.id.imageView25);
        navigateToCart.setOnClickListener(view -> {
            Intent intent = new Intent(categories.this, cart.class);
            startActivity(intent);
        });

        loadCategories(); // Fixed function name typo
    }

    private void loadCategories() { // Fixed function name
        db.collection("Categories").get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        categoryList.clear();
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot != null) {
                            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                                Category category = document.toObject(Category.class);
                                categoryList.add(category);
                            }
                            CategoryAdapter.notifyDataSetChanged();
                        }
                    } else {
                        Log.e("Firebase", "Error getting documents: ", task.getException());
                    }
                });
    }
}
