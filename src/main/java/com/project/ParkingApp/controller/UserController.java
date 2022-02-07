package com.project.ParkingApp.controller;


import com.project.ParkingApp.model.ParkingSpot;
import com.project.ParkingApp.model.User;
import com.project.ParkingApp.repository.CarRepository;
import com.project.ParkingApp.repository.ParkingSpotRepository;
import com.project.ParkingApp.repository.ReceiptRepository;
import com.project.ParkingApp.repository.UserRepository;
import com.project.ParkingApp.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private CarRepository carRepository;

    @PostMapping("/user/save")
    public User addUser(@RequestBody User user){

        return userRepository.save(user);
    }

    @PostMapping("/user/update")
    public User updateUser(@RequestBody User user){

        return userRepository.save(user);
    }

    @PostMapping("/user/addparking/{userId}/{parkingId}")
    public User addUserParking(@PathVariable Long userId, @PathVariable Long parkingId){

        User user = userRepository.findById(userId).get();

        ParkingSpot parkingSpot = parkingSpotRepository.getById(parkingId);


        parkingSpot.setUser(user);
        parkingSpot.setIsTaken(true);
        parkingSpot.setBookedAt(DateUtils.getCurrentDateString());
        parkingSpot.setBookedTimeInMillis(DateUtils.getCurrentTimeInMillis());


        user.getParkingSpotList().add(parkingSpot);

        return userRepository.save(user);
    }

    @PostMapping("/user/removeparking/{userId}/{parkingId}")
    public User removeUserParking(@PathVariable Long userId, @PathVariable Long parkingId){

        User user = userRepository.findById(userId).get();

        ParkingSpot parkingSpot = parkingSpotRepository.getById(parkingId);

        user.getParkingSpotList().remove(parkingSpot);

        parkingSpot.setBookedAt(null);
        parkingSpot.setIsTaken(false);
        parkingSpot.setUser(null);
        parkingSpot.setBookedTimeInMillis(null);


        parkingSpotRepository.save(parkingSpot);
        return userRepository.save(user);
    }



    @GetMapping("/users/all")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/get/{id}")
    public User getUserById(@PathVariable Long id){
        return userRepository.findById(id).get();
    }

    @GetMapping("/user/get/pass/{password}/{name}")
    public User getUserByPassword(@PathVariable String password, @PathVariable String name){
        return userRepository.getUserByPassword(password, name);
    }

    @GetMapping("/user/delete/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable Long id){
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
