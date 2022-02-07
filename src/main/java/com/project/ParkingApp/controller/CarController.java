package com.project.ParkingApp.controller;


import com.project.ParkingApp.model.Car;
import com.project.ParkingApp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @PostMapping("/car/save")
    public Car saveCar(@RequestBody Car car){
        return carRepository.save(car);
    }


    @GetMapping("/cars/all")
    public List<Car> getAllCars(){

        return carRepository.findAll();
    }

    @GetMapping("/car/get/{id}")
    public Car getCarById(@PathVariable Long id){
        return carRepository.findById(id).get();
    }

    @PostMapping("/car/update")
    public Car updateCarById(@RequestBody Car car){

        return carRepository.save(car);
    }

    @GetMapping("/car/delete/{id}")
    public ResponseEntity<String> deleteCarById(@PathVariable Long id){
        carRepository.deleteById(id);
        return new ResponseEntity<>("Car Deleted", HttpStatus.OK);
    }
}
