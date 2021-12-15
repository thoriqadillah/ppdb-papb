package com.example.ppdb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ppdb.R;
import com.example.ppdb.model.Siswa;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private Context context;
    private ArrayList<Siswa> siswas;

    FirebaseDatabase db = FirebaseDatabase.getInstance("https://ppdb-papb-1a3c3-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference dbReference = db.getReference(Siswa.class.getSimpleName());

//    private StorageReference reference;

    public Adapter(Context cont, ArrayList<Siswa> nama) {
        context = cont;
        siswas = nama;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pengumuman,
                parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Siswa siswa = siswas.get(position);
        holder.nama.setText(siswa.getNamaLengkap());
//        holder.nama.setText(siswas.get(position).getNamaLengkap());
    }

    @Override
    public int getItemCount() {
        return siswas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nama;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.txNamaPengumuman);
        }
    }
}
