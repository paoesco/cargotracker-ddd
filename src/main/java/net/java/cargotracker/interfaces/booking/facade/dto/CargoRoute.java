package net.java.cargotracker.interfaces.booking.facade.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * DTO for registering and routing a cargo.
 */
public class CargoRoute implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String trackingId;
    private final String origin;
    private final String finalDestination;
    private final Date arrivalDeadline;
    private final boolean misrouted;
    private final List<Leg> legs;
    private final boolean claimed;
    private final String lastKnownLocation;
    private final String transportStatus;
    private String nextLocation;

    public CargoRoute(String trackingId, String origin, String finalDestination,
            Date arrivalDeadline, boolean misrouted, boolean claimed, String lastKnownLocation, String transportStatus) {
        this.trackingId = trackingId;
        this.origin = origin;
        this.finalDestination = finalDestination;
        this.arrivalDeadline = arrivalDeadline;
        this.misrouted = misrouted;
        this.claimed = claimed;
        this.lastKnownLocation = lastKnownLocation;
        this.transportStatus = transportStatus;
        this.legs = new ArrayList<>();
    }

    public String getTrackingId() {
        return trackingId;
    }

    public String getOrigin() {
        return origin;
    }

    public String getFinalDestination() {
        return finalDestination;
    }

    public void addLeg(String voyageNumber, String from, String to,
            Date loadTime, Date unloadTime) {
        legs.add(new Leg(voyageNumber, from, to, loadTime, unloadTime));
    }

    public List<Leg> getLegs() {
        return Collections.unmodifiableList(legs);
    }

    public boolean isMisrouted() {
        return misrouted;
    }

    public boolean isRouted() {
        return !legs.isEmpty();
    }

    public Date getArrivalDeadline() {
        return arrivalDeadline;
    }

    public boolean isClaimed() {
        return claimed;
    }

    public String getLastKnownLocation() {
        return this.lastKnownLocation;
    }

    public String getTransportStatus() {
        return this.transportStatus;
    }

    public String getNextLocation() {
        return this.nextLocation;
    }
}
