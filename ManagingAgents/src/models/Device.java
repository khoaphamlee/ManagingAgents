package models; 

public class Device {
    private String id;
    private String name;
    private String ipAddress;
    private String owner;
    private Status status;

    public Device(String id, String name, String ipAddress, String owner, Status status) {
        this.id = id;
        this.name = name;
        this.ipAddress = ipAddress;
        this.owner = owner;
        this.status = status;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public enum Status {
        ONLINE,
        OFFLINE
    }
}
