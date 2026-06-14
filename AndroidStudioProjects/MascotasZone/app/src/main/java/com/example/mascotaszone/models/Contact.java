package com.example.mascotaszone.models;

public class Contact {
    private int id;
    private String name;
    private String country;
    private String phone;
    private String note;
    private String imageUri;

    public Contact() {}

    public Contact(int id, String name, String country, String phone, String note, String imageUri) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.phone = phone;
        this.note = note;
        this.imageUri = imageUri;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public String getImageUri() { return imageUri; }
    public void setImageUri(String imageUri) { this.imageUri = imageUri; }
}
