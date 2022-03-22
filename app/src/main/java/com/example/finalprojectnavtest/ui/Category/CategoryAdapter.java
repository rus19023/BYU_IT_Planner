package com.example.finalprojectnavtest.ui.Category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.finalprojectnavtest.R;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList <NotesFragmentCategory> notesFragmentCategories;
    private String selected;


    public CategoryAdapter(Context context, int layout, ArrayList<NotesFragmentCategory> notesFragmentCategories, String selected) {
        this.context = context;
        this.layout = layout;
        this.notesFragmentCategories = notesFragmentCategories;
        this.selected = selected;


    }

    @Override
    public int getCount() {
        return this.notesFragmentCategories.size();
    }

    @Override
    public Object getItem(int position) {
        return this.notesFragmentCategories.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {


        LayoutInflater inflater = LayoutInflater.from(this.context);
        view = inflater.inflate(R.layout.note_cell, null);

        NotesFragmentCategory note_frag = this.notesFragmentCategories.get(position);

        TextView title =view.findViewById(R.id.cellTitle);
        TextView description = view.findViewById(R.id.cellDesc);
        TextView label = view.findViewById(R.id.cellLabel);


        title.setText(note_frag.getTitle());
        description.setText(note_frag.getDescription());
        label.setText(note_frag.getLabel());

        return view;
    }
}