package com.juvigaf.myapplication.adapter;

import static com.juvigaf.myapplication.SharedData.ALL_KULI;
import static com.juvigaf.myapplication.SharedData.CART;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.juvigaf.myapplication.R;
import com.juvigaf.myapplication.models.User;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private final Context context;

    public CartAdapter(Context context) {
        this.context = context;
        if(CART.size() == 0) return;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.template_kuli, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User current = CART.get(position);

        if (current.getProfile() == 0) {
            //kalo belum upload profile
            Glide.with(context)
                    .load("https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/User-avatar.svg/1024px-User-avatar.svg.png")
                    .into(holder.kuliProfile);
        }

        holder.kuliName.setText(current.getName());
        holder.kuliRole.setText("Peran : kuli");
        holder.kuliRating.setText("Rating : " + current.getRating());

        holder.containerKuli.setOnClickListener(addToCart -> {
            CART.remove(position);
            this.notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return CART.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView kuliProfile;
        private final TextView kuliName, kuliRole, kuliRating;
        private final RelativeLayout containerKuli;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            kuliProfile = itemView.findViewById(R.id.kuliProfile);
            kuliName = itemView.findViewById(R.id.kuliName);
            kuliRole = itemView.findViewById(R.id.kuliRole);
            kuliRating = itemView.findViewById(R.id.kuliRating);
            containerKuli = itemView.findViewById(R.id.containerKuli);
        }

    }

}