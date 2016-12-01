package com.yaofei.admin.manager.account.service;

import com.yaofei.admin.manager.account.domain.Account;

/**
 * Created by fei.yao on 2016/10/19.
 */
public interface AccountService {
    Account findByLoginName(String currentLoginName);
}
