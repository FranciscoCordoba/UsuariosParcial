package com.example.usuariosparcial.ui.listar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usuariosparcial.R;
import com.example.usuariosparcial.modelos.Persona;

import java.util.List;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.ViewHolderPersona> {

    private List<Persona> personas;
    private LayoutInflater li;

    public PersonaAdapter(List<Persona> personas, LayoutInflater li){
        this.personas = personas;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolderPersona onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.item, parent, false);
        return new ViewHolderPersona(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPersona holder, int position) {
        Persona persona = personas.get(position);
        String n = "Nombre: " + persona.getNombre();
        String a = "Apellido: " + persona.getApellido();
        String e = "Edad: " + persona.getEdad();
        String d = "DNI: " + persona.getDni();
        holder.tvNombre.setText(n);
        holder.tvApellido.setText(a);
        holder.tvEdad.setText(e);
        holder.tvDni.setText(d);
        holder.img.setImageResource(R.drawable.avatar);
    }

    @Override
    public int getItemCount() {
        return personas.size();
    }

    public class ViewHolderPersona extends RecyclerView.ViewHolder{

        TextView tvNombre, tvApellido, tvEdad, tvDni;
        ImageView img;

        public ViewHolderPersona(@NonNull View itemView) {
            super(itemView);

            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvApellido = itemView.findViewById(R.id.tvApellido);
            tvEdad = itemView.findViewById(R.id.tvEdad);
            tvDni = itemView.findViewById(R.id.tvDni);
            img = itemView.findViewById(R.id.img);

        }
    }

}
