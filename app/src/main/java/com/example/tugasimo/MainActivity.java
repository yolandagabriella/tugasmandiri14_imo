package com.example.tugasimo;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String URL = "http://10.0.2.2/tugasimo/barang.php";

    RecyclerView rv;
    ProgressBar loading;
    TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv      = findViewById(R.id.recyclerView);
        loading = findViewById(R.id.loading);
        tvError = findViewById(R.id.tvError);

        rv.setLayoutManager(new LinearLayoutManager(this));
        ambilData();
    }

    void ambilData() {
        loading.setVisibility(View.VISIBLE);
        rv.setVisibility(View.GONE);
        tvError.setVisibility(View.GONE);

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, URL, null,
            response -> {
                loading.setVisibility(View.GONE);
                try {
                    boolean success = response.getBoolean("success");
                    if (success) {
                        JSONArray data = response.getJSONArray("data");
                        List<Barang> list = new ArrayList<>();
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject obj = data.getJSONObject(i);
                            list.add(new Barang(
                                obj.getInt("id"),
                                obj.getString("nama_barang"),
                                obj.getString("kategori"),
                                obj.getInt("stok"),
                                obj.getInt("harga"),
                                obj.optString("deskripsi", "")
                            ));
                        }
                        rv.setAdapter(new BarangAdapter(this, list));
                        rv.setVisibility(View.VISIBLE);
                    } else {
                        tampilError("Gagal mengambil data");
                    }
                } catch (Exception e) {
                    tampilError("Error: " + e.getMessage());
                }
            },
            error -> {
                loading.setVisibility(View.GONE);
                tampilError("Tidak bisa konek ke server.\nPastikan XAMPP aktif!");
            }
        );

        queue.add(req);
    }

    void tampilError(String msg) {
        tvError.setText(msg);
        tvError.setVisibility(View.VISIBLE);
        rv.setVisibility(View.GONE);
    }
}