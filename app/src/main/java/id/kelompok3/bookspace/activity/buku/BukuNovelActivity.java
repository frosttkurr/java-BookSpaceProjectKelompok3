package id.kelompok3.bookspace.activity.buku;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.kelompok3.bookspace.R;
import id.kelompok3.bookspace.adapter.buku.BukuNovelAdapter;
import id.kelompok3.bookspace.adapter.buku.BukuSejarahAdapter;
import id.kelompok3.bookspace.database.DBHelper;
import id.kelompok3.bookspace.model.BukuHandler;

public class BukuNovelActivity extends AppCompatActivity {
    private DBHelper database;
    protected RecyclerView recyclerView;
    protected RecyclerView.Adapter bukuNovelAdapter;
    private ArrayList<BukuHandler> bukuHandler = new ArrayList<BukuHandler>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buku_novel);

        database = new DBHelper(this);
        recyclerView = (RecyclerView)findViewById(R.id.list_novel);

        final DBHelper dh = new DBHelper(getApplicationContext());
        Cursor cursor = dh.tampilkanBukuNovel();
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            while (!cursor.isAfterLast()) {
                BukuHandler bukuHandlerList = new BukuHandler();
                bukuHandlerList.setJudul((cursor.getString(cursor.getColumnIndexOrThrow("judul"))));
                bukuHandlerList.setKategori((cursor.getString(cursor.getColumnIndexOrThrow("kategori"))));
                bukuHandler.add(bukuHandlerList);
                cursor.moveToNext();
            }
            dh.close();
        }

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        bukuNovelAdapter = new BukuNovelAdapter(bukuHandler, BukuNovelActivity.this, recyclerView);
        recyclerView.setAdapter(bukuNovelAdapter);
    }
}