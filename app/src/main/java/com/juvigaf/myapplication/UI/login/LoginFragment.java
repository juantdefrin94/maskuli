package com.juvigaf.myapplication.UI.login;

import static com.juvigaf.myapplication.SharedData.CURRENT_USER;
import static com.juvigaf.myapplication.SharedData.DATABASE_REFERENCE;
import static com.juvigaf.myapplication.SharedData.ORDER_COUNT;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.juvigaf.myapplication.MainActivity;
import com.juvigaf.myapplication.R;
import com.juvigaf.myapplication.models.Order;
import com.juvigaf.myapplication.models.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    EditText usernameText, passwordText;
    Button loginButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        init(view);

        loginButton.setOnClickListener(login->{
            String username = usernameText.getText().toString().trim();
            String password = passwordText.getText().toString().trim();

            //cek username and password (async) so place logic in function onDataChange
            DATABASE_REFERENCE.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //has child usernameText, ex = juantdefrin
                    if(snapshot.hasChild(username)){
                        String passwordFromDb = snapshot.child(username).child("password").getValue(String.class);
                        if(password.equals(passwordFromDb)){
                            //Successfully Log In, get User data before login
                            DATABASE_REFERENCE.child("users").child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String email = snapshot.child("email").getValue(String.class);
                                    String name = snapshot.child("name").getValue(String.class);
                                    String phone = snapshot.child("phone").getValue(String.class);
                                    Integer role = snapshot.child("role").getValue(Integer.class);

                                    User newUser = new User(username, name, email, password, phone, role);

                                    //get orders data
                                    for(int i = 0; i < ORDER_COUNT; i++){
                                        DATABASE_REFERENCE.child("orders").child(String.valueOf(i)).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                String userId = snapshot.child("user_id").getValue(String.class);
                                                if(userId.equals(username)){
                                                    int status = snapshot.child("status").getValue(Integer.class);
                                                    Integer money = snapshot.child("money").getValue(Integer.class);
                                                    String orderDate = snapshot.child("orderDate").getValue(String.class);
                                                    int teamId = snapshot.child("team_id").getValue(Integer.class);
                                                    Order newOrder = new Order(money, orderDate, teamId, userId);
                                                    newOrder.setStatus(status);
                                                    newUser.getOrders().add(newOrder);
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                    }
                                    CURRENT_USER = newUser;
                                    //to Main Activity
                                    Intent toMainActivity = new Intent(getContext(), MainActivity.class);
                                    getContext().startActivity(toMainActivity);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });

        return view;
    }

    public void init(View view){
        usernameText = view.findViewById(R.id.usernameText);
        passwordText = view.findViewById(R.id.passwordText);
        loginButton = view.findViewById(R.id.loginButton);
    }
}