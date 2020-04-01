package com.xatu.test;

/**
 * @Auther WeiLGHui
 * @Date 2020-03-07 18:34
 */

import com.xatu.dao.IAccountDao;
import com.xatu.domain.Account;
import com.xatu.service.IAccountService;
import config.springConfiguration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 使用Junit进行单元测试，测试配置
 */
public class AccountServiceTest {

    @Test
    public void testFindAll(){
        //获取容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(springConfiguration.class);
        //2.得到业务层对象
        IAccountService as= (IAccountService) ac.getBean("accountService");
        List<Account> accounts = as.findAllAccount();
        for (Account account:
             accounts) {
            System.out.println(account);
        }
    }
    @Test
    public void testFindOne(){
        //获取容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(springConfiguration.class);
        //2.得到业务层对象
        IAccountService as= (IAccountService) ac.getBean("accountService");
        Account account = as.findById(2);
        System.out.println(account);
    }
    @Test
    public void testSave(){
        Account account=new Account();
        //获取容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(springConfiguration.class);
        //2.得到业务层对象
        IAccountService as= (IAccountService) ac.getBean("accountService");
        account.setName("ddd");
        account.setMoney(1000);
        as.saveAccount(account);
    }
    @Test
    public void testUpdate(){
        //获取容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(springConfiguration.class);
        //2.得到业务层对象
        IAccountService as= (IAccountService) ac.getBean("accountService");
        Account account1 = as.findById(2);
        account1.setMoney(1000);
        as.updateAccount(account1);
    }
    @Test
    public void testDelete(){
        //获取容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(springConfiguration.class);
        //2.得到业务层对象
        IAccountService as= (IAccountService) ac.getBean("accountService");
        as.deleteAccount(6);
    }

    @Test
    public void testTransfer(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(springConfiguration.class);
        //2.得到业务层对象
        IAccountService as= (IAccountService) ac.getBean("accountService");

        as.transfer("aaa","bbb",500);
    }
}
