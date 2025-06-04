// main
package com.example.fragmentapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.fragmentapp.databinding.ActivityMainBinding;
import com.example.fragmentapp.ui.first.FirstFragment;
import com.example.fragmentapp.ui.second.SecondFragment;
import com.example.fragmentapp.ui.third.ThirdFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FirstFragment()).commit();
        binding.btnFirst.setOnClickListener(v -> showFragment(new FirstFragment()));
        binding.btnSecond.setOnClickListener(v -> showFragment(new SecondFragment()));
        binding.btnThrid.setOnClickListener(v -> showFragment(new ThirdFragment()));
    }

    private void showFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
    }
}