package net.java.cargotracker.interfaces.booking.facade.internal.assembler;

import java.util.ArrayList;
import java.util.List;
import net.java.cargotracker.domain.model.location.Location;

public class LocationDtoAssembler {

	public net.java.cargotracker.interfaces.booking.facade.dto.Location toDto(
			Location location) {
		return new net.java.cargotracker.interfaces.booking.facade.dto.Location(
				location.getUnLocode().getIdString(), location.getName());
	}

	public List<net.java.cargotracker.interfaces.booking.facade.dto.Location> toDtoList(
			List<Location> allLocations) {
		List<net.java.cargotracker.interfaces.booking.facade.dto.Location> dtoList = new ArrayList<>(
				allLocations.size());

		for (Location location : allLocations) {
			dtoList.add(toDto(location));
		}

		return dtoList;
	}
}
