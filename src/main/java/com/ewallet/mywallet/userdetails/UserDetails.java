package com.ewallet.mywallet.userdetails;

import com.ewallet.mywallet.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
//@NamedQuery(name = "UserDetails.findByMobileNo", query = "select u from UserDetails where u.mobileNo = ?1")
public class UserDetails extends BaseEntity {

    /*@Size(min = 3, message = "Please enter a valid Name")
    @NotEmpty*/
    private String name;

    /*@Size(min = 4, message = "Please enter a valid User Name")
    @Column(unique = true)
    @NotEmpty*/
    private String userName;

    /*@Column(unique = true)
    @Digits(integer = 10, fraction = 0, message = "Give a valid mobile no.")*/
    private String mobileNo;

   /* @NotEmpty
    @Column(unique = true)
    @Email(message = "Please enter a valid email")*/
    private String email;

    @Column(columnDefinition = "default '0.00'")
    private BigDecimal walletBalance;

    private String password;

}
