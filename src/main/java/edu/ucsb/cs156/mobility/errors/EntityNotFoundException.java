package edu.ucsb.cs156.mobility.errors;

public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(Class<?> entityType, Object id) {
    super("%s with id %s not found"
      .formatted(entityType.getSimpleName(), id.toString()));
  }
}
