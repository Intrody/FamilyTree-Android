package com.example.familytree.com.example.mem;


import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.example.familytree.R;
import com.example.familytree.Tree;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MememberFragment extends Fragment {
    private MemViewModel memViewModel;
    private RecyclerView recyclerView;
    private MineAdapter mineAdapter;
    private FloatingActionButton floatingActionButton;
    private LiveData<List<Mem>>filteredMems;
    private List<Mem>allMems;

    public MememberFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.clear:
                AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                builder.setTitle("清空数据");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        memViewModel.deleteAllMems();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create();
                builder.show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.mem_menu,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search_mem).getActionView();
        searchView.setMaxWidth(1000);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                String patten = s.trim();
                filteredMems.removeObservers(requireActivity());
                filteredMems = memViewModel.findMemsWithPatten(patten);
                filteredMems.observe(getViewLifecycleOwner(), new Observer<List<Mem>>() {
                    @Override
                    public void onChanged(List<Mem> mems) {
                        int temp = mineAdapter.getItemCount();
                        allMems = mems;
                        mineAdapter.setAllMems(mems);
                        if (temp != mems.size()){
                            mineAdapter.notifyDataSetChanged();
                        }
                    }
                });
                return true;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_memember, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {        super.onActivityCreated(savedInstanceState);
        memViewModel = new ViewModelProvider(requireActivity()).get(MemViewModel.class);
        recyclerView = requireActivity().findViewById(R.id.recyclerViewMem);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        mineAdapter = new MineAdapter();
        recyclerView.setAdapter(mineAdapter);
        filteredMems = memViewModel.getAllMemLive();
        filteredMems.observe(getViewLifecycleOwner(), new Observer<List<Mem>>() {
            @Override
            public void onChanged(List<Mem> mems) {
                int temp = mineAdapter.getItemCount();
                allMems = mems;
                mineAdapter.setAllMems(mems);
                if (temp != mems.size()){
                    mineAdapter.notifyDataSetChanged();
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
                final Mem memToDelete = allMems.get(viewHolder.getAdapterPosition());
                memViewModel.deleteMems(memToDelete);
                Snackbar.make(requireActivity().findViewById(R.id.memsFragmentView),"删除了一个成员",Snackbar.LENGTH_LONG)
                        .setAction("撤销", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                memViewModel.insertMems(memToDelete);
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






        floatingActionButton = requireActivity().findViewById(R.id.floatingActionButtonChange);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_mememberFragment_to_addMemFragment);
            }
        });

    }



}
