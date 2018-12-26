package com.ewallet.mywallet.userdetails;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface IUserService {

    Mono<UserDetailsVo> signUp(UserDetailsVo userDetailsVo);

    Mono<UserDetailsVo> findByUsername(String userName);

    Mono<UserDetailsVo> updateUserInfo(String userName, UserDetailsVo userDetailsVo);

    Mono<UserDetailsVo> addBalance(String userName, BigDecimal amount);

    Mono<UserDetailsVo> withdrawBalance(String userName, BigDecimal amount);

    Mono<UserDetailsVo> transferBalance(String from, String to, BigDecimal amount);

    Mono<UserDetailsVo> findByMobileNo(String mobileno);

    Mono<UserDetailsVo> findLoginDetails(String userName, String password);
}
