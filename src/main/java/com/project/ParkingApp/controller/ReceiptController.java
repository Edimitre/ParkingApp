package com.project.ParkingApp.controller;


import com.project.ParkingApp.model.ParkingSpot;
import com.project.ParkingApp.model.Receipt;
import com.project.ParkingApp.model.User;
import com.project.ParkingApp.repository.ParkingSpotRepository;
import com.project.ParkingApp.repository.ReceiptRepository;
import com.project.ParkingApp.repository.UserRepository;
import com.project.ParkingApp.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiptController {

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @PostMapping("/receipt/generate/{userId}/{parkingSpotId}")
    public Receipt generateReceipt(@PathVariable Long userId, @PathVariable Long parkingSpotId){

        // gjej user
        User user = userRepository.findById(userId).get();
        // gjej parkingSpot
        ParkingSpot parkingSpot = parkingSpotRepository.findById(parkingSpotId).get();
        // cmimi 1000 lek te vjetra ora
        double price = 100.0 / 60;
        // krijo fature
        Receipt receipt = new Receipt();
        // fshij parkingun nga user
        user.getParkingSpotList().remove(parkingSpot);

        receipt.setBookedTimeInMillis(parkingSpot.getBookedTimeInMillis());
        receipt.setUser(user);
        receipt.setReleasedTimeInMillis(DateUtils.getCurrentTimeInMillis());
        // nxir diferencen kohore
        long stayedTime = receipt.getReleasedTimeInMillis() - receipt.getBookedTimeInMillis();
        // gjenero minutat nga diferenca
        long minutes = (stayedTime / 1000) / 60;

        receipt.setMinutesStayed(minutes);

        int priceToPay  = (int) (minutes * price);
        receipt.setCost(priceToPay);
        // liro vendin e parkimit
        parkingSpot.setBookedAt(null);
        parkingSpot.setIsTaken(false);
        parkingSpot.setUser(null);
        parkingSpot.setBookedTimeInMillis(0L);

        // ruaj parking spot e liruar
        parkingSpotRepository.save(parkingSpot);
        // ruaj faturen e gjeneruar
        return receiptRepository.save(receipt);
    }
}
