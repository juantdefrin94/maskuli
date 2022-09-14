package com.juvigaf.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.juvigaf.myapplication.UI.transactiondetail.TransactionDetailFragment;
import com.juvigaf.myapplication.adapter.CartAdapter;

import java.util.Calendar;

public class UserCartFragment extends Fragment {

    private RecyclerView kuliCardView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private DatePickerDialog datePickerDialog;
    private Button bDateStart, bDateEnd, order;
    private int click = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_cart, container, false);
        this.init(view);

        initDatePicker();
        bDateStart = view.findViewById(R.id.bDateStart);
        bDateEnd = view.findViewById(R.id.bDateEnd);
        order = view.findViewById(R.id.order_kuli_btn);

        bDateStart.setOnClickListener(openDatePicker->{
            click = 1;
            datePickerDialog.show();
        });

        bDateEnd.setOnClickListener(openDatePicker->{
            click = 2;
            datePickerDialog.show();
        });

        order.setOnClickListener(toDetail->{
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.mainContainer, new TransactionDetailFragment()).commit();
        });

        bDateStart.setText(getTodayDate());
        bDateEnd.setText(getTodayDate());

        CartAdapter cartAdapter = new CartAdapter(this.getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        kuliCardView.setAdapter(cartAdapter);
        kuliCardView.setLayoutManager(linearLayoutManager);

        return view;
    }

    private void init(View view) {
        kuliCardView = view.findViewById(R.id.kuli_card);
    }

    private String getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month = month + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        String[] arr = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Ags", "Sept", "Okt", "Nov", "Dec"};
        return arr[month];
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = makeDateString(day, month, year);
                if(click == 1){
                    SharedData.dayStart = day;
                    SharedData.monthStart = month;
                    SharedData.yearStart = year;
                    bDateStart.setText(date);
                }else{
                    SharedData.dayEnd = day;
                    SharedData.monthEnd = month;
                    SharedData.yearEnd = year;
                    bDateEnd.setText(date);
                }
            }
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(getContext(), style, dateSetListener, year, month, day);
    }

}