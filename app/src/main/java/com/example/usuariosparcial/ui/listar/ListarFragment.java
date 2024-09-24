package com.example.usuariosparcial.ui.listar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usuariosparcial.R;
import com.example.usuariosparcial.databinding.FragmentCargarBinding;
import com.example.usuariosparcial.databinding.FragmentListarBinding;
import com.example.usuariosparcial.modelos.Persona;
import com.example.usuariosparcial.ui.cargar.CargarViewModel;

import java.util.ArrayList;

public class ListarFragment extends Fragment {

    private FragmentListarBinding binding;
    private ListarViewModel mViewModel;

    public static ListarFragment newInstance() {
        return new ListarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentListarBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(ListarViewModel.class);
        View root = binding.getRoot();

        mViewModel.getMCiudad().observe(getViewLifecycleOwner(), new Observer<ArrayList<Persona>>() {
            @Override
            public void onChanged(ArrayList<Persona> personas) {
                PersonaAdapter pa = new PersonaAdapter(personas, inflater);
                GridLayoutManager glm = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
                binding.rv.setAdapter(pa);
                binding.rv.setLayoutManager(glm);
            }
        });

        mViewModel.cargarLista();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListarViewModel.class);
        // TODO: Use the ViewModel
    }

}