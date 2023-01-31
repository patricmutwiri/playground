/*
 * Copyright (c) 2023.
 * @author Patrick Mutwiri on 1/31/23, 6:01 PM
 */

package xyz.patric.playground.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.patric.playground.services.LetsPlay;
import xyz.patric.playground.utils.Enums;

@RestController
@RequestMapping("/")
public class PlaygroundController {
    @Autowired
    LetsPlay letsPlay;

    @PostMapping("run")
    public ResponseEntity<String> request(@PathVariable Enums.MODE mode, @RequestBody String payload) {
        String response = letsPlay.fireRequest(mode, payload);
        return ResponseEntity.ok(response);
    }
}
