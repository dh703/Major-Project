package com.example.dheerajmajor;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView listView;
    DatabaseHelper databaseHelper;
    ArrayList<String> arrayList;
    ArrayList<String> arrayList1;
    BgAdapter arrayAdapter;

    public BlankFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment2 newInstance(String param1, String param2) {
        BlankFragment2 fragment = new BlankFragment2();
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_blank2, container, false);
        listView = v.findViewById(R.id.list_view);
        arrayList=new ArrayList();
        arrayList1=new ArrayList();
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        listView.setHasFixedSize(true);
        databaseHelper = new DatabaseHelper(getContext());
        arrayList=databaseHelper.getAllText();
        arrayList1=databaseHelper.getImage();
        arrayAdapter = new BgAdapter(getContext(),arrayList,arrayList1);
        listView.setAdapter(arrayAdapter);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        arrayList=new ArrayList();
        databaseHelper = new DatabaseHelper(getContext());
        arrayList=databaseHelper.getAllText();
        arrayAdapter = new BgAdapter(getContext(),arrayList,arrayList1);
        listView.setAdapter(arrayAdapter);

    }
}