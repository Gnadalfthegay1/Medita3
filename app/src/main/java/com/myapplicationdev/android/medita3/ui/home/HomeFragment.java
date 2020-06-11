package com.myapplicationdev.android.medita3.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.myapplicationdev.android.medita3.Journals;
import com.myapplicationdev.android.medita3.MainActivity;
import com.myapplicationdev.android.medita3.R;
import com.myapplicationdev.android.medita3.dbJournals;
import com.myapplicationdev.android.medita3.editEntry;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ListView lvEntry;
    ArrayAdapter<String> adapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        lvEntry = root.findViewById(R.id.lvEntry);

        dbJournals db = new dbJournals(getContext());
        ArrayList<String> userList = new ArrayList<String>(db.loadDate());
        adapter =
                new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, userList);

        if (adapter.getCount() > 0) {
            lvEntry.setAdapter(adapter);
        } else {
            Toast.makeText(getContext(), "NO Data Available..", Toast.LENGTH_SHORT).show();
        }
        lvEntry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), editEntry.class);
                dbJournals db = new dbJournals(getContext());
                String date = (String) lvEntry.getItemAtPosition(position);
                Journals j = db.findHandler(date);
                intent.putExtra("date", j.getDate());
                intent.putExtra("entry", j.getJournal());
                startActivityForResult(intent, 9);
            }
        });

        return root;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            ArrayList<String> userList = new ArrayList<String>();
            userList.clear();
            dbJournals db = new dbJournals(getActivity());
            userList = db.loadDate();
            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, userList);
            lvEntry.setAdapter(adapter);
        }
    }
}
