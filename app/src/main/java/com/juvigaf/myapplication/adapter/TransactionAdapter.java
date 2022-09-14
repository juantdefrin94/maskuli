package com.juvigaf.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juvigaf.myapplication.R;
import com.juvigaf.myapplication.models.Order;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private final List<Order> orders;
    Context ctx;

    public TransactionAdapter(List<Order> orders, Context ctx) {
        this.orders = orders;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.template_transaction, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position) {
        holder.tvName.setText("Maman"); //TODO: nama mandor
        holder.tvPrice.setText(orders.get(position).getMoney().toString());
        holder.tvDate.setText(orders.get(position).getOrderDate());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvPrice, tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.teamText);
            tvPrice = itemView.findViewById(R.id.priceText);
            tvDate = itemView.findViewById(R.id.dateText);
        }
    }
}