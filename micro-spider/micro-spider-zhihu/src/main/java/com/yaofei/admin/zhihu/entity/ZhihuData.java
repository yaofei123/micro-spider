package com.yaofei.admin.zhihu.entity;

import java.util.List;

/**
 * Created by fei.yao on 2016/10/19.
 */
public class ZhihuData {
    private Question question;
    private List<Answer> answerList;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }
}
