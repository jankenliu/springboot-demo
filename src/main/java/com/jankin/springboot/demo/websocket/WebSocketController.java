package com.jankin.springboot.demo.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by Sky
 */
@RestController
public class WebSocketController {
    @Autowired
    WebSocketServer server;

    @ResponseBody
    @RequestMapping("/login")
    public String login(String username,String password) throws IOException {
        //TODO: 校验密码
        server.sendInfo(username + "进入了聊天室!");
        return username;
    }
}