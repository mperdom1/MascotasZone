package com.example.mascotaszone.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mascotaszone.R;
import com.example.mascotaszone.models.Community;
import java.util.List;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {

    private List<Community> communities;

    public CommunityAdapter(List<Community> communities) {
        this.communities = communities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_community, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Community community = communities.get(position);
        holder.tvName.setText(community.getName());
        holder.tvDescription.setText(community.getDescription());
        int count = community.getMemberIds() != null ? community.getMemberIds().size() : 0;
        holder.tvMemberCount.setText(count + " miembros");

        holder.itemView.setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(v.getContext(), com.example.mascotaszone.CommunityDetailActivity.class);
            intent.putExtra("comm_id", community.getId());
            intent.putExtra("comm_name", community.getName());
            intent.putExtra("comm_desc", community.getDescription());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return communities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDescription, tvMemberCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvCommunityName);
            tvDescription = itemView.findViewById(R.id.tvCommunityDescription);
            tvMemberCount = itemView.findViewById(R.id.tvMemberCount);
        }
    }
}
