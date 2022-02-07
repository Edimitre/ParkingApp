package com.project.ParkingApp.controller;


import com.project.ParkingApp.model.ParkingSpot;
import com.project.ParkingApp.repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParkingController {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @PostMapping("/parking/save")
    private ParkingSpot addParkingSpot(@RequestBody ParkingSpot parkingSpot){

        return parkingSpotRepository.save(parkingSpot);

    }

    @GetMapping("/parking/all")
    public List<ParkingSpot> getAllParkingSpots(){

        return parkingSpotRepository.findAll();
    }

    @GetMapping("/parking/all/free")
    public List<ParkingSpot> getAllFreeParkingSpots(){
        return parkingSpotRepository.findAllActiveSpots();
    }

    @GetMapping("/parking/all/reserved")
    public List<ParkingSpot> getAllReservedParkingSpots(){
        return parkingSpotRepository.findAllReservedSpots();
    }


}
