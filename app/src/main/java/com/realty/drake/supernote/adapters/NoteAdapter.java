package com.realty.drake.supernote.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.realty.drake.supernote.Note;
import com.realty.drake.supernote.R;

import java.util.ArrayList;

/**
 * Created by drake on 9/7/18
 */
public class NoteAdapter extends ArrayAdapter<Note> {

    public NoteAdapter(Context context, ArrayList<Note> notes) {
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Note note = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_note, parent, false);
        }
        TextView tvName = convertView.findViewById(R.id.tvNoteTitle);
        TextView tvHome = convertView.findViewById(R.id.tvNoteBody);

        // Populate the data into the template view using the data object
        tvName.setText(note.getNoteTitle());
        tvHome.setText(note.getNoteBody());

        // Return the completed view to render on screen
        return convertView;

    }

}