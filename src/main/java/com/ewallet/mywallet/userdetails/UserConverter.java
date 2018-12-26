package com.ewallet.mywallet.userdetails;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class UserConverter {
    /**
     * Encoding password with the BCrypt- genSalt and haspw technology.
     * To retrieve the password, we will match it with the plainTextPassword and the salt
     */

    public UserDetails convertVoToEntity(UserDetailsVo userDetailsVo) {
        /*String plainTextPassword = userDetailsVo.getPassword();
        String gensalt = BCrypt.gensalt();
        String hashed = BCrypt.hashpw(plainTextPassword, gensalt);*/
        UserDetails userDetails = new UserDetails();
        userDetails.setName(userDetailsVo.getName());
        userDetails.setMobileNo(userDetailsVo.getMobileNo());
        userDetails.setEmail(userDetailsVo.getEmail());
        userDetails.setWalletBalance(userDetailsVo.getWalletBalance() == null ? BigDecimal.ZERO : userDetailsVo.getWalletBalance());
        userDetails.setUuid(userDetailsVo.getUuid());
        userDetails.setUserName(userDetailsVo.getUserName());
        userDetails.setPassword(userDetailsVo.getPassword());
        return userDetails;
    }

    public UserDetailsVo convertEntityToVO(UserDetails userDetails) {
        return UserDetailsVo.builder()
                .uuid(userDetails.getUuid())
                .name(userDetails.getName())
                .mobileNo(userDetails.getMobileNo())
                .email(userDetails.getEmail())
                .walletBalance(userDetails.getWalletBalance())
                .userName(userDetails.getUserName())
                .password(userDetails.getPassword())
                .build();
    }

    /*public Mono<UserDetailsVo> updateEntityFromVo(UserDetails userDetails, UserDetailsVo userDetailsVo) {
        userDetails.setUserName(userDetailsVo.getUserName());
        userDetails.setName(userDetailsVo.getName());
        userDetails.setUuid(userDetailsVo.getUuid());
        userDetails.setWalletBalance(userDetailsVo.getWalletBalance());
        userDetails.setEmail(userDetailsVo.getEmail());
        userDetails.setMobileNo(userDetailsVo.getMobileNo());
        userDetails.setPassword(userDetailsVo.getPassword());
        return convertEntityToVO(userDetails);
    }*/

}
