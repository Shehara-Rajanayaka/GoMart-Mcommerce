package com.slynec.gomart;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Arrays;
import java.util.List;

public class home extends AppCompatActivity {
    private ViewPager2 carouselViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        carouselViewPager = findViewById(R.id.carousel_viewpager);

        // List of images for carousel (replace with your drawable images)
        List<Integer> imageList = Arrays.asList(
                R.drawable.carousel1,  // Replace with your actual image resources
                R.drawable.carousel2,
                R.drawable.carousel3
        );

        // Set up the adapter
        CarouselAdapter carouselAdapter = new CarouselAdapter(this, imageList);
        carouselViewPager.setAdapter(carouselAdapter);

        // Start auto-scrolling the carousel
        startAutoScroll(imageList.size());
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
                handler.postDelayed(this, 3000);  // Delay of 3 seconds
            }
        };
        handler.postDelayed(runnable, 3000);  // Start after 3 seconds
    }

    // Carousel Adapter
    public static class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder> {
        private final Context context;
        private final List<Integer> images;

        public CarouselAdapter(home context, List<Integer> images) {
            this.context = context;
            this.images = images;
        }

        @Override
        public CarouselViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return new CarouselViewHolder(imageView);
        }

        @Override
        public void onBindViewHolder(CarouselViewHolder holder, int position) {
            holder.imageView.setImageResource(images.get(position));
        }

        @Override
        public int getItemCount() {
            return images.size();
        }

        public static class CarouselViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public CarouselViewHolder(ImageView imageView) {
                super(imageView);
                this.imageView = imageView;
            }
        }
    }
}