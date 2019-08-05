package vn.poly.demosqlite;

import android.provider.BaseColumns;

public class Student {

    public String id;
    public String name;
    public String phone;
    public String address;


    public static final String SQL_CREATE_STUDENTS =
            "CREATE TABLE " + StudentTable.TABLE_NAME + " (" +
                    StudentTable._ID + " INTEGER PRIMARY KEY," +
                    StudentTable.COLUMN_NAME + " TEXT," +
                    StudentTable.COLUMN_ADDRESS + " TEXT," +
                    StudentTable.COLUMN_PHONE + " TEXT)";

    public static final String SQL_DELETE_STUDENTS =
            "DROP TABLE IF EXISTS " + StudentTable.TABLE_NAME;

    public static class StudentTable implements BaseColumns {

        public static final String TABLE_NAME = "Student";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_PHONE = "phone";

    }
}
