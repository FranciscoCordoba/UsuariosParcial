package com.example.usuariosparcial.ui.cargar;

import static androidx.core.content.ContextCompat.getSystemService;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.usuariosparcial.R;
import com.example.usuariosparcial.databinding.FragmentCargarBinding;

public class CargarFragment extends Fragment {

    private FragmentCargarBinding binding;
    private CargarViewModel mViewModel;

    public static CargarFragment newInstance() {
        return new CargarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentCargarBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(CargarViewModel.class);
        View root = binding.getRoot();

        //Observadores de los mutables de mensajes de error
        mViewModel.getMErrorNombre().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvErrNombre.setText(s);
            }
        });
        mViewModel.getMErrorApellido().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvErrApellido.setText(s);
            }
        });
        mViewModel.getMErrorEdad().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvErrEdad.setText(s);
            }
        });
        mViewModel.getMErrorDni().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvErrDni.setText(s);
            }
        });

        //Observador del mutable que verifica una carga correcta
        mViewModel.getMValido().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.etNombre.setText("");
                binding.etApellido.setText("");
                binding.etDni.setText("");
                binding.etEdad.setText("");
                Toast.makeText(getContext(), "Usuario agregado exitosamente", Toast.LENGTH_SHORT).show();
            }
        });

        //ClickListener del botón
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = binding.etNombre.getText().toString();
                String apellido = binding.etApellido.getText().toString();
                String dni = binding.etDni.getText().toString();
                String edad = binding.etEdad.getText().toString();

                mViewModel.verificarDatos(nombre, apellido, dni, edad);

                esconderTeclado(view);
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CargarViewModel.class);
        // TODO: Use the ViewModel
    }

    //Para quitar el teclado luego de pulsar el boton de cargar usuario
    public void esconderTeclado(View view) {

        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}