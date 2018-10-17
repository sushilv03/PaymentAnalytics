package co.poynt.events.model;

public class EventInfo {

    String eventType;

    String status;

    public EventInfo(String eventType, String status) {
        this.eventType = eventType;
        this.status = status;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
