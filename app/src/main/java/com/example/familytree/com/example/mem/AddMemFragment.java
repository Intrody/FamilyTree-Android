package com.example.familytree.com.example.mem;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.familytree.R;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddMemFragment extends Fragment {

    private Button buttonMemSubmit;
    private EditText editTextMemName,editTextPat,editTextMemSex,editTextMemAddress,editTextGenId,editTextMemBorn,editTextDai,editTextParents,editTextChidren;
    private MemViewModel memViewModel;

    public AddMemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_mem, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = requireActivity();
        memViewModel = new ViewModelProvider(activity).get(MemViewModel.class);
        buttonMemSubmit = activity.findViewById(R.id.buttonMemSubmit);
        editTextDai = activity.findViewById(R.id.editTextDai);
        editTextGenId = activity.findViewById(R.id.editTextGenId);
        editTextMemAddress = activity.findViewById(R.id.editTextMemAddress);
        editTextMemBorn = activity.findViewById(R.id.editTextMemBorn);
        editTextMemName =activity.findViewById(R.id.editTextMemName);
        editTextMemSex = activity.findViewById(R.id.editTextMemSex);
        editTextPat = activity.findViewById(R.id.editTextPat);
        editTextParents = activity.findViewById(R.id.editTextParents);
        editTextChidren = activity.findViewById(R.id.editTextChildren);
        buttonMemSubmit.setEnabled(false);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String name = editTextMemName.getText().toString().trim();
                String genid = editTextGenId.getText().toString().trim();
                String dai = editTextDai.getText().toString().trim();
                buttonMemSubmit.setEnabled(!name.isEmpty()&&!genid.isEmpty()&&!dai.isEmpty());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        editTextMemName.addTextChangedListener(textWatcher);
        editTextGenId.addTextChangedListener(textWatcher);
        editTextDai.addTextChangedListener(textWatcher);
        buttonMemSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextMemName.getText().toString().trim();
                String genid = editTextGenId.getText().toString().trim();
                String sex = editTextMemSex.getText().toString().trim();
                String born = editTextMemBorn.getText().toString().trim();
                String add = editTextMemAddress.getText().toString().trim();
                String pat = editTextPat.getText().toString().trim();
                String parents = editTextParents.getText().toString().trim();
                String children = editTextChidren.getText().toString().trim();
                String dai = editTextDai.getText().toString().trim();
                Mem mem = new Mem(name,genid,sex,born,add,pat,parents,children,dai);
                memViewModel.insertMems(mem);
                NavController navController = Navigation.findNavController(view);
                navController.navigateUp();
            }
        });



    }
}
