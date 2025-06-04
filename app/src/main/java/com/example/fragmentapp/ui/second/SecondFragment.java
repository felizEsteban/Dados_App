package com.example.fragmentapp.ui.second;

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

public class SecondFragment extends Fragment {

    public SecondFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        ImageView dado1Img = view.findViewById(R.id.dado1);
        ImageView dado2Img = view.findViewById(R.id.dado2);
        Button btnLanzar = view.findViewById(R.id.btnLanzar);

        DadosViewModel viewModel = new ViewModelProvider(requireActivity()).get(DadosViewModel.class);

        btnLanzar.setOnClickListener(v -> {
            Random random = new Random();
            int val1 = random.nextInt(6) + 1;
            int val2 = random.nextInt(6) + 1;

            dado2Img.setImageResource(getResources().getIdentifier("dice_" + val1, "drawable", requireContext().getPackageName()));
            dado1Img.setImageResource(getResources().getIdentifier("dice_" + val2, "drawable", requireContext().getPackageName()));

            viewModel.guardarDado(val1);
            viewModel.guardarDado(val2);
        });



        viewModel.dadosLanzados.observe(getViewLifecycleOwner(), dados -> {
            if (dados != null && dados.size() >= 3) {
                int val1 = dados.get(dados.size() - 2);
                int val2 = dados.get(dados.size() - 1);

                dado2Img.setImageResource(getResources().getIdentifier("dice_" + val1, "drawable", requireContext().getPackageName()));
                dado1Img.setImageResource(getResources().getIdentifier("dice_" + val2, "drawable", requireContext().getPackageName()));
            }
        });



        return view;
    }
}
