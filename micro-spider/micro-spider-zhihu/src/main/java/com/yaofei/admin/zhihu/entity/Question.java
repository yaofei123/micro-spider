package com.yaofei.admin.zhihu.entity;

import java.io.Serializable;

/**
 * Created by fei.yao on 2016/10/17.
 */
public class Question implements Serializable{
    private String id;
    private String question;
    private String questionDetail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionDetail() {
        return questionDetail;
    }

    public void setQuestionDetail(String questionDetail) {
        this.questionDetail = questionDetail;
    }
}
