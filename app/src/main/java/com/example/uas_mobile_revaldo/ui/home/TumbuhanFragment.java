package com.example.uas_mobile_revaldo.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import com.example.uas_mobile_revaldo.adapter.EndemikAdapter;
import com.example.uas_mobile_revaldo.databinding.FragmentListBinding;
import com.example.uas_mobile_revaldo.ui.detail.DetailActivity;
import com.example.uas_mobile_revaldo.viewmodel.HomeViewModel;

public class TumbuhanFragment extends Fragment {

    private FragmentListBinding binding;
    private HomeViewModel viewModel;
    private EndemikAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        adapter = new EndemikAdapter(item -> {
            Intent intent = new Intent(requireContext(), DetailActivity.class);
            intent.putExtra("ENDEMIK_ID", item.getId());
            startActivity(intent);
        });

        binding.rvList.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        binding.rvList.setAdapter(adapter);

        viewModel.getTumbuhan().observe(getViewLifecycleOwner(), list -> {
            adapter.submitList(list);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
