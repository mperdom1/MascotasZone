package com.example.mascotaszone.models;

public class Pet {
    private String id;
    private String name;
    private String breed;
    private int age;
    private String ownerId;
    private String imageUri;

    public Pet() {}

    public Pet(String id, String name, String breed, int age, String ownerId) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.ownerId = ownerId;
    }

    public String getImageUri() { return imageUri; }
    public void setImageUri(String imageUri) { this.imageUri = imageUri; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getOwnerId() { return ownerId; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }
}
