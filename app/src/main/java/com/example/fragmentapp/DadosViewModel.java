package com.example.fragmentapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class DadosViewModel extends ViewModel {
    public MutableLiveData<List<Integer>> dadosLanzados = new MutableLiveData<>(new ArrayList<>());

    public void guardarDado(int valor) {
        List<Integer> actuales = new ArrayList<>(dadosLanzados.getValue());
        actuales.add(valor);
        dadosLanzados.setValue(actuales);
    }
}