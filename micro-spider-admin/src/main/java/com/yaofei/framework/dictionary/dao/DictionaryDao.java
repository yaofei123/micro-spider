package com.yaofei.framework.dictionary.dao;

import com.yaofei.framework.dictionary.entity.Dictionary;

import java.util.List;

/**
 * Created by fei.yao on 16/7/30.
 */
public interface DictionaryDao{

    List<Dictionary> findAll();
}
