package com.arun.springsecuritycoreclient.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

/**
 * @author arun on 8/30/20
 */

@Configuration
public class WebClientConfiguration {

    @Bean
    public WebClient webClient() {

        ExchangeStrategies strategies = ExchangeStrategies
                .builder()
                .codecs(clientDefaultCodecsConfigurer -> {
                    clientDefaultCodecsConfigurer.customCodecs().register(new Jackson2JsonEncoder(new ObjectMapper(), MediaType.APPLICATION_JSON));
                }).build();
        TcpClient tcpClient = TcpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2_000)
                .doOnConnected(connection -> connection.addHandlerLast(new ReadTimeoutHandler(2))
                        .addHandlerLast(new WriteTimeoutHandler(2)));
        return WebClient.builder()
                .exchangeStrategies(strategies)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient))).build();
    }


}
