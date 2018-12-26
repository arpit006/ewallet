package com.ewallet.mywallet.userdetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, String> {

    UserDetails findByUserName(String username);

    UserDetails findByMobileNo(String mobileno);
}
