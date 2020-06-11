package com.myapplicationdev.android.medita3.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.myapplicationdev.android.medita3.Journals;
import com.myapplicationdev.android.medita3.R;
import com.myapplicationdev.android.medita3.dbJournals;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    Button btAdd;
    EditText etDate, etEntry;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        btAdd = root.findViewById(R.id.editButton);
        etDate = root.findViewById(R.id.etDate);
        etEntry = root.findViewById(R.id.entry);
        getActivity().setTitle("Add Entry");
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbJournals db = new dbJournals(getContext());
                String date = etDate.getText().toString();
                String entry = etEntry.getText().toString();
                Journals j = new Journals();
                int i = db.loadHandler().size();
                if(i == i){
                    j = new Journals(date, entry);
                }
                db.addHandler(j);
                etDate.setText("");
                etEntry.setText("");
            }
        });
        return root;
    }
}
