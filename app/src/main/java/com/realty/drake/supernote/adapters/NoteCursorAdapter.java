package com.realty.drake.supernote.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.realty.drake.supernote.Note;
import com.realty.drake.supernote.R;

import java.util.ArrayList;

/**
 * Created by drake on 9/7/18
 */
public class NoteCursorAdapter extends CursorAdapter {
    public NoteCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_note, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView tvNoteTitle = view.findViewById(R.id.tvNoteTitle);
        TextView tvNoteBody = view.findViewById(R.id.tvNoteBody);
        // Extract properties from cursor
        String noteTitle = cursor.getString(cursor.getColumnIndexOrThrow("noteTitle"));
        String noteBody = cursor.getString(cursor.getColumnIndexOrThrow("noteBody"));
        // Populate fields with extracted properties
        tvNoteTitle.setText(noteTitle);
        tvNoteBody.setText(noteBody);
    }
}