package com.example.uas_mobile_revaldo.ui.detail;

import android.os.Bundle;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;
import com.example.uas_mobile_revaldo.BaseActivity;
import com.example.uas_mobile_revaldo.R;
import com.example.uas_mobile_revaldo.data.model.Favorit;
import com.example.uas_mobile_revaldo.databinding.ActivityDetailBinding;
import com.example.uas_mobile_revaldo.viewmodel.HomeViewModel;

public class DetailActivity extends BaseActivity {

    private ActivityDetailBinding binding;
    private HomeViewModel viewModel;
    private boolean isFavorit = false;
    private String endemikId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Setup tombol back di toolbar
        binding.toolbar.setNavigationOnClickListener(v -> finish());

        endemikId = getIntent().getStringExtra("ENDEMIK_ID");
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // Cek status favorit di background thread
        viewModel.checkIsFavorit(endemikId, result -> runOnUiThread(() -> {
            isFavorit = result;
            updateFavoritIcon();
        }));

        // Observe data detail dari ROOM
        viewModel.getById(endemikId).observe(this, endemik -> {
            if (endemik == null) return;

            // Set judul toolbar
            binding.toolbar.setTitle(endemik.getNama());
            binding.toolbar.setSubtitle(endemik.getTipe());

            // Isi data
            binding.tvNamaLatin.setText(endemik.getNamaLatin());
            binding.tvDeskripsi.setText(endemik.getDeskripsi());
            binding.tvAsal.setText(endemik.getAsal());
            binding.tvSebaran.setText(endemik.getSebaran());
            binding.tvStatus.setText(endemik.getStatus());

            // Warna badge status
            if ("Terancam Punah".equals(endemik.getStatus())) {
                binding.tvStatus.setBackgroundResource(R.drawable.bg_status_terancam);
                binding.tvStatus.setTextColor(getColor(R.color.status_terancam));
            } else {
                binding.tvStatus.setBackgroundResource(R.drawable.bg_status_aman);
                binding.tvStatus.setTextColor(getColor(R.color.status_aman));
            }

            // Load gambar
            Glide.with(this)
                    .load(endemik.getFoto())
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                    .into(binding.ivFoto);

            // Toggle favorit
            binding.ivFavoritToggle.setOnClickListener(v -> {
                if (isFavorit) {
                    viewModel.removeFavorit(endemik.getId());
                    isFavorit = false;
                    Toast.makeText(this, "Dihapus dari favorit", Toast.LENGTH_SHORT).show();
                } else {
                    Favorit fav = new Favorit(
                            endemik.getId(),
                            endemik.getNama(),
                            endemik.getNamaLatin(),
                            endemik.getTipe(),
                            endemik.getFoto(),
                            endemik.getStatus()
                    );
                    viewModel.addFavorit(fav);
                    isFavorit = true;
                    Toast.makeText(this, "Ditambahkan ke favorit ♡", Toast.LENGTH_SHORT).show();
                }
                updateFavoritIcon();
            });
        });
    }

    private void updateFavoritIcon() {
        if (isFavorit) {
            binding.ivFavoritToggle.setImageResource(R.drawable.ic_favorite);
        } else {
            binding.ivFavoritToggle.setImageResource(R.drawable.ic_favorite_border);
        }
    }
}
