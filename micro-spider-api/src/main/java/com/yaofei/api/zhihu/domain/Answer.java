package com.yaofei.api.zhihu.domain;

import java.io.Serializable;

/**
 * Created by fei.yao on 2016/10/17.
 */
public class Answer implements Serializable {
    private String id;
    private String answerer;
    private Integer vote;
    private String answerDetail;
    private String linkedQuestion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswerer() {
        return answerer;
    }

    public void setAnswerer(String answerer) {
        this.answerer = answerer;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    public String getAnswerDetail() {
        return answerDetail;
    }

    public void setAnswerDetail(String answerDetail) {
        this.answerDetail = answerDetail;
    }

    public String getLinkedQuestion() {
        return linkedQuestion;
    }

    public void setLinkedQuestion(String linkedQuestion) {
        this.linkedQuestion = linkedQuestion;
    }
}
