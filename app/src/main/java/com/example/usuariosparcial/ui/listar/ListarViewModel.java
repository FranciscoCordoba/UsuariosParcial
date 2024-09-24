package com.example.usuariosparcial.ui.listar;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.usuariosparcial.MainActivity;
import com.example.usuariosparcial.modelos.Persona;

import java.util.ArrayList;
import java.util.Comparator;

public class ListarViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Persona>> mPersonas;

    public ListarViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<Persona>> getMCiudad(){
        if(mPersonas == null){
            mPersonas= new MutableLiveData<>();
        }
        return mPersonas;
    }

    //Ordena la lista y la carga al mutable
    public void cargarLista(){
        MainActivity.personas.sort(Comparator.comparingInt(Persona::getEdad));
        mPersonas.setValue(MainActivity.personas);
    }
}