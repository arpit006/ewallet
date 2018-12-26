package com.ewallet.mywallet.userdetails;

import com.ewallet.mywallet.common.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import lombok.experimental.Wither;

import java.math.BigDecimal;

@Value
@Wither
@Builder(toBuilder = true)
@JsonDeserialize(builder = UserDetailsVo.UserDetailsVoBuilder.class)
public class UserDetailsVo implements BaseVo<UserDetailsVo> {

    private String uuid;

    private String name;

    private String userName;

    private String mobileNo;

    private String email;

    private BigDecimal walletBalance;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
