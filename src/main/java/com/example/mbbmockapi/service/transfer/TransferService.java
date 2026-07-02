package com.example.mbbmockapi.service.transfer;

import java.math.BigDecimal;

public interface TransferService {
    void executeTransfer(String accountNumberFrom, String accountNumberTo, BigDecimal amount);
}
