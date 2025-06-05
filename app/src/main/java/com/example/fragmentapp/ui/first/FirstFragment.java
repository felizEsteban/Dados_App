package com.example.fragmentapp.ui.first;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fragmentapp.DadosViewModel;
import com.example.fragmentapp.databinding.FragmentFirstBinding;

import java.util.Random;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    public FirstFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);

        DadosViewModel viewModel = new ViewModelProvider(requireActivity()).get(DadosViewModel.class);

        binding.btnLanzarDado.setOnClickListener(v -> {
            int valor = new Random().nextInt(6) + 1;
            int resId = getResources().getIdentifier("dice_" + valor, "drawable", requireContext().getPackageName());
            binding.imageView.setImageResource(resId);
            viewModel.guardarDado(valor);
        });

        viewModel.dadosLanzados.observe(getViewLifecycleOwner(), dados -> {
            if (dados != null && dados.size() >= 1) {
                int ultimo = dados.get(dados.size() - 1);
                int resId = getResources().getIdentifier("dice_" + ultimo, "drawable", requireContext().getPackageName());
                binding.imageView.setImageResource(resId);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // muy importante para evitar memory leaks
    }
}
