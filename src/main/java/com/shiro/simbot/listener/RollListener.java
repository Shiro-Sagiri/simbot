package com.shiro.simbot.listener;

import love.forte.simboot.annotation.ContentTrim;
import love.forte.simboot.annotation.Filter;
import love.forte.simboot.annotation.Listener;
import love.forte.simbot.event.GroupMessageEvent;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Component
public class RollListener {

    /**
     * 用法
     * @param event 事件
     * @return 返回值
     */
    @Listener
    @Filter(value = "/roll")
    @ContentTrim
    public CompletableFuture<?> rollHelper(GroupMessageEvent event) {
        return event.replyAsync("用法：1. /roll <数字>，例如/roll 100, 即会返回0-100之间的数字 \n 2. /roll <对象1> <对象2> <对象3> ...，例如/roll 猪 狗 猫，即会返回这些对象中的一个");
    }

    /**
     * roll数字
     * @param event 事件
     * @return 返回值
     */
    @Listener
    @ContentTrim
    public CompletableFuture<?> rollNumber(GroupMessageEvent event) {
        String message = event.getMessageContent().getPlainText();
        String[] params = message.split(" ");
        if (params.length == 2) {
            if (params[0].equals("/roll")) {
                try {
                    int max = Integer.parseInt(params[1]);
                    int result = (int) (Math.random() * (max + 1));
                    return event.replyAsync("你掷出了" + result);
                } catch (Exception e) {
                    return event.replyAsync("参数错误");
                }
            }
        }
        return null;
    }

    /**
     * roll对象
     * @param event 事件
     * @return 返回值
     */
    @Listener
    @ContentTrim
    public CompletableFuture<?> rollObject(GroupMessageEvent event) {
        String message = event.getMessageContent().getPlainText();
        String[] params = message.split(" ");
        if (params.length > 2 ) {
            if (params[0].equals("/roll")) {
                try {
                    Random random = new Random();
                    int result = random.nextInt(1,params.length);
                    return event.replyAsync("你掷出了" + params[result]);
                } catch (Exception e) {
                    return event.replyAsync("参数错误");
                }
            }
        }
        return null;
    }
}
