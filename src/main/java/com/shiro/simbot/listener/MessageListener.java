package com.shiro.simbot.listener;

import love.forte.simboot.annotation.ContentTrim;
import love.forte.simboot.annotation.Filter;
import love.forte.simboot.annotation.Listener;
import love.forte.simbot.event.GroupMessageEvent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * 监听消息
 */
@Component
public class MessageListener {

    @Listener
    @Filter(value = "呜啪")
    @ContentTrim // 当匹配被at时，将'at'这个特殊消息移除后，剩余的文本消息大概率存在前后空格，通过此注解在匹配的时候忽略前后空格
    public CompletableFuture<?> onChannelMessage(GroupMessageEvent event) {
        // 将要监听的事件类型放在参数里，即代表监听此类型的消息
        // ...
        // 将 CompletableFuture 作为返回值，simbot会以非阻塞的形式处理它
        return event.replyAsync("呜啪呜啪!");
    }

    @Listener
    @ContentTrim
    public CompletableFuture<?> feedBack(GroupMessageEvent event) {
        String text = event.getMessageContent().getPlainText();
        if (StringUtils.containsAny(text,"猪", "笨比", "战犯", "笨蛋")) {
            return event.replyAsync("我真是个天才!");
        }
        return null;
    }
}
