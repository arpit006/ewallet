package com.ewallet.mywallet.transactions;

import com.ewallet.mywallet.common.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

import java.math.BigDecimal;

@Value
@Wither
@Builder(toBuilder = true)
@JsonDeserialize(builder = TransactionsVo.TransactionsVoBuilder.class)
public class TransactionsVo implements BaseVo<TransactionsVo> {
    private String uuid;
    private String sender;
    private String receiver;
    private BigDecimal amount;
    private String time;
}
