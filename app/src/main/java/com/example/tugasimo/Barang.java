package com.example.tugasimo;

public class Barang {
    int id;
    String namaBarang, kategori, deskripsi;
    int stok, harga;

    public Barang(int id, String namaBarang, String kategori, int stok, int harga, String deskripsi) {
        this.id = id;
        this.namaBarang = namaBarang;
        this.kategori = kategori;
        this.stok = stok;
        this.harga = harga;
        this.deskripsi = deskripsi;
    }
}