package com.example.mascotaszone.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.example.mascotaszone.AddContactActivity;
import com.example.mascotaszone.R;
import com.example.mascotaszone.db.DatabaseHelper;
import com.example.mascotaszone.models.Contact;
import java.util.List;

public class ContactsFragment extends Fragment {

    private ListView listView;
    private DatabaseHelper db;
    private List<Contact> contacts;
    private ContactAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_contact_list, container, false);

        db = new DatabaseHelper(getContext());
        listView = view.findViewById(R.id.listViewContacts);
        
        view.findViewById(R.id.fabAdd).setOnClickListener(v -> 
            startActivity(new Intent(getActivity(), AddContactActivity.class)));

        refreshList();
        return view;
    }

    private void refreshList() {
        contacts = db.getAllContacts();
        adapter = new ContactAdapter();
        listView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshList();
    }

    class ContactAdapter extends BaseAdapter {
        @Override
        public int getCount() { return contacts.size(); }
        @Override
        public Object getItem(int position) { return contacts.get(position); }
        @Override
        public long getItemId(int position) { return contacts.get(position).getId(); }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_contact, parent, false);
            }
            Contact c = contacts.get(position);
            TextView name = convertView.findViewById(R.id.tvName);
            TextView phone = convertView.findViewById(R.id.tvPhone);
            ImageView img = convertView.findViewById(R.id.ivContact);

            name.setText(c.getName());
            phone.setText(c.getPhone());
            
            if (c.getImageUri() != null && !c.getImageUri().isEmpty()) {
                img.setImageURI(Uri.parse(c.getImageUri()));
            } else {
                img.setImageResource(android.R.drawable.ic_menu_camera);
            }

            convertView.setOnClickListener(v -> showOptions(c));
            return convertView;
        }
    }

    private void showOptions(Contact c) {
        String[] options = {"Llamar", "Compartir", "Eliminar", "Actualizar"};
        new AlertDialog.Builder(getContext())
                .setTitle("Opciones para " + c.getName())
                .setItems(options, (dialog, which) -> {
                    switch (which) {
                        case 0: // Llamar
                            Intent callIntent = new Intent(Intent.ACTION_DIAL);
                            callIntent.setData(Uri.parse("tel:" + c.getPhone()));
                            startActivity(callIntent);
                            break;
                        case 1: // Compartir
                            Intent share = new Intent(Intent.ACTION_SEND);
                            share.setType("text/plain");
                            share.putExtra(Intent.EXTRA_TEXT, "Contacto: " + c.getName() + " - Tel: " + c.getPhone());
                            startActivity(Intent.createChooser(share, "Compartir vía"));
                            break;
                        case 2: // Eliminar
                            db.deleteContact(c.getId());
                            refreshList();
                            break;
                        case 3: // Actualizar
                            // Implementar lógica de actualización si se desea
                            break;
                    }
                }).show();
    }
}
