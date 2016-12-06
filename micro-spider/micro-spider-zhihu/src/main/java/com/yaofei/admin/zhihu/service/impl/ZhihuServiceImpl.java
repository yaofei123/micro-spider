package com.yaofei.admin.zhihu.service.impl;

import com.yaofei.admin.zhihu.dao.ZhihuDao;
import com.yaofei.admin.zhihu.entity.Answer;
import com.yaofei.admin.zhihu.entity.Question;
import com.yaofei.admin.zhihu.entity.ZhihuData;
import com.yaofei.admin.zhihu.service.ZhihuService;
import com.yaofei.framework.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fei.yao on 2016/10/18.
 */
@Service(value = "zhihuService")
public class ZhihuServiceImpl implements ZhihuService {

    @Autowired
    ZhihuDao zhihuDao;

    @Override
    public Integer insertQuestion(Question question) throws Exception {
        return zhihuDao.insertQuestion(question);
    }

    @Override
    public Integer insertAnswer(List<Answer> answerList) throws Exception {
        return zhihuDao.insertAnswer(answerList);
    }

    @Override
    public List<ZhihuData> selectZhihuData(Pagination pagination) throws Exception{
        return zhihuDao.listPageZhihuData(pagination);
    }

    @Override
    public List<Question> selectZhihuQ(Pagination pagination,String question,String questionDetail) throws Exception {
        return zhihuDao.listPageZhihuQ(pagination,question,questionDetail);
    }

    @Override
    public List<Answer> selectZhihuA(Pagination pagination,String linkedQuestion) throws Exception {
        return zhihuDao.nolistPageZhihuA(pagination,linkedQuestion);
    }
}
