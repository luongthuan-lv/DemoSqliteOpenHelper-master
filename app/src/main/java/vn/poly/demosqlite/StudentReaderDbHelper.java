package vn.poly.demosqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static vn.poly.demosqlite.Student.SQL_CREATE_STUDENTS;
import static vn.poly.demosqlite.Student.SQL_DELETE_STUDENTS;


public class StudentReaderDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Students.db";

    public StudentReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_STUDENTS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_STUDENTS);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    public long insertStudent(Student student) {

        // Xin quyền ghi
        SQLiteDatabase db = this.getWritableDatabase();
        // Tạo ra cặp tên cột vs dự liệu qua đối tượng ContentValues
        ContentValues values = new ContentValues();
        values.put(Student.StudentTable._ID, student.id);
        values.put(Student.StudentTable.COLUMN_NAME, student.name);
        values.put(Student.StudentTable.COLUMN_PHONE, student.phone);
        values.put(Student.StudentTable.COLUMN_ADDRESS, student.address);

        // thêm hàng mới vào trong bảng, dữ liệu trả về là id của hàng (nếu thêm thành công trả về 1 số > -1)
        long newRowId = db.insert(Student.StudentTable.TABLE_NAME, null, values);


        return newRowId;
    }

    public long updateStudent(Student student) {

        SQLiteDatabase db = this.getWritableDatabase();

        // Tạo ra cặp tên cột vs dự liệu qua đối tượng ContentValues
        ContentValues values = new ContentValues();
        values.put(Student.StudentTable._ID, student.id);
        values.put(Student.StudentTable.COLUMN_NAME, student.name);
        values.put(Student.StudentTable.COLUMN_PHONE, student.phone);
        values.put(Student.StudentTable.COLUMN_ADDRESS, student.address);


        // update theo giá trị _ID
        String selection = Student.StudentTable._ID + " = ?";

        // truyền vào giá trị của _ID
        String[] selectionArgs = {student.id};

        int count = db.update(
                Student.StudentTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;
    }


    public long delStudent(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Xóa theo giá trị cột ID
        String selection = Student.StudentTable._ID + " = ?";
        // Truyền giá trị cho id
        String[] selectionArgs = {id};
        // Thực hiện câu lệnh
        int deletedRows = db.delete(Student.StudentTable.TABLE_NAME, selection, selectionArgs);

        return deletedRows;

    }


    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query_all = "SELECT * FROM " + Student.StudentTable.TABLE_NAME;


        Cursor cursor = db.rawQuery(query_all, null);

        if (cursor.getCount() > 0) {
            while (!cursor.isAfterLast()) {
                Student student = new Student();
                student.id = cursor.getString(cursor.getColumnIndex(Student.StudentTable._ID));
                student.name = cursor.getString(cursor.getColumnIndex(Student.StudentTable.COLUMN_NAME));
                student.phone = cursor.getString(cursor.getColumnIndex(Student.StudentTable.COLUMN_PHONE));
                student.address = cursor.getString(cursor.getColumnIndex(Student.StudentTable.COLUMN_ADDRESS));

                students.add(student);

            }
        }
        return students;
    }


}
