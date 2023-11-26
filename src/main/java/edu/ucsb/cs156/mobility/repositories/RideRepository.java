package edu.ucsb.cs156.mobility.repositories;

import edu.ucsb.cs156.mobility.entities.Ride;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RideRepository extends CrudRepository<Ride, Long> {
  Iterable<Ride> findAllByRiderId(long riderId);
  Optional<Ride> findByIdAndRiderId(long id, long riderId);
}
