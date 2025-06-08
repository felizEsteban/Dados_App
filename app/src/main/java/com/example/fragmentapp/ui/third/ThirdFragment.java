package com.example.fragmentapp.ui.third;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fragmentapp.DadosViewModel;
import com.example.fragmentapp.databinding.FragmentThirdBinding;

import java.util.Random;

public class ThirdFragment extends Fragment {

    private FragmentThirdBinding binding;

    public ThirdFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentThirdBinding.inflate(inflater, container, false);

        DadosViewModel viewModel = new ViewModelProvider(requireActivity()).get(DadosViewModel.class);

        binding.btnLanzar.setOnClickListener(v -> {
            Random random = new Random();
            int val1 = random.nextInt(6) + 1;
            int val2 = random.nextInt(6) + 1;
            int val3 = random.nextInt(6) + 1;

            binding.dado3.setImageResource(getResources().getIdentifier("dice_" + val1, "drawable", requireContext().getPackageName()));
            binding.dado2.setImageResource(getResources().getIdentifier("dice_" + val2, "drawable", requireContext().getPackageName()));
            binding.dado1.setImageResource(getResources().getIdentifier("dice_" + val3, "drawable", requireContext().getPackageName()));

            viewModel.guardarDado(val1);
            viewModel.guardarDado(val2);
            viewModel.guardarDado(val3);
        });

        viewModel.dadosLanzados.observe(getViewLifecycleOwner(), dados -> {
            if (dados != null && dados.size() >= 6) {
                int val1 = dados.get(dados.size() - 3);
                int val2 = dados.get(dados.size() - 2);
                int val3 = dados.get(dados.size() - 1);

                binding.dado3.setImageResource(getResources().getIdentifier("dice_" + val1, "drawable", requireContext().getPackageName()));
                binding.dado2.setImageResource(getResources().getIdentifier("dice_" + val2, "drawable", requireContext().getPackageName()));
                binding.dado1.setImageResource(getResources().getIdentifier("dice_" + val3, "drawable", requireContext().getPackageName()));
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
