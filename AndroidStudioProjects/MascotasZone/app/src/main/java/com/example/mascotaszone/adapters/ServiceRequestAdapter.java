package com.example.mascotaszone.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mascotaszone.R;
import com.example.mascotaszone.models.ServiceRequest;
import java.util.List;

public class ServiceRequestAdapter extends RecyclerView.Adapter<ServiceRequestAdapter.ViewHolder> {

    private List<ServiceRequest> requests;

    public ServiceRequestAdapter(List<ServiceRequest> requests) {
        this.requests = requests;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ServiceRequest request = requests.get(position);
        holder.tvServiceType.setText(request.getType().name());
        holder.tvDescription.setText(request.getDescription());
        
        holder.btnHelp.setOnClickListener(v -> {
            android.widget.Toast.makeText(v.getContext(), 
                "¡Gracias! Se le notificará al dueño que quieres ayudar.", 
                android.widget.Toast.LENGTH_SHORT).show();
            // Aquí se podría abrir un chat directamente con el solicitante
        });
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvServiceType, tvDescription;
        android.widget.Button btnHelp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvServiceType = itemView.findViewById(R.id.tvServiceType);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            btnHelp = itemView.findViewById(R.id.btnHelp);
        }
    }
}
