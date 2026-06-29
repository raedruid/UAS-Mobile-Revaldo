package com.example.uas_mobile_revaldo.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.uas_mobile_revaldo.R;
import com.example.uas_mobile_revaldo.data.model.Endemik;
import com.example.uas_mobile_revaldo.databinding.ItemEndemikBinding;

public class EndemikAdapter extends ListAdapter<Endemik, EndemikAdapter.ViewHolder> {

    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onClick(Endemik endemik);
    }

    public EndemikAdapter(OnItemClickListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    private static final DiffUtil.ItemCallback<Endemik> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Endemik>() {
                @Override
                public boolean areItemsTheSame(@NonNull Endemik a, @NonNull Endemik b) {
                    return a.getId().equals(b.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Endemik a, @NonNull Endemik b) {
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
        holder.bind(getItem(position), listener);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemEndemikBinding binding;

        ViewHolder(ItemEndemikBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Endemik item, OnItemClickListener listener) {
            binding.tvNama.setText(item.getNama());
            binding.tvNamaLatin.setText(item.getNamaLatin());

            Glide.with(binding.getRoot().getContext())
                    .load(item.getFoto())
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                    .centerCrop()
                    .into(binding.ivFoto);

            binding.getRoot().setOnClickListener(v -> listener.onClick(item));
        }
    }
}