/*
 * Copyright (c) 2023.
 * @author Patrick Mutwiri on 1/31/23, 6:01 PM
 */

package xyz.patric.playground.utils;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;
import lombok.extern.log4j.Log4j2;
import org.junit.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Log4j2
public class SocketClient {
    @Autowired
    Environment e;

    @Rule
    public ConcurrentRule concurrently = new ConcurrentRule();

    @Rule
    public RepeatingRule repeatedly = new RepeatingRule();

    private static final AtomicInteger counter = new AtomicInteger();

    @Concurrent(count = 7)
    @Repeating(repetition = 14)
    public String initConnect(String ip, int port, String payload){
        log.info("{} Input: {} ", counter.get(), payload);
        Socket s;
        String resp = "", line = "";
        try {
            s = new Socket(ip, port);
            s.setKeepAlive(true);
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            BufferedWriter w = new BufferedWriter(new OutputStreamWriter(os));

            // write
            w.write(payload);
            w.write("\r\n");
            w.flush();

            // read response
            while ((line = r.readLine()) != null) {
                log.debug("Line: {} ", line);
                resp = line;
            }
        } catch (IOException e) {
            log.error("Could not connect to {}:{} ; Error message: {} ", ip, port, e.getMessage(), e);
            resp = e.getMessage();
        }
        counter.getAndIncrement();
        log.info("{} Output: {} ", counter.get(), resp);
        return resp;
    }

    @Concurrent(count = 5)
    @Repeating(repetition = 10)
    public void runsMultipleTimes() {
        counter.getAndIncrement();
        log.debug("Counter: {} ", counter);
    }
}
