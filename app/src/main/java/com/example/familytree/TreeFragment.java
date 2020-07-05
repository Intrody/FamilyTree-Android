package com.example.familytree;


import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.familytree.com.example.mem.Mem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TreeFragment extends Fragment {
    private TreeViewModel treeViewModel;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private FloatingActionButton floatingActionButton;
    private LiveData<List<Tree>>filteredTrees;
    private List<Tree>allTrees;



    public TreeFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.thissear:
                Toast.makeText(getActivity(),getResources().getString(R.string.toast1),Toast.LENGTH_LONG).show();
                break;
            case R.id.isfaker:
                Toast.makeText(getActivity(),getResources().getString(R.string.toast1),Toast.LENGTH_LONG).show();
                break;
            case R.id.developer:
                Intent intent = new Intent(getActivity(),Developer.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setMaxWidth(1000);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tree, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        treeViewModel = new ViewModelProvider(requireActivity()).get(TreeViewModel.class);
        recyclerView = requireActivity().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        filteredTrees = treeViewModel.getAllTreeLive();
        filteredTrees.observe(getViewLifecycleOwner(), new Observer<List<Tree>>() {
            @Override
            public void onChanged(List<Tree> trees) {
                int temp = myAdapter.getItemCount();
                allTrees = trees;
                myAdapter.setAllTrees(trees);
                if(temp!=trees.size())
                {
                    myAdapter.notifyDataSetChanged();
                }
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.START | ItemTouchHelper.END) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final Tree treeToDelete = filteredTrees.getValue().get(viewHolder.getAdapterPosition());
                treeViewModel.deleteTrees(treeToDelete);
                Snackbar.make(requireActivity().findViewById(R.id.treesFragmentView),"删除了一个族谱",Snackbar.LENGTH_LONG)
                        .setAction("撤销", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                treeViewModel.insertTrees(treeToDelete);
                            }
                        })
                        .show();
            }

            Drawable icon = ContextCompat.getDrawable(requireActivity(),R.drawable.ic_delete_black_24dp);
            Drawable background = new ColorDrawable(Color.LTGRAY);

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                View itemView = viewHolder.itemView;
                int iconMargin = (itemView.getHeight()-icon.getIntrinsicHeight())/2;

                int iconLeft,iconRight,iconTop,iconBottom;
                int backTop,backBottom,backLeft,backRight;
                backTop = itemView.getTop();
                backBottom = itemView.getBottom();
                iconTop = itemView.getTop() + (itemView.getHeight()-icon.getIntrinsicHeight())/2;
                iconBottom = iconTop + icon.getIntrinsicHeight();
                if (dX>0){
                    backLeft = itemView.getLeft();
                    backRight = itemView.getLeft()+(int)dX;
                    background.setBounds(backLeft,backTop,backRight,backBottom);
                    iconLeft = itemView.getLeft() + iconMargin;
                    iconRight = iconLeft + icon.getIntrinsicWidth();
                    icon.setBounds(iconLeft,iconTop,iconRight,iconBottom);
                }else if(dX < 0){
                    backRight = itemView.getRight();
                    backLeft = itemView.getRight()+(int)dX;
                    background.setBounds(backLeft,backTop,backRight,backBottom);
                    iconRight = itemView.getRight() - iconMargin;
                    iconLeft = iconRight - icon.getIntrinsicWidth();
                    icon.setBounds(iconLeft,iconTop,iconRight,iconBottom);
                }else{
                    background.setBounds(0,0,0,0);
                    icon.setBounds(0,0,0,0);

                }
                background.draw(c);
                icon.draw(c);
            }


        }).attachToRecyclerView(recyclerView);

        floatingActionButton = requireActivity().findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_treeFragment_to_addTreeFragment);

            }
        });


    }
}
