package com.project.ParkingApp.repository;

import com.project.ParkingApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE password = :password and name =:name")
    User getUserByPassword(@Param(value="password") String password, @Param(value = "name") String name);


    @Query("SELECT u FROM User u WHERE id = :id")
    User getUserById(@Param(value = "id") Long id);







//    @Modifying
//    @Transactional
//    @Query("update User u set u.parkingSpotList = ?1, where u.id = ?2")
//    User setUserInfoById(List<ParkingSpot> parkingSpotList, Long id);

//    @Modifying
//    @Transactional
//    @Query("update User u set u.parkingSpotList = :parkingSpotList, where u.id = :id")
//    void updateUserParkingSpot(@Param(value="parkingSpotList") List<ParkingSpot> parkingSpotList,@Param(value="id") Long id);


}
