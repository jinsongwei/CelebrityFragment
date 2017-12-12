package com.williamjin.celebrityfragment.model;

import android.provider.BaseColumns;

/**
 * Created by william on 12/4/17.
 */

public class DatabaseMeta {
    private DatabaseMeta(){}

    public static class CelebrityEntry implements BaseColumns{

        public static final String TABLE_NAME = "celebrity";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String GENDER = "gender";
        public static final String TYPE = "type";
        public static final String FAVORITE = "favorite";

    }
}
