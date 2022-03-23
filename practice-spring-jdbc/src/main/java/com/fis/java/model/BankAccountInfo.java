package com.fis.java.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankAccountInfo {
    private Long id;
    private String fullName;
    private double balance;
}
