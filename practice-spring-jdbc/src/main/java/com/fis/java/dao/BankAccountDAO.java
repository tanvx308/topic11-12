package com.fis.java.dao;

import com.fis.java.exception.BankTransactionException;
import com.fis.java.mapper.BankAccountMapper;
import com.fis.java.model.BankAccountInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
@Slf4j
public class BankAccountDAO extends JdbcDaoSupport {
    @Autowired
    public BankAccountDAO(DataSource dataSource){
        this.setDataSource(dataSource);
    }

    public List<BankAccountInfo> getBankAccounts(){
        String sql = BankAccountMapper.BASE_SQL;
        Object params[] = new Object[]{};
        BankAccountMapper bankAccountMapper = new BankAccountMapper();
        List<BankAccountInfo> list = this.getJdbcTemplate().query(sql, params, bankAccountMapper);
        return list;
    }

    public BankAccountInfo findBankAccount(Long id){
        String sql = BankAccountMapper.BASE_SQL + " where ba.id = ?";
        Object[] params = new Object[]{id};
        BankAccountMapper mapper = new BankAccountMapper();
        try {
            BankAccountInfo bankAccountInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return bankAccountInfo;
        }catch (EmptyResultDataAccessException e){
            log.error(e.getMessage());
            return null;
        }
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void addAmount(Long id, double amount) throws BankTransactionException{
        BankAccountInfo bankAccountInfo = findBankAccount(id);
            if(bankAccountInfo == null){
                throw new BankTransactionException("Account not found " + id);
            }
            double newBalance = bankAccountInfo.getBalance() + amount;
            if(bankAccountInfo.getBalance() + amount < 0){
                throw new BankTransactionException(
                        "The money in the account '" + id + "' is not enough (" + bankAccountInfo.getBalance() + ")");
            }

            bankAccountInfo.setBalance(newBalance);

            String sql = "Update Bank_Account set Balance = ? where Id = ?";
            this.getJdbcTemplate().update(sql, bankAccountInfo.getBalance(), bankAccountInfo.getId());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankTransactionException.class)
    public void sendMoney(Long fromAccountId, Long toAccountId, double amount) throws BankTransactionException {
        addAmount(toAccountId, amount);
        addAmount(fromAccountId, -amount);
    }
}
