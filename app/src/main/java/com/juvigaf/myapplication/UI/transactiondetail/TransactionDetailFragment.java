package com.juvigaf.myapplication.UI.transactiondetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.button.MaterialButton;
import com.juvigaf.myapplication.R;
import com.juvigaf.myapplication.UI.main.MainFragment;

public class TransactionDetailFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaction_detail, container, false);

        MaterialButton purchaseBtn = view.findViewById(R.id.purchase_btn);
        purchaseBtn.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Successfully purchased", Toast.LENGTH_SHORT).show();

            FragmentManager manager = this.getActivity().getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainContainer, new MainFragment());
        });

        return view;
    }

}