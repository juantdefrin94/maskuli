package com.juvigaf.myapplication;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.juvigaf.myapplication.models.KuliMember;
import com.juvigaf.myapplication.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SharedData {

    public static User CURRENT_USER;
    public static DatabaseReference DATABASE_REFERENCE = FirebaseDatabase.getInstance().getReferenceFromUrl("https://maskuliapp-default-rtdb.firebaseio.com/");
    public static List<KuliMember>teams;
    public static int ORDER_COUNT;
    public static List<User> ALL_KULI = new ArrayList<>();
    public static List<User> CART = new ArrayList<>();
    public static int dayStart, monthStart, yearStart, dayEnd, monthEnd, yearEnd;

}
