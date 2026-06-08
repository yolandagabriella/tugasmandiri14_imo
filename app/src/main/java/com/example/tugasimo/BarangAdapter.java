package com.example.tugasimo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.VH> {

    Context context;
    List<Barang> list;

    public BarangAdapter(Context context, List<Barang> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_barang, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int i) {
        Barang b = list.get(i);
        h.nama.setText(b.namaBarang);
        h.kategori.setText(b.kategori);
        h.harga.setText("Rp " + String.format("%,d", b.harga).replace(",", "."));
        h.stok.setText(b.stok > 0 ? "Stok: " + b.stok : "Habis");

        h.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("id",    b.id);
            intent.putExtra("nama",  b.namaBarang);
            intent.putExtra("kat",   b.kategori);
            intent.putExtra("stok",  b.stok);
            intent.putExtra("harga", b.harga);
            intent.putExtra("desc",  b.deskripsi);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() { return list.size(); }

    public static class VH extends RecyclerView.ViewHolder {
        TextView nama, kategori, harga, stok;
        VH(View v) {
            super(v);
            nama     = v.findViewById(R.id.tvNama);
            kategori = v.findViewById(R.id.tvKategori);
            harga    = v.findViewById(R.id.tvHarga);
            stok     = v.findViewById(R.id.tvStok);
        }
    }
}