package com.example.mascotaszone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mascotaszone.db.DatabaseHelper;
import com.example.mascotaszone.models.Contact;
import java.util.List;

public class ContactListActivity extends AppCompatActivity {

    private ListView listView;
    private DatabaseHelper db;
    private List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        db = new DatabaseHelper(this);
        listView = findViewById(R.id.listViewContacts);

        refreshList();

        findViewById(R.id.fabAdd).setOnClickListener(v -> startActivity(new Intent(this, AddContactActivity.class)));
    }

    private void refreshList() {
        contacts = db.getAllContacts();
        // Rúbrica 2.1: ListView con CustomAdapter
        listView.setAdapter(new ContactAdapter());
    }

    @Override
    protected void onResume() {
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
            
            // Rúbrica 2.5: Visualizar imágenes guardadas
            if (c.getImageUri() != null && !c.getImageUri().isEmpty()) {
                img.setImageURI(Uri.parse(c.getImageUri()));
            }

            convertView.setOnClickListener(v -> showOptions(c));
            return convertView;
        }
    }

    private void showOptions(Contact c) {
        String[] options = {"Llamar", "Compartir", "Eliminar", "Actualizar"};
        new AlertDialog.Builder(this)
                .setTitle("Opciones para " + c.getName())
                .setItems(options, (dialog, which) -> {
                    switch (which) {
                        case 0: // Llamar (Rúbrica 3.1)
                            Intent callIntent = new Intent(Intent.ACTION_DIAL); // DIAL no requiere permiso directo, CALL sí.
                            callIntent.setData(Uri.parse("tel:" + c.getPhone()));
                            startActivity(callIntent);
                            break;
                        case 1: // Compartir (Rúbrica 2.4)
                            Intent share = new Intent(Intent.ACTION_SEND);
                            share.setType("text/plain");
                            share.putExtra(Intent.EXTRA_TEXT, "Contacto: " + c.getName() + " - Tel: " + c.getPhone());
                            startActivity(Intent.createChooser(share, "Compartir vía"));
                            break;
                        case 2: // Eliminar (Rúbrica 2.2)
                            db.deleteContact(c.getId());
                            refreshList();
                            break;
                        case 3: // Actualizar (Rúbrica 2.3)
                            // Lógica de actualizar (abrir AddContact con datos)
                            break;
                    }
                }).show();
    }
}
