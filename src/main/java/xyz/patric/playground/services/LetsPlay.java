/*
 * Copyright (c) 2023.
 * @author Patrick Mutwiri on 1/31/23, 6:01 PM
 */

package xyz.patric.playground.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import xyz.patric.playground.utils.Enums;
import xyz.patric.playground.utils.HttpClient;
import xyz.patric.playground.utils.SocketClient;

@Service
@Log4j2
public class LetsPlay {
    @Autowired
    Environment env;

    @Autowired
    SocketClient socketClient;

    @Autowired
    HttpClient httpClient;

    public String fireRequest(Enums.MODE mode, String payload) {
        StringBuilder response = new StringBuilder();
        if (mode.equals(Enums.MODE.SOCKET)) {
            log.debug("Fire SOCKET requests. ");
            String[] addresses = env.getProperty("tests.socket.addresses", "").split(",");
            for (String address : addresses) {
                String[] socketAddress = address.split(":");
                String ip = socketAddress[0];
                int port = Integer.parseInt(socketAddress[1]);
                response.append(String.format("%s:%s /Request: %s",ip, port,payload));
                String res = socketClient.initConnect(ip, port, payload);
                response.append(String.format("%s:%s /Response: %s",ip, port,res));
            }
        } else if (mode.equals(Enums.MODE.HTTP)) {
            log.debug("Fire HTTP requests. ");
        } else {
            log.error("Mode not specified. ");
        }
        return response.toString();
    }
}
