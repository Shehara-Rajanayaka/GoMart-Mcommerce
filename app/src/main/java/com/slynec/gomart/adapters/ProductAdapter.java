package com.slynec.gomart.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.slynec.gomart.R;
import com.slynec.gomart.models.Product;
import com.slynec.gomart.single_product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;
    private Context context;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.nameTextView.setText(product.getName());
        holder.priceTextView.setText(product.getPrice());
        holder.weightTextView.setText(product.getWeight());
        holder.discountTextView.setText(product.getDiscount());

        // Load image using Glide
        Glide.with(holder.itemView.getContext())
                .load(product.getImageUrl())
                .into(holder.productImageView);

        // Handle item click
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, single_product.class);
            intent.putExtra("product_name", product.getName());
            intent.putExtra("product_price", product.getPrice());
            intent.putExtra("product_weight", product.getWeight());
            intent.putExtra("product_discount", product.getDiscount());
            intent.putExtra("product_image", product.getImageUrl());
            intent.putExtra("product_description", product.getDescription()); // Pass description
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView, priceTextView, weightTextView, discountTextView;
        public ImageView productImageView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.product_name);
            priceTextView = itemView.findViewById(R.id.product_price);
            weightTextView = itemView.findViewById(R.id.product_weight);
            discountTextView = itemView.findViewById(R.id.product_discount);
            productImageView = itemView.findViewById(R.id.product_image);
        }
    }
}
