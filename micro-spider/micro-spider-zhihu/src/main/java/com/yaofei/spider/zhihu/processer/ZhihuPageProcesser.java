package com.yaofei.spider.zhihu.processer;

import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by fei.yao on 2016/10/14.
 */
@Service
public class ZhihuPageProcesser implements PageProcessor {

    private Site site = Site.me().setDomain("https://www.zhihu.com").addStartUrl("https://www.zhihu.com/question/20232626");

    public Site getSite() {
        return site;
    }

    public void process(Page page) {

        page.addTargetRequests(page.getHtml().regex("https://www\\.zhihu\\.com/question/\\d+").all());

        page.putField("questionName", page.getHtml().xpath("//h2[@class='zm-item-title']/span/text()").toString());

        page.putField("questionDetail", page.getHtml().xpath("//div[@id='zh-question-detail']/div/allText()").toString());

        page.putField("answererList", page.getHtml().xpath("//div[@id='zh-question-answer-wrap']/div[@class='zm-item-answer']/div[@class='answer-head']/div[@class='zm-item-answer-author-info']/text()").all());

        page.putField("voteList", page.getHtml().xpath("//div[@id='zh-question-answer-wrap']/div[@class='zm-item-answer']/div[@class='zm-votebar']/button[@class='up']/span[@class='count']/text()").all());

        page.putField("answerDetailList", page.getHtml().xpath("//div[@id='zh-question-answer-wrap']/div[@class='zm-item-answer']/div[@class='zm-item-rich-text']/div[@class='zm-editable-content']/outerHtml()").replace("<noscript>", "").replace("</noscript>", "").replace("\\bsrc=\"//\\b.*\\b\"", "").all());
    }
}
