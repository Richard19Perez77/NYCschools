package com.practice.nycschools.service;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.nycschools.R;
import com.practice.nycschools.model.DataViewModel;
import com.practice.nycschools.model.NYCListClass;
import com.practice.nycschools.FirstFragment;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<MyViewHolder> {

    List<NYCListClass> dataList;
    FirstFragment first;
    DataViewModel dataViewModel;

    public DataAdapter(List<NYCListClass> data, FirstFragment fragment, DataViewModel viewModel) {
        dataList = data;
        first = fragment;
        dataViewModel = viewModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listitem, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String school = dataList.get(position).getSchoolName();
        holder.idTextview.setText(school);
        holder.idTextview.setOnClickListener(v -> {

            dataViewModel.setSelectedData(dataList.get(position).getDbn());

            // add data to fragment
            findNavController(first).navigate(R.id.action_FirstFragment_to_SecondFragment);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {

    TextView idTextview;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        idTextview = itemView.findViewById(R.id.textId);
    }
}


