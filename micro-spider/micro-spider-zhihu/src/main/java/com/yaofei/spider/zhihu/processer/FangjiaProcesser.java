package com.yaofei.spider.zhihu.processer;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by fei.yao on 2016/10/14.
 */
public class FangjiaProcesser implements PageProcessor {
    private Site site = Site.me().setDomain("http://wuhan.fang.com/s");
    public void process(Page page) {
        List<String> links = page.getHtml().links().regex("http://wuhan\\.fang\\.com/s").all();
        page.addTargetRequests(links);
        page.putField("name", page.getHtml().xpath("//div[@class='nlcd_name']/a/text()").toString());
        page.putField("type", page.getHtml().xpath("//div[@class='house_type']/a/text()").toString());
        page.putField("price", page.getHtml().xpath("//div[@class='nhouse_price']/span/text()").toString());
    }

    public Site getSite() {
        return site;

    }

    public static void main(String[] args) {
        Spider.create(new FangjiaProcesser())
                .pipeline(new ConsolePipeline()).run();
    }

}
