package com.project.ParkingApp.repository;

import com.project.ParkingApp.model.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

    @Query("SELECT p FROM ParkingSpot p WHERE p.isTaken = 0")
    List<ParkingSpot> findAllActiveSpots();

    @Query("SELECT p FROM ParkingSpot p WHERE p.isTaken = 1")
    List<ParkingSpot> findAllReservedSpots();



}
