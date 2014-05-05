package net.java.cargotracker.interfaces.booking.facade.internal.assembler;

import java.util.ArrayList;
import java.util.List;
import net.java.cargotracker.domain.model.cargo.Itinerary;
import net.java.cargotracker.domain.model.cargo.Leg;
import net.java.cargotracker.domain.model.location.Location;
import net.java.cargotracker.domain.model.location.LocationRepository;
import net.java.cargotracker.domain.model.location.UnLocode;
import net.java.cargotracker.domain.model.voyage.Voyage;
import net.java.cargotracker.domain.model.voyage.VoyageNumber;
import net.java.cargotracker.domain.model.voyage.VoyageRepository;
import net.java.cargotracker.interfaces.booking.facade.dto.RouteCandidate;

public class ItineraryCandidateDtoAssembler {

	public RouteCandidate toDTO(Itinerary itinerary) {
		List<net.java.cargotracker.interfaces.booking.facade.dto.Leg> legDTOs = new ArrayList<>(
				itinerary.getLegs().size());
		for (Leg leg : itinerary.getLegs()) {
			legDTOs.add(toLegDTO(leg));
		}
		return new RouteCandidate(legDTOs);
	}

	protected net.java.cargotracker.interfaces.booking.facade.dto.Leg toLegDTO(
			Leg leg) {
		VoyageNumber voyageNumber = leg.getVoyage().getVoyageNumber();
		UnLocode from = leg.getLoadLocation().getUnLocode();
		UnLocode to = leg.getUnloadLocation().getUnLocode();
		return new net.java.cargotracker.interfaces.booking.facade.dto.Leg(
				voyageNumber.getIdString(), from.getIdString(),
				to.getIdString(), leg.getLoadTime(), leg.getUnloadTime());
	}

	public Itinerary fromDTO(RouteCandidate routeCandidateDTO,
			VoyageRepository voyageRepository,
			LocationRepository locationRepository) {
		List<Leg> legs = new ArrayList<>(routeCandidateDTO.getLegs().size());

		for (net.java.cargotracker.interfaces.booking.facade.dto.Leg legDTO : routeCandidateDTO
				.getLegs()) {
			VoyageNumber voyageNumber = new VoyageNumber(
					legDTO.getVoyageNumber());
			Voyage voyage = voyageRepository.find(voyageNumber);
			Location from = locationRepository.find(new UnLocode(legDTO
					.getFrom()));
			Location to = locationRepository.find(new UnLocode(legDTO.getTo()));
			legs.add(new Leg(voyage, from, to, legDTO.getLoadTime(), legDTO
					.getUnloadTime()));
		}

		return new Itinerary(legs);
	}
}