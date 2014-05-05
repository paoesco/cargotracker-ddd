package net.java.cargotracker.interfaces.booking.facade.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for a leg in an itinerary.
 */
public class Leg implements Serializable {

    private final String voyageNumber;
    private final String from;
    private final String to;
    private final Date loadTime;
    private final Date unloadTime;

    public Leg(String voyageNumber, String from, String to, Date loadTime,
            Date unloadTime) {
        this.voyageNumber = voyageNumber;
        this.from = from;
        this.to = to;
        this.loadTime = loadTime;
        this.unloadTime = unloadTime;
    }

    public String getVoyageNumber() {
        return voyageNumber;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Date getLoadTime() {
        return loadTime;
    }

    public Date getUnloadTime() {
        return unloadTime;
    }

    @Override
    public String toString() {
        return "Leg{" + "voyageNumber=" + voyageNumber + ", from=" + from + ", to=" + to + ", loadTime=" + loadTime + ", unloadTime=" + unloadTime + '}';
    }
}
