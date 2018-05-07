package model;

/**
 * Created by denny on 29/04/18.
 */

public class DBStatus {
    private boolean status;
    private String description;

    public DBStatus() {}

    public boolean isStatusTrue() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
