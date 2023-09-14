package Class;

import java.util.Date;

public class TripLogEntry {
    private Port port;
    private Date date;

//    constructor,setter, getter

    public TripLogEntry(Port port, Date date) {
        this.port = port;
        this.date = date;
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
