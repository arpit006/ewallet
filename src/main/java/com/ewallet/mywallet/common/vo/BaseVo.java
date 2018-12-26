package com.ewallet.mywallet.common.vo;

public interface BaseVo<T> {

    T withUuid(String uuid);

    String getUuid();
}
