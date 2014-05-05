package net.java.cargotracker.infrastructure.persistence.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.java.cargotracker.domain.model.cargo.Cargo;
import net.java.cargotracker.domain.model.cargo.CargoRepository;
import net.java.cargotracker.domain.model.cargo.Leg;
import net.java.cargotracker.domain.model.cargo.TrackingId;

@ApplicationScoped
public class JpaCargoRepository implements CargoRepository, Serializable {

    private static final long serialVersionUID = 1L;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Cargo find(TrackingId trackingId) {
        return entityManager.createNamedQuery("Cargo.findByTrackingId",
                Cargo.class).setParameter("trackingId", trackingId)
                .getSingleResult();
    }

    @Override
    public void store(Cargo cargo) {
        // TODO See why cascade is not working correctly for legs.
        for (Leg leg : cargo.getItinerary().getLegs()) {
            entityManager.persist(leg);
        }

        entityManager.persist(cargo);
    }

    @Override
    public TrackingId nextTrackingId() {
        String random = UUID.randomUUID().toString().toUpperCase();

        return new TrackingId(random.substring(0, random.indexOf("-")));
    }

    @Override
    public List<Cargo> findAll() {
        return entityManager.createNamedQuery("Cargo.findAll", Cargo.class)
                .getResultList();
    }
}
