package com.example.mascotaszone.models;

public class ServiceRequest {
    public enum ServiceType { CARPOOL, BABYSITTING, SOCIALIZATION }

    private String id;
    private String requesterId;
    private String communityId;
    private ServiceType type;
    private String description;
    private long timestamp;

    public ServiceRequest() {}

    public ServiceRequest(String id, String requesterId, String communityId, ServiceType type, String description) {
        this.id = id;
        this.requesterId = requesterId;
        this.communityId = communityId;
        this.type = type;
        this.description = description;
        this.timestamp = System.currentTimeMillis();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getRequesterId() { return requesterId; }
    public void setRequesterId(String requesterId) { this.requesterId = requesterId; }
    public String getCommunityId() { return communityId; }
    public void setCommunityId(String communityId) { this.communityId = communityId; }
    public ServiceType getType() { return type; }
    public void setType(ServiceType type) { this.type = type; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}
