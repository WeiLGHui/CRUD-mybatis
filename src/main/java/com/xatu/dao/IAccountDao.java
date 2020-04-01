package com.xatu.dao;

/**
 * @Auther WeiLGHui
 * @Date 2020-03-07 15:03
 */

import com.xatu.domain.Account;

import java.util.List;

/**
 * 账户的持久层接口
 */
public interface IAccountDao {
    /**
     * 查询所有
     * @return
     */
    List<Account> findAllAccount();

    /**
     * 查询一个
     * @return
     */
    Account findById(Integer accountId);

    /**
     * 插入数据
     * @param account
     */
    void saveAccount(Account account);

    /**
     * 更新
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 删除
     * @param accountId
     */
    void deleteAccount(Integer accountId);

    /**
     * 通过姓名查询
     * @return
     */
    Account findByName(String accountName);
}
