package com.ewallet.mywallet.userdetails;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@RequestMapping("/ewallet")
public class UserController {

    @Autowired
    private IUserService iUserService;


    @GetMapping("/username/{userName}")
    private Mono<UserDetailsVo> findByUsername(@PathVariable String userName) {
        return iUserService.findByUsername(userName);
    }

    @GetMapping("/mobileno/{mobileNo}")
    private Mono<UserDetailsVo> findByMobileNo(@PathVariable String mobileNo) {

        return iUserService.findByMobileNo(mobileNo);
    }

    @GetMapping("/login/{userName}/{password}")
    private Mono<UserDetailsVo> findLoginDetails(@PathVariable String userName, @PathVariable String password) {
        return iUserService.findLoginDetails(userName, password);
    }


    @NonNull
    @PostMapping
    public Mono<UserDetailsVo> signUp(@RequestBody UserDetailsVo userDetailsVo) {
        return iUserService.signUp(userDetailsVo);
    }

    @PutMapping("/updateuserinfo/{userName}")
    public Mono<UserDetailsVo> updateUserInfo(@PathVariable("userName")String userName, @RequestBody UserDetailsVo userDetailsVo) {
        return iUserService.updateUserInfo(userName,userDetailsVo);
    }

    @PutMapping("/addbalance/{userName}/{amount}")
    public Mono<UserDetailsVo> addBalance(@PathVariable("userName") String userName, @PathVariable BigDecimal amount) {
        return iUserService.addBalance(userName, amount);
    }

    @PutMapping("/withdrawbalance/{userName}/{amount}")
    public Mono<UserDetailsVo> withdrawBalance(@PathVariable("userName") String userName, @PathVariable BigDecimal amount) {
        return iUserService.withdrawBalance(userName, amount);
    }

    @PutMapping("/transfermoney/{from}/{to}/{amount}")
    public Mono<UserDetailsVo> transferBalance(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal amount) {
        return iUserService.transferBalance(from, to, amount);
    }
}
