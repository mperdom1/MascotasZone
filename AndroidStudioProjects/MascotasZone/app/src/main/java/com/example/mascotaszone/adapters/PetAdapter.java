package com.example.mascotaszone.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mascotaszone.R;
import com.example.mascotaszone.models.Pet;
import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.ViewHolder> {

    private List<Pet> pets;

    public PetAdapter(List<Pet> pets) {
        this.pets = pets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pet pet = pets.get(position);
        holder.tvPetName.setText(pet.getName());
        holder.tvPetBreed.setText(pet.getBreed());
        holder.tvPetAge.setText("Edad: " + pet.getAge() + " años");
        
        if (pet.getImageUri() != null) {
            holder.ivPetImage.setImageURI(android.net.Uri.parse(pet.getImageUri()));
        } else {
            holder.ivPetImage.setImageResource(android.R.drawable.ic_menu_gallery);
        }
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPetName, tvPetBreed, tvPetAge;
        android.widget.ImageView ivPetImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPetName = itemView.findViewById(R.id.tvPetName);
            tvPetBreed = itemView.findViewById(R.id.tvPetBreed);
            tvPetAge = itemView.findViewById(R.id.tvPetAge);
            ivPetImage = itemView.findViewById(R.id.ivPetImage);
        }
    }
}
