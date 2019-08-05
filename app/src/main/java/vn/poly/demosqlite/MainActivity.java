package vn.poly.demosqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    StudentReaderDbHelper studentReaderDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentReaderDbHelper = new StudentReaderDbHelper(this);




    }

    @Override
    protected void onDestroy() {
        studentReaderDbHelper.close();
        super.onDestroy();
    }
}
