package com.yaofei.admin.manager.account.service.impl;

import com.yaofei.admin.manager.account.dao.AccountDao;
import com.yaofei.admin.manager.account.domain.Account;
import com.yaofei.admin.manager.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fei.yao on 2016/10/19.
 */
@Service(value = "accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountDao;

    @Override
    public Account findByLoginName(String currentLoginName) {
        return accountDao.findByLoginName(currentLoginName);
    }
}
