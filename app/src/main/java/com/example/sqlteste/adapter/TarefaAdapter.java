package com.example.sqlteste.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlteste.R;
import com.example.sqlteste.model.Tarefa;

import java.util.ArrayList;
import java.util.List;


public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.MyViewHolder> {

    private   List<Tarefa> listaTarefa;

    public TarefaAdapter(List<Tarefa> tarefaLista) {
        this.listaTarefa = tarefaLista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).
                            inflate(R.layout.layout_lista_adapter,parent,false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Tarefa tarefa1 = listaTarefa.get(position);
        holder.tarefa.setText(tarefa1.getNomeTarefa());

    }

    @Override
    public int getItemCount() {
        return listaTarefa.size();
    }

   public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tarefa;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tarefa = itemView.findViewById(R.id.tarefaAdd);
        }
    }
}
