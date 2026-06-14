package com.example.mascotaszone;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mascotaszone.adapters.MemberAdapter;
import com.example.mascotaszone.db.DatabaseHelper;
import java.util.List;

public class CommunityDetailActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private String communityId;
    private MemberAdapter adapter;
    private List<String> memberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_detail);

        dbHelper = new DatabaseHelper(this);
        communityId = getIntent().getStringExtra("comm_id");
        String name = getIntent().getStringExtra("comm_name");
        String desc = getIntent().getStringExtra("comm_desc");

        TextView tvName = findViewById(R.id.tvDetailName);
        TextView tvDesc = findViewById(R.id.tvDetailDescription);
        EditText etPhone = findViewById(R.id.etMemberPhone);
        Button btnAdd = findViewById(R.id.btnAddMember);
        RecyclerView rvMembers = findViewById(R.id.rvMembers);

        tvName.setText(name);
        tvDesc.setText(desc);

        memberList = dbHelper.getCommunityMembers(communityId);
        adapter = new MemberAdapter(memberList);
        rvMembers.setLayoutManager(new LinearLayoutManager(this));
        rvMembers.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            String phone = etPhone.getText().toString();
            if (!phone.isEmpty()) {
                dbHelper.addMemberToCommunity(communityId, phone);
                memberList.clear();
                memberList.addAll(dbHelper.getCommunityMembers(communityId));
                adapter.notifyDataSetChanged();
                etPhone.setText("");
                Toast.makeText(this, "Miembro invitado", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
