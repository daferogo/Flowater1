package com.example.flowater1;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class PrediccionHolder extends RecyclerView.ViewHolder {
    private View mView;

    public PrediccionHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }

    public void setFecha(String fecha) {
        TextView field = (TextView) mView.findViewById(R.id.lblFecha);
        field.setText(fecha);
    }

    public void setConsumo(float consumo) {
        TextView field = (TextView) mView.findViewById(R.id.lblConsumo);
        field.setText(String.valueOf(consumo)+" m3");
    }

}

