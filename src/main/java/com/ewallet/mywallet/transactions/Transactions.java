package com.ewallet.mywallet.transactions;

import com.ewallet.mywallet.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Transactions extends BaseEntity {
    private String sender;
    private String receiver;
    private BigDecimal amount;
    private String time;
}
