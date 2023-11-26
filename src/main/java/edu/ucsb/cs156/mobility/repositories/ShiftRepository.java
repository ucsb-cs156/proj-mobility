package edu.ucsb.cs156.mobility.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.ucsb.cs156.mobility.entities.Shift;

import java.util.Optional;

@Repository
public interface ShiftRepository extends CrudRepository<Shift, Long> {
  Optional<Shift> findByDay(String day);
  Optional<Shift> findByDriverID(Long driverID);
}
