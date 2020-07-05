package com.example.familytree;


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


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTreeFragment extends Fragment {

    private Button buttonSubmit;
    private EditText editTextTreeName,editTextPeople,editTextMaker;
    private TreeViewModel treeViewModel;

    public AddTreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_tree, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = requireActivity();
        treeViewModel = new ViewModelProvider(activity).get(TreeViewModel.class);
        buttonSubmit = activity.findViewById(R.id.buttonSubmit);
        editTextTreeName = activity.findViewById(R.id.editTextTreeName);
        editTextPeople = activity.findViewById(R.id.editTextPeople);
        editTextMaker = activity.findViewById(R.id.editTextMaker);
        buttonSubmit.setEnabled(false);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String name = editTextTreeName.getText().toString().trim();
                String people = editTextPeople.getText().toString().trim();
                String maker = editTextMaker.getText().toString().trim();
                buttonSubmit.setEnabled(!name.isEmpty()&&!people.isEmpty()&&!maker.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        editTextTreeName.addTextChangedListener(textWatcher);
        editTextPeople.addTextChangedListener(textWatcher);
        editTextMaker.addTextChangedListener(textWatcher);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextTreeName.getText().toString().trim();
                String people = editTextPeople.getText().toString().trim();
                String maker = editTextMaker.getText().toString().trim();
                Tree tree = new Tree(name,people,maker);
                treeViewModel.insertTrees(tree);
                NavController navController = Navigation.findNavController(view);
                navController.navigateUp();
            }
        });
    }
}
