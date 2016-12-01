package com.yaofei.admin.manager.account.dao;

import com.yaofei.admin.manager.account.domain.Account;
import com.yaofei.framework.util.MybatisMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by fei.yao on 2016/10/19.
 */
@Mapper
public interface AccountDao extends MybatisMapper {
    Account findByLoginName(String currentLoginName);
}
