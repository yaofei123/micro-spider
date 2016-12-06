package com.yaofei.admin.zhihu.service;


import com.yaofei.admin.zhihu.entity.Answer;
import com.yaofei.admin.zhihu.entity.Question;
import com.yaofei.admin.zhihu.entity.ZhihuData;
import com.yaofei.framework.util.Pagination;

import java.util.List;

/**
 * Created by fei.yao on 2016/10/18.
 */
public interface ZhihuService {

    Integer insertQuestion(Question question) throws Exception;

    Integer insertAnswer(List<Answer> answerList) throws Exception;

    List<ZhihuData> selectZhihuData(Pagination pagination) throws Exception;

    List<Question> selectZhihuQ(Pagination pagination,String question,String questionDetail) throws Exception;

    List<Answer> selectZhihuA(Pagination pagination,String linkedQuestion) throws Exception;


}
