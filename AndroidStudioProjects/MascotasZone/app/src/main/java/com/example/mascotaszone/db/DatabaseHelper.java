package com.example.mascotaszone.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.mascotaszone.models.Community;
import com.example.mascotaszone.models.Pet;
import com.example.mascotaszone.models.ServiceRequest;
import com.example.mascotaszone.models.Contact;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MascotasZone.db";
    private static final int DATABASE_VERSION = 5;

    private static final String TABLE_PETS = "pets";
    private static final String TABLE_COMMUNITIES = "communities";
    private static final String TABLE_SERVICE_REQUESTS = "service_requests";
    private static final String TABLE_MEMBERS = "community_members";
    private static final String TABLE_CONTACTS = "contacts";

    private static final String COLUMN_ID = "id";

    // Contacts columns
    private static final String COLUMN_CONTACT_NAME = "name";
    private static final String COLUMN_CONTACT_COUNTRY = "country";
    private static final String COLUMN_CONTACT_PHONE = "phone";
    private static final String COLUMN_CONTACT_NOTE = "note";
    private static final String COLUMN_CONTACT_IMAGE = "image";

    // Community Members columns
    private static final String COLUMN_MEMBER_COMM_ID = "community_id";
    private static final String COLUMN_MEMBER_PHONE = "phone_number";

    // Pets columns
    private static final String COLUMN_PET_NAME = "name_pet";
    private static final String COLUMN_PET_BREED = "breed";
    private static final String COLUMN_PET_AGE = "age";
    private static final String COLUMN_PET_IMAGE = "image_uri";

    // Communities columns
    private static final String COLUMN_COMM_NAME = "name_comm";
    private static final String COLUMN_COMM_DESC = "description";

    // Service Requests columns
    private static final String COLUMN_REQ_TYPE = "type";
    private static final String COLUMN_REQ_DESC = "description_req";
    private static final String COLUMN_REQ_TIMESTAMP = "timestamp";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PETS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PET_NAME + " TEXT," + COLUMN_PET_BREED + " TEXT," + COLUMN_PET_AGE + " INTEGER," + COLUMN_PET_IMAGE + " TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_COMMUNITIES + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_COMM_NAME + " TEXT," + COLUMN_COMM_DESC + " TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_SERVICE_REQUESTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_REQ_TYPE + " TEXT," + COLUMN_REQ_DESC + " TEXT," + COLUMN_REQ_TIMESTAMP + " LONG)");
        db.execSQL("CREATE TABLE " + TABLE_MEMBERS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_MEMBER_COMM_ID + " INTEGER," + COLUMN_MEMBER_PHONE + " TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_CONTACTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CONTACT_NAME + " TEXT," + COLUMN_CONTACT_COUNTRY + " TEXT," + COLUMN_CONTACT_PHONE + " TEXT," + COLUMN_CONTACT_NOTE + " TEXT," + COLUMN_CONTACT_IMAGE + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PETS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMUNITIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE_REQUESTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    // --- CRUD CONTACTS ---
    public long addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(COLUMN_CONTACT_NAME, contact.getName());
        v.put(COLUMN_CONTACT_COUNTRY, contact.getCountry());
        v.put(COLUMN_CONTACT_PHONE, contact.getPhone());
        v.put(COLUMN_CONTACT_NOTE, contact.getNote());
        v.put(COLUMN_CONTACT_IMAGE, contact.getImageUri());
        long id = db.insert(TABLE_CONTACTS, null, v);
        db.close();
        return id;
    }

    public List<Contact> getAllContacts() {
        List<Contact> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS, null);
        if (c.moveToFirst()) {
            do {
                Contact con = new Contact(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5));
                list.add(con);
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    public void deleteContact(int id) {
        this.getWritableDatabase().delete(TABLE_CONTACTS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public void updateContact(Contact contact) {
        ContentValues v = new ContentValues();
        v.put(COLUMN_CONTACT_NAME, contact.getName());
        v.put(COLUMN_CONTACT_COUNTRY, contact.getCountry());
        v.put(COLUMN_CONTACT_PHONE, contact.getPhone());
        v.put(COLUMN_CONTACT_NOTE, contact.getNote());
        v.put(COLUMN_CONTACT_IMAGE, contact.getImageUri());
        this.getWritableDatabase().update(TABLE_CONTACTS, v, COLUMN_ID + " = ?", new String[]{String.valueOf(contact.getId())});
    }

    // --- CRUD PETS ---
    public void addPet(Pet pet) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(COLUMN_PET_NAME, pet.getName());
        v.put(COLUMN_PET_BREED, pet.getBreed());
        v.put(COLUMN_PET_AGE, pet.getAge());
        v.put(COLUMN_PET_IMAGE, pet.getImageUri());
        db.insert(TABLE_PETS, null, v);
        db.close();
    }

    public List<Pet> getAllPets() {
        List<Pet> list = new ArrayList<>();
        Cursor c = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_PETS, null);
        if (c.moveToFirst()) {
            do {
                Pet p = new Pet(String.valueOf(c.getInt(0)), c.getString(1), c.getString(2), c.getInt(3), "");
                p.setImageUri(c.getString(4));
                list.add(p);
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    // --- CRUD COMMUNITIES ---
    public void addCommunity(Community community) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(COLUMN_COMM_NAME, community.getName());
        v.put(COLUMN_COMM_DESC, community.getDescription());
        db.insert(TABLE_COMMUNITIES, null, v);
        db.close();
    }

    public List<Community> getAllCommunities() {
        List<Community> list = new ArrayList<>();
        Cursor c = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_COMMUNITIES, null);
        if (c.moveToFirst()) {
            do {
                Community comm = new Community(String.valueOf(c.getInt(0)), c.getString(1), c.getString(2));
                comm.setMemberIds(getCommunityMembers(comm.getId()));
                list.add(comm);
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    public void addMemberToCommunity(String commId, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(COLUMN_MEMBER_COMM_ID, commId);
        v.put(COLUMN_MEMBER_PHONE, phone);
        db.insert(TABLE_MEMBERS, null, v);
        db.close();
    }

    public List<String> getCommunityMembers(String commId) {
        List<String> list = new ArrayList<>();
        Cursor c = this.getReadableDatabase().rawQuery("SELECT " + COLUMN_MEMBER_PHONE + " FROM " + TABLE_MEMBERS + " WHERE " + COLUMN_MEMBER_COMM_ID + " = ?", new String[]{commId});
        if (c.moveToFirst()) {
            do { list.add(c.getString(0)); } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    // --- CRUD REQUESTS ---
    public void addServiceRequest(ServiceRequest r) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(COLUMN_REQ_TYPE, r.getType().name());
        v.put(COLUMN_REQ_DESC, r.getDescription());
        v.put(COLUMN_REQ_TIMESTAMP, r.getTimestamp());
        db.insert(TABLE_SERVICE_REQUESTS, null, v);
        db.close();
    }

    public List<ServiceRequest> getAllServiceRequests() {
        List<ServiceRequest> list = new ArrayList<>();
        Cursor c = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_SERVICE_REQUESTS + " ORDER BY " + COLUMN_REQ_TIMESTAMP + " DESC", null);
        if (c.moveToFirst()) {
            do {
                ServiceRequest r = new ServiceRequest(String.valueOf(c.getInt(0)), "", "", ServiceRequest.ServiceType.valueOf(c.getString(1)), c.getString(2));
                r.setTimestamp(c.getLong(3));
                list.add(r);
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }
}
