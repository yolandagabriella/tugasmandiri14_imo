package com.example.tugasimo;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle e = getIntent().getExtras();

        if (e != null) {
            ((TextView) findViewById(R.id.tvDetailId)).setText("ID: " + e.getInt("id"));
            ((TextView) findViewById(R.id.tvDetailNama)).setText(e.getString("nama"));
            ((TextView) findViewById(R.id.tvDetailKategori)).setText("Kategori: " + e.getString("kat"));
            ((TextView) findViewById(R.id.tvDetailHarga)).setText("Rp " + String.format("%,d", e.getInt("harga")).replace(",", "."));
            ((TextView) findViewById(R.id.tvDetailStok)).setText("Stok: " + e.getInt("stok"));
            ((TextView) findViewById(R.id.tvDetailDeskripsi)).setText(e.getString("desc"));
        }
    }
}