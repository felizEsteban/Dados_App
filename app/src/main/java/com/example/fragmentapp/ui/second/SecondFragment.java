package com.example.fragmentapp.ui.second;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fragmentapp.DadosViewModel;
import com.example.fragmentapp.databinding.FragmentSecondBinding;

import java.util.Random;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    public SecondFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);

        DadosViewModel viewModel = new ViewModelProvider(requireActivity()).get(DadosViewModel.class);

        binding.btnLanzar.setOnClickListener(v -> {
            Random random = new Random();
            int val1 = random.nextInt(6) + 1;
            int val2 = random.nextInt(6) + 1;

            // Mostrar de derecha a izquierda
            binding.dado2.setImageResource(getResources().getIdentifier("dice_" + val1, "drawable", requireContext().getPackageName()));
            binding.dado1.setImageResource(getResources().getIdentifier("dice_" + val2, "drawable", requireContext().getPackageName()));

            viewModel.guardarDado(val1);
            viewModel.guardarDado(val2);
        });

        viewModel.dadosLanzados.observe(getViewLifecycleOwner(), dados -> {
            if (dados != null && dados.size() >= 3) {
                int val1 = dados.get(dados.size() - 2);
                int val2 = dados.get(dados.size() - 1);

                binding.dado2.setImageResource(getResources().getIdentifier("dice_" + val1, "drawable", requireContext().getPackageName()));
                binding.dado1.setImageResource(getResources().getIdentifier("dice_" + val2, "drawable", requireContext().getPackageName()));
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
