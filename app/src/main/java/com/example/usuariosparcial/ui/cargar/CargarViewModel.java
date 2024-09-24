package com.example.usuariosparcial.ui.cargar;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.usuariosparcial.MainActivity;
import com.example.usuariosparcial.modelos.Persona;

import java.util.Comparator;
import java.util.Objects;

public class CargarViewModel extends AndroidViewModel {

    private MutableLiveData<String> mErrorNombre;
    private MutableLiveData<String> mErrorApellido;
    private MutableLiveData<String> mErrorEdad;
    private MutableLiveData<String> mErrorDni;
    private MutableLiveData<Integer> mValido;

    public CargarViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMErrorNombre(){
        if(mErrorNombre == null){
            mErrorNombre = new MutableLiveData<>();
        }
        return mErrorNombre;
    }

    public LiveData<String> getMErrorApellido(){
        if(mErrorApellido == null){
            mErrorApellido = new MutableLiveData<>();
        }
        return mErrorApellido;
    }

    public LiveData<String> getMErrorEdad(){
        if(mErrorEdad == null){
            mErrorEdad = new MutableLiveData<>();
        }
        return mErrorEdad;
    }

    public LiveData<String> getMErrorDni(){
        if(mErrorDni == null){
            mErrorDni = new MutableLiveData<>();
        }
        return mErrorDni;
    }

    public LiveData<Integer> getMValido(){
        if(mValido == null){
            mValido = new MutableLiveData<>(0);
        }
        return mValido;
    }

    //Validación de datos de los campos
    public void verificarDatos(String nom, String ape, String dni, String edad){
        boolean bandera = true;
        int dniValido, edadValida;

        //Antes de validar datos, eliminamos todos los mensajes de error
        mErrorNombre.setValue("");
        mErrorApellido.setValue("");
        mErrorDni.setValue("");
        mErrorEdad.setValue("");

        //Validación de datos
        if(nom.trim().isEmpty() || nom.trim().length() < 2 || nom.trim().length() > 15){
            mErrorNombre.setValue("*ERROR: debe ingresar un nombre válido (2 a 15 caracteres)");
            bandera = false;}
        if(ape.trim().isEmpty() || ape.trim().length() < 2 || ape.trim().length() > 15){
            mErrorApellido.setValue("*ERROR: debe ingresar un apellido válido (2 a 15 caracteres)");
            bandera = false;}
        if(dni.trim().isEmpty() || dni.trim().length() < 6 || dni.trim().length() > 8 || !dni.matches("\\d+")){
            mErrorDni.setValue("*ERROR: debe ingresar un DNI válido (de 6 a 8 dígitos)");
            bandera = false;}
        if(dni.matches("\\d+")){
            if(dniRepetido(Integer.parseInt(dni))){
                mErrorDni.setValue("*ERROR: este DNI ya existe");
                bandera = false;}}
        if(edad.trim().isEmpty() || !edad.matches("\\d+")){
            mErrorEdad.setValue("*ERROR: debe ingresar una edad válida");
            bandera = false;}
        if(edad.matches("\\d+")) {
            if(Integer.parseInt(edad) < 0 || Integer.parseInt(edad) > 120){
                mErrorEdad.setValue("*ERROR: debe ingresar una edad entre 0 y 120 años");
                bandera = false;}
        }

        if(bandera){
            dniValido = Integer.parseInt(dni);
            edadValida = Integer.parseInt(edad);
            cargarUsuario(nom, ape, dniValido, edadValida);
        }

    }

    //Funcion que busca en la lista de personas si se repite el dni
    private boolean dniRepetido(int dni){
        for (Persona persona: MainActivity.personas) {
            if(persona.getDni() == dni){
                return true;
            }
        }
        return false;
    }

    private void cargarUsuario(String nom, String ape, int dni, int edad){
        Persona persona = new Persona(nom, ape, edad, dni);
        MainActivity.personas.add(persona);
        mValido.setValue(mValido.getValue() + 1); //Modifica el mutable para que se dispare su escuchador y limpie los campos
    }

}