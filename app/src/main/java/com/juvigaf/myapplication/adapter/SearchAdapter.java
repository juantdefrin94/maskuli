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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    Context context;

    public SearchAdapter(Context context) {
        this.context = context;
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
        if (ALL_KULI.get(position).getProfile() == 0) {
            //kalo belum upload profile
            Glide.with(context)
                    .load("https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/User-avatar.svg/1024px-User-avatar.svg.png")
                    .into(holder.kuliProfile);
        }

        holder.kuliName.setText(ALL_KULI.get(position).getName());
        holder.kuliRole.setText("Peran : kuli");
        holder.kuliRating.setText("Rating : " + ALL_KULI.get(position).getRating());
        holder.containerKuli.setOnClickListener(addToCart -> {
            boolean checkUnique = true;
            int idxRemove = 0;
            for (int i = 0; i < CART.size(); i++) {
                if (CART.get(i).getUsername().equals(ALL_KULI.get(position).getUsername())) {
                    checkUnique = false;
                    idxRemove = i;
                    break;
                }
            }
            if (checkUnique) {
                CART.add(ALL_KULI.get(position));
                holder.check.setVisibility(View.VISIBLE);
            } else {
                CART.remove(idxRemove);
                holder.check.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ALL_KULI.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView kuliProfile;
        TextView kuliName, kuliRole, kuliRating;
        RelativeLayout check, containerKuli;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            kuliProfile = itemView.findViewById(R.id.kuliProfile);
            kuliName = itemView.findViewById(R.id.kuliName);
            kuliRole = itemView.findViewById(R.id.kuliRole);
            kuliRating = itemView.findViewById(R.id.kuliRating);
            containerKuli = itemView.findViewById(R.id.containerKuli);
            check = itemView.findViewById(R.id.check);
        }
    }
}
