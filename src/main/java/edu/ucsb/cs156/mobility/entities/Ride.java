package edu.ucsb.cs156.mobility.entities;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.GeneratedValue;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "ride")
public class Ride {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private long riderId;
  private String student;

  @Schema(allowableValues = "Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday")
  private String day;
  
  private String startTime; // format: HH:MM(A/P)M e.g. "11:00AM" or "1:37PM"
  private String endTime; // format: HH:MM(A/P)M e.g. "11:00AM" or "1:37PM"

  private String pickupBuilding;
  private String dropoffBuilding;
  
  private String dropoffRoom;
  private String pickupRoom;
  private String notes;
  private String course; // e.g. CMPSC 156
}