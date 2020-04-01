package com.xatu.service.impl;

import com.xatu.dao.IAccountDao;
import com.xatu.domain.Account;
import com.xatu.service.IAccountService;
import com.xatu.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther WeiLGHui
 * @Date 2020-03-07 15:01
 */

/**
 * 账户的业务层实现类
 * 事务控制都应该在业务层
 */
@Service("accountService")
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountDao accountDao;
    @Autowired
    private TransactionManager tx;
    public List<Account> findAllAccount() {

           return accountDao.findAllAccount();

    }

    public Account findById(Integer accountId) {

           return accountDao.findById(accountId);

    }

    public void saveAccount(Account account) {
            accountDao.saveAccount(account);
    }

    public void updateAccount(Account account) {
            accountDao.updateAccount(account);
    }

    public void deleteAccount(Integer accountId) {
            accountDao.deleteAccount(accountId);
    }

    /**
     * 这个方法产生了多次与数据库的交互，从而产生多个Connection连接，从而如果中途产生异常，会导致事务进行不完整，
     * 比如一个的money有变化一个没有变化
     * @param sourceName
     * @param targetName
     * @param money
     */
    public void transfer(String sourceName, String targetName, float money) {
        Account source = accountDao.findByName(sourceName);
        source.setMoney(source.getMoney() - money);
        accountDao.updateAccount(source);
        Account target = accountDao.findByName(targetName);
        target.setMoney(target.getMoney() + money);
        accountDao.updateAccount(target);
    }
}
