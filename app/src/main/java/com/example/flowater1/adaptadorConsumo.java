package com.example.flowater1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class adaptadorConsumo extends RecyclerView.Adapter<adaptadorConsumo.consumoViewHolder> {

    private Context mCtx;
    private List<Prediccion> consumos;

    public adaptadorConsumo(History_Activity mCtx, List<Prediccion> consumos) {
        this.mCtx = mCtx;
        this.consumos = consumos;
    }

    @NonNull
    @Override
    public consumoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_lista, parent, false);
        return new consumoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull consumoViewHolder holder, int position) {
        Prediccion consumoActual = consumos.get(position);

        holder.textViewFecha.setText("Fecha-Hora: " + consumoActual.getFecha());
        holder.textViewConsumo.setText("Consumo: " + consumoActual.getConsumo() + "m3");

    }

    @Override
    public int getItemCount() {
        return consumos.size();
    }

    class consumoViewHolder extends RecyclerView.ViewHolder {

        TextView textViewFecha, textViewConsumo;

        public consumoViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewFecha = itemView.findViewById(R.id.lblFecha);
            textViewConsumo = itemView.findViewById(R.id.lblConsumo);
        }
    }
}