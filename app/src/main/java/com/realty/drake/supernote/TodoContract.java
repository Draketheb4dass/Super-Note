package com.realty.drake.supernote;

/* Created by Drake
 * June 13 2018
 * This is a Contract class*/

import android.provider.BaseColumns;

public final class TodoContract {
    private TodoContract(){}

    public static abstract class TodoEntry implements BaseColumns{
        public static final String _ID = BaseColumns._ID;
        public static final String TABLE_NAME = "todo";
        public static final String COLUMN_TODO_ITEM = "item";


    }
}