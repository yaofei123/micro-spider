package com.yaofei.admin.zhihu.dao;

import com.yaofei.admin.zhihu.entity.Answer;
import com.yaofei.admin.zhihu.entity.Question;
import com.yaofei.admin.zhihu.entity.ZhihuData;
import com.yaofei.framework.util.MybatisMapper;
import com.yaofei.framework.util.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by fei.yao on 2016/10/18.
 */
@Mapper
public interface ZhihuDao extends MybatisMapper {

    Integer insertQuestion(@Param("question") Question question) throws Exception;

    Integer insertAnswer(@Param("answerList") List<Answer> answerList) throws Exception;

    List<ZhihuData> listPageZhihuData(@Param("pagination") Pagination pagination) throws Exception;

    List<Question> listPageZhihuQ(@Param("pagination") Pagination pagination,@Param("question") String question,@Param("questionDetail") String questionDetail) throws Exception;


    List<Answer> nolistPageZhihuA(@Param("pagination") Pagination pagination, @Param("linkedQuestion") String linkedQuestion) throws Exception;
}
