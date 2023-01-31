/*
 * Copyright (c) 2023.
 * @author Patrick Mutwiri on 1/31/23, 6:33 PM
 */

package xyz.patric.playground.utils;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;
import lombok.extern.log4j.Log4j2;
import org.junit.Rule;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

@Log4j2
public class SocketClient {
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
//            w.write("\r\n");
            w.flush();

            // read response
            while ((line = r.readLine()) != null) {
                log.info("Line: {} ", line);
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
        log.info("Counter: {} ", counter);
    }
}
