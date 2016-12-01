package com.yaofei.spider.zhihu.pipeline;

import com.yaofei.admin.zhihu.entity.Answer;
import com.yaofei.admin.zhihu.entity.Question;
import com.yaofei.admin.zhihu.service.ZhihuService;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by fei.yao on 2016/10/18.
 */
@Service
@Repository
public class MysqlPipeline implements Pipeline {

    private static final Logger LOG = LoggerFactory.getLogger(MysqlPipeline.class);

    @Autowired
    ZhihuService zhihuService;


//    private String mysqlUrl = "jdbc:mysql://127.0.0.1:3306/micro-spider?useUnicode=true&characterEncoding=utf-8&autoReconnect=true";
//    private String mysqlUser = "yaofei";
//    private String mysqlPassword = "123456";

    public void process(ResultItems resultItems, Task task) {
        System.out.println("get page: " + resultItems.getRequest().getUrl());
        String questionName = "";
        String questionDetail = "";
        List<String> answererList = new ArrayList<>();
        List<String> voteList = new ArrayList<>();
        List<String> answerDetailList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            switch (entry.getKey()) {
                case "questionName":
                    questionName = (String) entry.getValue();
                    break;
                case "questionDetail":
                    questionDetail = (String) entry.getValue();
                    break;
                case "answererList":
                    answererList = (List<String>) entry.getValue();
                    break;
                case "voteList":
                    voteList = (List<String>) entry.getValue();
                    break;
                case "answerDetailList":
                    answerDetailList = (List<String>) entry.getValue();
                    break;
            }
//            System.out.println(entry.getKey() + ":\t" + entry.getValue());
        }
        Question question = newQuestion(questionName, questionDetail);
        List<Answer> answerList = newAnswer(question.getId(), answererList, voteList, answerDetailList);
        insert(question, answerList);
    }

    private Question newQuestion(String questionName, String questionDetail) {
        Question question = new Question();
        question.setId(UUID.randomUUID().toString());
        question.setQuestion(StringEscapeUtils.escapeSql(questionName));
        question.setQuestionDetail(StringEscapeUtils.escapeSql(questionDetail));
        return question;
    }

    private List<Answer> newAnswer(String questionId, List<String> answererList, List<String> voteList, List<String> answerDetailList) {
        List<Answer> answerList = new ArrayList<Answer>();
        int i = 0;
        for (String answerer : answererList) {
            Answer answer = new Answer();
            answer.setAnswerId(UUID.randomUUID().toString());
            answer.setAnswerer(StringEscapeUtils.escapeSql(answerer));
            String voteStr = voteList.get(i).toLowerCase();
            Integer voteNum;
            if (StringUtils.isEmpty(voteStr)) {
                voteNum = -1;
            } else {
                if (voteStr.contains("k")) {
                    voteNum = Integer.valueOf(voteStr.replace("k", "")) * 1000;
                } else if (voteStr.contains("w")) {
                    voteNum = Integer.valueOf(voteStr.replace("w", "")) * 10000;
                } else if (StringUtils.isNumeric(voteStr)) {
                    voteNum = Integer.valueOf(voteStr);
                } else {
                    voteNum = -1;
                }
            }
            answer.setVote(voteNum);
            answer.setAnswerDetail(StringEscapeUtils.escapeSql(answerDetailList.get(i)));
            answer.setLinkedQuestion(questionId);
            i++;
            answerList.add(answer);
        }
        return answerList;
    }

    /* 获取数据库连接的函数*/
//    private Connection getConnection() {
//        Connection con = null;  //创建用于连接数据库的Connection对象
//        try {
//            Class.forName("com.mysql.jdbc.Driver").newInstance();// 加载Mysql数据驱动
//            con = DriverManager.getConnection(mysqlUrl, mysqlUser, mysqlPassword);// 创建数据连接
//        } catch (Exception e) {
//            LOG.error("数据库连接失败" + e);
//        }
//        return con; //返回所建立的数据库连接
//    }

    private void insert(Question question, List<Answer> answerList) {
        try {
            Integer index = zhihuService.insertQuestion(question);
            if(index > 0){
                zhihuService.insertAnswer(answerList);
            }

//            Connection conn = getConnection(); // 首先要获取连接，即连接到数据库
//            String sql;
//            Statement st = conn.createStatement();    // 创建用于执行静态sql语句的Statement对象
//            sql = "INSERT INTO zhihu_question(id,question,question_detail)  VALUES ('" + question.getId() + "','" + question.getQuestion() + "','" + question.getQuestionDetail() + "')";  //
//            Integer index = st.executeUpdate(sql);  // 执行插入操作的sql语句，并返回插入数据的个数
//            if (index > 0) {
//                sql = "";
//                String headSql = "INSERT INTO zhihu_answer(id,answerer,vote,answer_detail,linked_question) VALUES";
//                for (Answer answer : answerList) {
//                    if (StringUtils.isEmpty(sql)) {
//                        sql = "('" + answer.getId() + "','" + answer.getAnswerer() + "','" + answer.getVote() + "','" + answer.getAnswerDetail() + "','" + answer.getLinkedQuestion() + "')";
//                    } else {
//                        sql = sql + ",('" + answer.getId() + "','" + answer.getAnswerer() + "','" + answer.getVote() + "','" + answer.getAnswerDetail() + "','" + answer.getLinkedQuestion() + "')";
//                    }
//                }
//                sql = headSql + sql;
//                st.executeUpdate(sql);  // 执行插入操作的sql语句，并返回插入数据的个数
//            }
//            conn.close();   //关闭数据库连接
        } catch (Exception e) {
            LOG.error(question.getQuestion() + " 插入/更新数据失败" + e);
        }
    }
}
