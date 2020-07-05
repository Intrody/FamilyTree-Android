package com.example.familytree.com.example.mem;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familytree.R;
import com.example.familytree.Tree;

import java.util.ArrayList;
import java.util.List;

public class MineAdapter extends RecyclerView.Adapter<MineAdapter.MineViewHolder> {
List<Mem> allMems = new ArrayList<>();

    public void setAllMems(List<Mem> allTMems){this.allMems = allTMems;}

    @NonNull
    @Override
    public MineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_normal,parent,false);
        return new MineViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final MineViewHolder holder, int position) {
        Mem mem = allMems.get(position);
        holder.textViewNumber.setText(String.valueOf(mem.getMemberId()));
        holder.textViewMemName.setText(mem.getMemberName());
        holder.textViewGen.setText(mem.getMemberDai());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"请稍后...",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(),Member_info.class);
                intent.putExtra("ID",String.valueOf(holder.textViewNumber.getText()));
                Log.i("ID", String.valueOf(holder.textViewNumber.getText()));
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return allMems.size();
    }


    static class MineViewHolder extends RecyclerView.ViewHolder{
        TextView textViewNumber,textViewMemName,textViewGen;
        public MineViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumber = itemView.findViewById(R.id.textViewNumber);
            textViewMemName = itemView.findViewById(R.id.textViewMemName);
            textViewGen = itemView.findViewById(R.id.textViewGen);
        }
    }
}
