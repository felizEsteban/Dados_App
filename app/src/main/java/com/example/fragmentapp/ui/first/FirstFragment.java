package com.example.fragmentapp.ui.first;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fragmentapp.DadosViewModel;
import com.example.fragmentapp.R;

import java.util.Random;

public class FirstFragment extends Fragment {

    public FirstFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        ImageView dadoImg = view.findViewById(R.id.imageView);
        Button btnLanzar = view.findViewById(R.id.btnLanzarDado);

        DadosViewModel viewModel = new ViewModelProvider(requireActivity()).get(DadosViewModel.class);

        btnLanzar.setOnClickListener(v -> {
            int valor = new Random().nextInt(6) + 1;
            int resId = getResources().getIdentifier("dice_" + valor, "drawable", requireContext().getPackageName());
            dadoImg.setImageResource(resId);
            viewModel.guardarDado(valor);
        });

        viewModel.dadosLanzados.observe(getViewLifecycleOwner(), dados -> {
            if (dados != null && dados.size() >= 1) {
                int ultimo = dados.get(dados.size() - 1);
                int resId = getResources().getIdentifier("dice_" + ultimo, "drawable", requireContext().getPackageName());
                dadoImg.setImageResource(resId);
            }
        });

        return view;
    }
}
