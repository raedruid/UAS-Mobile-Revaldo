package com.example.uas_mobile_revaldo.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.uas_mobile_revaldo.R;
import com.example.uas_mobile_revaldo.data.model.Favorit;
import com.example.uas_mobile_revaldo.databinding.ItemEndemikBinding;

public class FavoritAdapter extends ListAdapter<Favorit, FavoritAdapter.ViewHolder> {

    public FavoritAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Favorit> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Favorit>() {
                @Override
                public boolean areItemsTheSame(@NonNull Favorit a, @NonNull Favorit b) {
                    return a.getEndemikId().equals(b.getEndemikId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Favorit a, @NonNull Favorit b) {
                    return a.getNama().equals(b.getNama());
                }
            };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemEndemikBinding binding = ItemEndemikBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemEndemikBinding binding;

        ViewHolder(ItemEndemikBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Favorit item) {
            binding.tvNama.setText(item.getNama());
            binding.tvNamaLatin.setText(item.getNamaLatin());

            Glide.with(binding.getRoot().getContext())
                    .load(item.getFoto())
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                    .centerCrop()
                    .into(binding.ivFoto);
        }
    }
}
