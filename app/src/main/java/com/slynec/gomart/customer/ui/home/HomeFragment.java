package com.slynec.gomart.customer.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.slynec.gomart.R;
import com.slynec.gomart.adapters.CategoryAdapter;
import com.slynec.gomart.adapters.ProductAdapter;
import com.slynec.gomart.categories;
import com.slynec.gomart.customer.customer_home;
import com.slynec.gomart.databinding.FragmentHomeBinding;
import com.slynec.gomart.models.Category;
import com.slynec.gomart.models.Product;
import com.slynec.gomart.products;
import com.slynec.gomart.single_product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerViewproducts,Categoryrecycler;
    private ProductAdapter productAdapterr;
    private CategoryAdapter CategoryAdapter;

    private List<Product> productList;
    private FirebaseFirestore db;
    private List<Category>categoryList;

    private FragmentHomeBinding binding;
    private ViewPager2 carouselViewPager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        carouselViewPager = binding.carouselViewpager;

        // List of images for carousel
        List<Integer> imageList = Arrays.asList(
                R.drawable.carousel1,
                R.drawable.carousel2,
                R.drawable.carousel3
        );

        // Set up the adapter
        CarouselAdapter carouselAdapter = new CarouselAdapter(requireContext(), imageList);
        carouselViewPager.setAdapter(carouselAdapter);

        // Start auto-scrolling the carousel
        startAutoScroll(imageList.size());

        // Set up navigation buttons
        setupNavigation(root);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseApp.initializeApp(requireContext());
        db = FirebaseFirestore.getInstance();

        Categoryrecycler=view.findViewById(R.id.CategoryRecycler);
        Categoryrecycler.setLayoutManager(new GridLayoutManager(requireContext(), 5));


        recyclerViewproducts = view.findViewById(R.id.recyclerViewProductList);
        recyclerViewproducts.setLayoutManager(new GridLayoutManager(requireContext(), 3));

        categoryList=new ArrayList<>();
        CategoryAdapter=new CategoryAdapter(requireContext(),categoryList);
        Categoryrecycler.setAdapter(CategoryAdapter);

        productList = new ArrayList<>();
        productAdapterr = new ProductAdapter(requireContext(),productList);
        recyclerViewproducts.setAdapter(productAdapterr);


        CategoryLoad();
        loadProduct();
    }

    private void CategoryLoad() {
        db.collection("Categories")
                .limit(5)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    categoryList.clear();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Category category = document.toObject(Category.class);
                        categoryList.add(category);  // Corrected this line
                    }
                    CategoryAdapter.notifyDataSetChanged();
                })
                .addOnFailureListener(Throwable::printStackTrace);
    }


    private void loadProduct() {
        db.collection("products")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    productList.clear();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Product card = document.toObject(Product.class);
                        productList.add(card);
                    }
                    productAdapterr.notifyDataSetChanged();
                })
                .addOnFailureListener(Throwable::printStackTrace);
    }



    private void startAutoScroll(int size) {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int currentItem = 0;

            @Override
            public void run() {
                if (currentItem == size) {
                    currentItem = 0;
                }
                carouselViewPager.setCurrentItem(currentItem++, true);
                handler.postDelayed(this, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);
    }

    private void setupNavigation(View root) {
        TextView textViewProducts = root.findViewById(R.id.textView44);
        textViewProducts.setText("View All");
        textViewProducts.setPaintFlags(textViewProducts.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textViewProducts.setOnClickListener(view -> {
            Intent intent = new Intent(requireActivity(), products.class);
            startActivity(intent);
        });

        TextView textViewCategories = root.findViewById(R.id.textView40);
        textViewCategories.setText("All Categories");
        textViewCategories.setPaintFlags(textViewCategories.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textViewCategories.setOnClickListener(view -> {
            Intent intent = new Intent(requireActivity(), categories.class);
            startActivity(intent);
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Carousel Adapter
    public static class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder> {
        private final Context context;
        private final List<Integer> images;

        public CarouselAdapter(Context context, List<Integer> images) {
            this.context = context;
            this.images = images;
        }

        @NonNull
        @Override
        public CarouselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return new CarouselViewHolder(imageView);
        }

        @Override
        public void onBindViewHolder(@NonNull CarouselViewHolder holder, int position) {
            holder.imageView.setImageResource(images.get(position));
        }

        @Override
        public int getItemCount() {
            return images.size();
        }

        public static class CarouselViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public CarouselViewHolder(@NonNull ImageView imageView) {
                super(imageView);
                this.imageView = imageView;
            }
        }
    }
}
