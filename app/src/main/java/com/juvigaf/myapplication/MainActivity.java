package com.juvigaf.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

import com.juvigaf.myapplication.UI.search.SearchFragment;
import com.juvigaf.myapplication.UI.main.MainFragment;
import com.juvigaf.myapplication.UI.profile.ProfileFragment;
import com.juvigaf.myapplication.UI.transaction.TransactionFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.mainContainer, new MainFragment()).commit();
    }

    public void toHome(View view) {
        fragmentManager.beginTransaction().replace(R.id.mainContainer, new MainFragment()).commit();
    }

    public void toSearch(View view) {
        fragmentManager.beginTransaction().replace(R.id.mainContainer, new SearchFragment()).commit();
    }

    public void toTransaction(View view) {
        fragmentManager.beginTransaction().replace(R.id.mainContainer, new TransactionFragment()).commit();
    }

    public void toProfile(View view) {
        fragmentManager.beginTransaction().replace(R.id.mainContainer, new ProfileFragment()).commit();
    }

    public void toUserCart(View view) {
        fragmentManager.beginTransaction().replace(R.id.mainContainer, new UserCartFragment()).commit();
    }

}