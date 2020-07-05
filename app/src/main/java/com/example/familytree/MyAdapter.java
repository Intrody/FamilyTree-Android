package com.example.familytree;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familytree.com.example.mem.Memember;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Tree> allTrees = new ArrayList<>();
    public   void setAllTrees(List<Tree> allTrees){this.allTrees = allTrees;}


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_card,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Tree tree = allTrees.get(position);
        holder.textViewNumber.setText(String.valueOf(tree.getGenId()));
        holder.textViewTreeName.setText(tree.getGenName());
        holder.textViewPeople.setText("人数：" + tree.getGenNumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"你选择的族谱ID是："+holder.textViewNumber.getText(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(),Memember.class);
                intent.putExtra("ID",holder.textViewNumber.getText());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allTrees.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewNumber,textViewTreeName,textViewPeople;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumber = itemView.findViewById(R.id.textViewNumber);
            textViewTreeName = itemView.findViewById(R.id.textViewMemName);
            textViewPeople = itemView.findViewById(R.id.textViewGen);
        }
    }
}
//package com.example.familytree;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
//    List<Tree> allTrees = new ArrayList<>();
//
//    public  void  setAllTrees(List<Tree> allTrees){
//        this.allTrees = allTrees    ;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from((parent.getContext()));
//        View itemView = layoutInflater.inflate(R.layout.cell_card,parent,false);
//        return new MyViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        Tree tree = allTrees.get(position);
//        holder.textViewNumber.setText(String.valueOf(position + 1));
//        holder.textViewTreeName.setText(tree.getGenName());
//        holder.textViewPeople.setText("人数："+tree.getGenNumber());
//    }
//
//    @Override
//    public int getItemCount() {
//        return allTrees.size();
//    }
//
//    static class MyViewHolder extends  RecyclerView.ViewHolder{
//        TextView textViewNumber,textViewTreeName,textViewPeople;
//        public MyViewHolder(@NonNull View itemView){
//            super(itemView);
//            textViewNumber = itemView.findViewById(R.id.textViewNumber);
//            textViewTreeName = itemView.findViewById(R.id.textViewTreeName);
//            textViewPeople = itemView.findViewById(R.id.textViewPeople);
//        }
//
//    }
//}
