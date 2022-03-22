package com.example.finalprojectnavtest.ui.Category;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.finalprojectnavtest.App;
import com.example.finalprojectnavtest.R;
import com.example.finalprojectnavtest.ui.AddNote.Note;
import com.example.finalprojectnavtest.ui.AddNote.NoteAdapter;
import com.example.finalprojectnavtest.ui.AddNote.ObserveNote;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CategoryFragment extends Fragment {

    private Spinner spinner;
    private ListView note_list;
    private List<Note> noteList;

    private NoteAdapter noteAdapter;
    private CategoryAdapter adapterlist;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_category, null);
        spinner = view.findViewById(R.id.spinner);
        note_list = view.findViewById(R.id.tv_result);

        Set<String> labelSet = new HashSet<>();
        //List<String> descripSet = new LinkedList<>();
        //List<String> titleSet = new LinkedList<>();
        Set<String> descrptSet = new HashSet<>();

        noteList  = App.getInstance().getNoteDao().getAll();

        for (Note note: noteList) {
            labelSet.add(note.getLabel());
            descrptSet.add(note.getDescription());
        }

        String [] labeArray = labelSet.toArray(new String[0]);

        ArrayAdapter<String> adapter = new ArrayAdapter <String> (getContext(), android.R.layout.simple_spinner_item, labeArray);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext().getApplicationContext(), "Seleccionado " + adapterView.getItemAtPosition(i), Toast.LENGTH_LONG).show();
                String selected = adapterView.getItemAtPosition(i).toString();


                //For to collect the label and description into the new Class
                //If it's the same label and description it's going to be in.
                NotesFragmentCategory.notes_frag.clear();
                for (Note note: noteList) {
                    for (String d: descrptSet) {
                        if (selected.equals(note.getLabel()) && d.equals(note.getDescription())) {
                            NotesFragmentCategory note_frag = new NotesFragmentCategory(note.getTitle(), note.getDescription(), note.getLabel(), note.getCode(), note.getId());
                            NotesFragmentCategory.notes_frag.add(note_frag);
                            //Toast.makeText(getContext().getApplicationContext(), "Dentro del if" + note_frag.hashCode(), Toast.LENGTH_LONG).show();
                        }

                    }
                }
                //Sending values to the Adapter Listview
                adapterlist = new CategoryAdapter(getContext(), R.layout.note_cell2, NotesFragmentCategory.notes_frag, selected);

                //Show the notes on ListView
                note_list.setAdapter(adapterlist);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        return view;
    }

    public void  onResume() {
        super.onResume();
        note_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), ObserveNote.class);
                intent.putExtra("title", NotesFragmentCategory.notes_frag.get(i).getTitle());
                intent.putExtra("label", NotesFragmentCategory.notes_frag.get(i).getLabel());
                intent.putExtra("description", NotesFragmentCategory.notes_frag.get(i).getDescription());
                intent.putExtra("code", NotesFragmentCategory.notes_frag.get(i).getCode());
                intent.putExtra("id", i);
                intent.putExtra("del", true);
                startActivity(intent);
            }
        });
    }

}