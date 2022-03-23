package com.fis.java.mapper;

import com.fis.java.model.BankAccountInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountMapper implements RowMapper<BankAccountInfo> {
    public static final String BASE_SQL
            = "Select ba.Id, ba.Full_Name, ba.Balance From Bank_Account ba ";
    @Override
    public BankAccountInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("ID");
        String fullname = rs.getString("FULL_NAME");
        double balance = rs.getDouble("BALANCE");
        return new BankAccountInfo(id, fullname, balance);
    }
}
