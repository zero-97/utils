package com.zero.chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * WebScoket配置处理器
 * @author zero
 */
@Configuration
public class WebSocketConfig {
    /**
     * ServerEndpointExporter：自动注册使用 @ServerEndpoint 声明的 websocket endpoint
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
